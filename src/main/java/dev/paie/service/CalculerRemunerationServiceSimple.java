package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;

	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {

		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();

		String salaireBase = paieUtils.formaterBigDecimal(bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase()
				.multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase()));
		resultat.setSalaireDeBase(salaireBase);

		String salaireBrut = paieUtils
				.formaterBigDecimal(new BigDecimal(salaireBase).add(bulletin.getPrimeExceptionnelle()));
		resultat.setSalaireBrut(salaireBrut);

		String totalRetenueSalariale = paieUtils.formaterBigDecimal(
				bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().stream()
						.filter(cotisation -> cotisation.getTauxSalarial()!=null)
						.map(cotisation -> cotisation.getTauxSalarial().multiply(new BigDecimal(salaireBrut)))
						.reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
		resultat.setTotalRetenueSalarial(totalRetenueSalariale);
		
		
		
		return resultat;
	}

}
