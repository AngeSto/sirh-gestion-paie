package dev.paie.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinController {

	@Autowired
	PeriodeRepository periodeRepository;
	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepository;
	@Autowired
	BulletinSalaireRepository bulletinSalaireRepository;
	@Autowired
	CalculerRemunerationService remunerationService;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerBulletin() {
		List<Periode> periodes = periodeRepository.findAll();
		List<RemunerationEmploye> employes = remunerationEmployeRepository.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("periodes", periodes);
		mv.addObject("employes", employes);
		mv.addObject("bulletin", new BulletinSalaire());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String submitForm(@RequestParam("periode") Integer periode,
			@RequestParam("remunerationEmploye.matricule") String matricule,
			@RequestParam("primeExceptionnelle") Double prime) {
		BulletinSalaire bulletin = new BulletinSalaire();
		bulletin.setDateDeCreation(LocalDateTime.now());
		bulletin.setPeriode(periodeRepository.findById(periode));
		bulletin.setRemunerationEmploye(remunerationEmployeRepository.findByMatricule(matricule));
		bulletin.setPrimeExceptionnelle(new BigDecimal(prime));
		bulletinSalaireRepository.save(bulletin);

		return "redirect:/mvc/bulletins/liste";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/liste")
	@Secured({"ROLE_ADMINISTRATEUR", "ROLE_UTILISATEUR"})
	public ModelAndView listerBulletin() {
		Map <BulletinSalaire, ResultatCalculRemuneration> list = new TreeMap<>();
		bulletinSalaireRepository.findAll().forEach(bulletin -> list.put(bulletin, remunerationService.calculer(bulletin)));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listeBulletins");
		mv.addObject("list", list);
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/visualiser/{id}")
	public ModelAndView visualierBulletin(@PathVariable int id) {
		BulletinSalaire bulletin = bulletinSalaireRepository.findById(id);
		ResultatCalculRemuneration resultat = remunerationService.calculer(bulletin);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/visualiserBulletin");
		mv.addObject("bulletin", bulletin);
		mv.addObject("resultat", resultat);
		return mv;
	}
}
