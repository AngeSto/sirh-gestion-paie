package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.GradeService;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	@Autowired EntrepriseRepository entrepriseRepository;
	@Autowired GradeRepository gradeRepository;
	@Autowired ProfilRemunerationRepository profilRemunerationRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		List<Entreprise> listeEntreprise = entrepriseRepository.findAll();
		List<Grade> listeGrade = gradeRepository.findAll();
		List<ProfilRemuneration> listeProfil = profilRemunerationRepository.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("listeEntreprise", listeEntreprise);
		mv.addObject("listeProfil", listeProfil);
		mv.addObject("listeGrade", listeGrade);
		mv.addObject("employe", new RemunerationEmploye());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path ="/creer")
	public String submitForm(@RequestParam("matricule") String matricule, @RequestParam("entreprise") String entreprise, @RequestParam("profilRemuneration") String profil, @RequestParam("grade") String grade) {
		RemunerationEmploye remuEmploye = new RemunerationEmploye();
		remuEmploye.setDateDeCreation(LocalDateTime.now());
		remuEmploye.setMatricule(matricule);
		remuEmploye.setEntreprise(entrepriseRepository.findByDenomination(entreprise));
		remuEmploye.setProfilRemuneration(profilRemunerationRepository.findByCode(profil));
		remuEmploye.setGrade(gradeRepository.findByCode(grade));
		remunerationEmployeRepository.save(remuEmploye);
		return "redirect:/mvc/employes/liste";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/liste")
	public ModelAndView listeEmploye() {
		List<RemunerationEmploye> employes = remunerationEmployeRepository.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listeEmployes");
		mv.addObject("employes", employes);
		return mv;

	}
}
