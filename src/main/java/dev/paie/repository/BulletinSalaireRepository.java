package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.BulletinSalaire;

public interface BulletinSalaireRepository extends JpaRepository<BulletinSalaire, Integer>{
	
	public BulletinSalaire findById(Integer id);
	
}
