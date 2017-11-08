package dev.paie.service;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
		grades.forEach((index, grade) -> em.persist(grade));
		entreprises.forEach((index, entreprise) -> em.persist(entreprise));
		profilsRemuneration.forEach((index, profilRemuneration) -> em.persist(profilRemuneration));
		periodes.forEach(periode -> em.persist(periode));
		
		em.persist(new Utilisateur("Ange", passwordEncoder.encode("mpd"), true, ROLES.ROLE_ADMINISTRATEUR));
		em.persist(new Utilisateur("Invite", passwordEncoder.encode("invite"), true, ROLES.ROLE_UTILISATEUR));
		
		RemunerationEmploye employe1 = new RemunerationEmploye("M01",entreprises.get("entreprise1"), profilsRemuneration.get("profil-technicien"), grades.get("grade1"), LocalDateTime.of(2012, 04, 05, 12, 5, 15));
		em.persist(employe1);
		RemunerationEmploye employe2 = new RemunerationEmploye("M02",entreprises.get("entreprise2"), profilsRemuneration.get("profil-cadre"), grades.get("grade2"), LocalDateTime.of(2013, 05, 03, 22, 6, 48));
		em.persist(employe2);
		RemunerationEmploye employe3 = new RemunerationEmploye("M03",entreprises.get("entreprise3"), profilsRemuneration.get("profil-stagiaire"), grades.get("grade3"), LocalDateTime.of(2015, 02, 01, 02, 3, 22));
		em.persist(employe3);
		
		em.persist(new BulletinSalaire(employe1, periodes.get(0),new BigDecimal(100), LocalDateTime.now()));
		em.persist(new BulletinSalaire(employe2, periodes.get(0),new BigDecimal(200), LocalDateTime.now()));
		em.persist(new BulletinSalaire(employe3, periodes.get(0),new BigDecimal(300), LocalDateTime.now()));
		context.close();
	}

	
		

		
			
		
	

}
