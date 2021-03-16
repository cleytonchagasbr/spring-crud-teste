package com.pixeon.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pixeon.api.entity.ExamEntity;
import com.pixeon.api.entity.HealthcareInstitutionEntity;

public interface ExamRepository extends JpaRepository<ExamEntity, Long>{

	@Query("select u from ExamEntity u where u.healthcareInstitution = ?1 and u.patientAge = ?2 and u.patientGender = ?3 and u.patientName = ?4 and u.physicianCRM = ?5 and u.physicianName = ?6 and u.procedureName = ?7")
	Optional<ExamEntity> findByParams(HealthcareInstitutionEntity healthcareInstitution, Integer patientAge, String patientGender,
			String patientName, String physicianCRM, String physicianName, String procedureName);
	


	

}
