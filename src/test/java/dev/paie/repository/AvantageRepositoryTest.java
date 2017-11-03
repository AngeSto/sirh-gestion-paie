package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;


@ContextConfiguration(classes = { ServicesConfig.class})
@RunWith(SpringRunner.class)

public class AvantageRepositoryTest {
	
	@Autowired private AvantageRepository avantageRepository;
	
	private JdbcTemplate jdbcTemplate;
	@Autowired DataSource dataSource;
	
	@Before
	public void truncate() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("TRUNCATE TABLE AVANTAGE");

	}
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour(){
		
		Avantage avantage = new Avantage();
		avantage.setCode("1111");
		avantage.setNom("1er avantage");
		avantage.setMontant(new BigDecimal(25));
		avantageRepository.save(avantage);
		Avantage newAvantage = avantageRepository.findOne(avantageRepository.findByCode("1111").getId());
		assertThat(newAvantage).isEqualTo(avantage);
		
		newAvantage.setCode("2222");
		newAvantage.setNom("1er avantage modifier");
		newAvantage.setMontant(new BigDecimal(50));
		avantageRepository.save(newAvantage);
		Avantage avantageModifier = avantageRepository.findOne(avantageRepository.findByCode("2222").getId());
		assertThat(newAvantage).isEqualTo(avantageModifier);
	}
	

}
