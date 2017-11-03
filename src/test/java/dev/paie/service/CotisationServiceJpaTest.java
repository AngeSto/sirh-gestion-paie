package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;


@ContextConfiguration(classes = { ServicesConfig.class})
@RunWith(SpringRunner.class)

public class CotisationServiceJpaTest {
	
	@Autowired private CotisationService cotisationService;
	
	private JdbcTemplate jdbcTemplate;
	@Autowired DataSource dataSource;
	
	@Before
	public void truncate() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("TRUNCATE TABLE COTISATION");

	}
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour(){
		
		Cotisation cotisation = new Cotisation();
		cotisation.setCode("111111");
		cotisation.setLibelle("Libelle");
		cotisation.setTauxPatronal(new BigDecimal(75));
		cotisation.setTauxSalarial(new BigDecimal(2));
		
		cotisationService.sauvegarder(cotisation);
		
		List<Cotisation> list = cotisationService.lister();
		Cotisation nouvelleCotisation = list.stream().findFirst().get();
		
		assertThat(nouvelleCotisation).isEqualTo(cotisation);
		
		nouvelleCotisation.setCode("222222");
		nouvelleCotisation.setLibelle("Libelle2");
		nouvelleCotisation.setTauxPatronal(new BigDecimal(50));
		nouvelleCotisation.setTauxSalarial(new BigDecimal(5));
		
		cotisationService.mettreAJour(nouvelleCotisation);
		
		assertThat(nouvelleCotisation).isEqualTo(cotisationService.lister().stream().findFirst().get());
	}

}
