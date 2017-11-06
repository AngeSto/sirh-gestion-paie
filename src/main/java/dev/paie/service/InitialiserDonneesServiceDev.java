package dev.paie.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

import static java.time.temporal.TemporalAdjusters.*;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {
	
	@PersistenceContext private EntityManager em;

	@Transactional
	@Override
	public void initialiser() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cotisations-imposables.xml",
				"cotisations-non-imposables.xml", "entreprises.xml", "grades.xml", "profils-remuneration.xml");
		Map<String, Cotisation> cotisations = context.getBeansOfType(Cotisation.class);
		Map<String, Grade> grades = context.getBeansOfType(Grade.class);
		Map<String, Entreprise> entreprises = context.getBeansOfType(Entreprise.class);
		Map<String, ProfilRemuneration> profilsRemuneration = context.getBeansOfType(ProfilRemuneration.class);
		List<Periode> periodes = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			LocalDate debut = LocalDate.of(2017, i, 1);
			periodes.add(new Periode(debut, debut.with(lastDayOfMonth())));
		}
		
		cotisations.forEach((index, cotisation) -> em.persist(cotisation));
		grades.forEach((index, grade)-> em.persist(grade));
		entreprises.forEach((index, entreprise) -> em.persist(entreprise));
		profilsRemuneration.forEach((index, profilRemuneration) -> em.persist(profilRemuneration));
		periodes.forEach(periode->em.persist(periode));

		context.close();
	}

}
