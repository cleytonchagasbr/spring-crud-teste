package com.pixeon.api.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.pixeon.api.entity.HealthcareInstitutionEntity;

public interface HealthcareInstitutionRepository extends JpaRepository<HealthcareInstitutionEntity, Long>{

	@Query("select u from HealthcareInstitutionEntity u where u.cnpj = ?1")
	public Optional<HealthcareInstitutionEntity> findByCnpj(String cnpj);

	@Transactional
	@Modifying
	@Query("Update HealthcareInstitutionEntity a SET a.coins = ?1 where a.cnpj = ?2")
	public void updateCoins(Integer novoSaldo, String cnpj);
	
	

}
