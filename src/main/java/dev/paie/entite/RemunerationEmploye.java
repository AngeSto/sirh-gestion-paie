package dev.paie.entite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RemunerationEmploye {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String matricule;
	@ManyToOne
	@JoinColumn(name="ID_Entreprise")
	private Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name="ID_Profil_Remuneration")
	private ProfilRemuneration profilRemuneration;
	@ManyToOne
	@JoinColumn(name="ID_Grade")
	private Grade grade;
	@Column
	private LocalDateTime dateDeCreation;
	
	public RemunerationEmploye() {

	}
	
	public RemunerationEmploye(String matricule, Entreprise entreprise, ProfilRemuneration profilRemuneration,
			Grade grade, LocalDateTime dateDeCreation) {
		super();
		this.matricule = matricule;
		this.entreprise = entreprise;
		this.profilRemuneration = profilRemuneration;
		this.grade = grade;
		this.dateDeCreation = dateDeCreation;
	}

	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public ProfilRemuneration getProfilRemuneration() {
		return profilRemuneration;
	}
	public void setProfilRemuneration(ProfilRemuneration profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/**Getter for datedecreation
	 * @return datedecreation
	 */
	public LocalDateTime getDateDeCreation() {
		return dateDeCreation;
	}

	/**
	 * @param datedecreation the datedecreation to set
	 */
	public void setDateDeCreation(LocalDateTime dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}
	
	public String getDateDeCreationFormat(){
		return dateDeCreation.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss"));
	}
	
	

}
