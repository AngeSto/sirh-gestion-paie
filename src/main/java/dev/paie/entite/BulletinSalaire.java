package dev.paie.entite;

import java.math.BigDecimal;
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
public class BulletinSalaire implements Comparable<BulletinSalaire>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="ID_RemEmploye")
	private RemunerationEmploye remunerationEmploye;
	@ManyToOne
	@JoinColumn(name="ID_Periode")
	private Periode periode;
	@Column
	private BigDecimal primeExceptionnelle;
	@Column
	private LocalDateTime dateDeCreation;
	
	public BulletinSalaire() {

	}
	
	public RemunerationEmploye getRemunerationEmploye() {
		return remunerationEmploye;
	}
	public void setRemunerationEmploye(RemunerationEmploye remunerationEmploye) {
		this.remunerationEmploye = remunerationEmploye;
	}
	public Periode getPeriode() {
		return periode;
	}
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}
	public void setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	/**Getter for dateDeCreation
	 * @return dateDeCreation
	 */
	public LocalDateTime getDateDeCreation() {
		return dateDeCreation;
	}

	/**
	 * @param dateDeCreation the dateDeCreation to set
	 */
	public void setDateDeCreation(LocalDateTime dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}
	
	public String getDateDeCreationFormat(){
		return dateDeCreation.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss"));
	}

	@Override
	public int compareTo(BulletinSalaire bulletinSalaire) {
		if (this.id == bulletinSalaire.id){
	    	return 0;
	    }
	    else if (this.id > bulletinSalaire.id){
	    	return 1;
	    }
	    else {
	    	return -1;
	    }
	
	}
	
}
