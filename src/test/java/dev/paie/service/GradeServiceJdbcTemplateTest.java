package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { ServicesConfig.class})
@RunWith(SpringRunner.class)


public class GradeServiceJdbcTemplateTest {
	
	@Autowired private GradeService gradeService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour(){
		
		gradeService.reinitialiser();
		
		Grade grade = new Grade();
		grade.setCode("111");
		grade.setNbHeuresBase(new BigDecimal(35));
		grade.setTauxBase(new BigDecimal(15));
		
		//sauvegarder un nouveau grade
		gradeService.sauvegarder(grade);
		
		//Vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		List<Grade> list  = gradeService.lister();
		assertThat(list.stream().findFirst().get().equals(grade));
		
		//modifier un grade
		Grade grade2 = list.stream().findFirst().get();
		grade2.setCode("222");
		grade2.setNbHeuresBase(new BigDecimal(40));
		grade2.setTauxBase(new BigDecimal(5));
		gradeService.mettreAJour(grade2);
		list = gradeService.lister();
		assertThat(list.stream().findFirst().get().equals(grade2));
		
	}

}
