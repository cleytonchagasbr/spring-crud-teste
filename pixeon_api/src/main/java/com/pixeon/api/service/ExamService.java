package com.pixeon.api.service;

import java.util.Optional;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pixeon.api.dto.ExamDto;
import com.pixeon.api.dto.HealthcareInstitutionDto;
import com.pixeon.api.entity.ExamEntity;
import com.pixeon.api.entity.HealthcareInstitutionEntity;
import com.pixeon.api.repository.ExamRepository;
import com.pixeon.api.repository.HealthcareInstitutionRepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository repository;

	@Autowired
	private HealthcareInstitutionRepository healthcareRepository;

	public ResponseEntity<?> createExam(ExamDto dto) {

		try {
			ExamEntity entity = new ExamEntity();
			Optional<HealthcareInstitutionEntity> institution = institutionExist(dto.getHealthcareInstitutionCnpj());

			if (institution == null) {
				return new ResponseEntity<String>("Instituição não encontrada para o CNPJ informado, por favor efetue o cadastro.",
						HttpStatus.BAD_REQUEST);
			}

			entity.setHealthcareInstitution(institution.get());
			entity.setPatientName(dto.getPatientName());
			entity.setPatientAge(dto.getPatientAge());
			entity.setPatientGender(dto.getPatientGender());
			entity.setPhysicianName(dto.getPhysicianName());
			entity.setPhysicianCRM(dto.getPhysicianCRM());
			entity.setProcedureName(dto.getProcedureName());

			if (exameExist(entity)) {
				return new ResponseEntity<String>("Exame já cadastrado.", HttpStatus.BAD_REQUEST);
			}

			if (!enoughCoinsAndSubtractOneCurrency(dto.getHealthcareInstitutionCnpj())) {
				return new ResponseEntity<String>("Instituição sem saldo para realizar exames. Saldo atual: 0",
						HttpStatus.BAD_REQUEST);
			}
			ExamEntity response = repository.save(entity);

			return new ResponseEntity<ExamEntity>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	private Optional<HealthcareInstitutionEntity> institutionExist(String cnpj) {
		Optional<HealthcareInstitutionEntity> entity = healthcareRepository.findByCnpj(cnpj);

		if (entity.isPresent()) {
			return entity;
		} else {
			return null;
		}

	}

	private boolean enoughCoinsAndSubtractOneCurrency(String cnpj) {
		Optional<HealthcareInstitutionEntity> entity = healthcareRepository.findByCnpj(cnpj);

		if (entity.isPresent()) {
			if (entity.get().getCoins() > 0) {
				Integer novoSaldo = entity.get().getCoins() - 1;
				healthcareRepository.updateCoins(novoSaldo, cnpj);
				return true;
			}

		}

		return false;
	}

	public boolean exameExist(ExamEntity entity) {
		try {
			//boolean response = repository.exists(Example.of(entity));
			
			Optional<ExamEntity> responseEntity = repository.findByParams(entity.getHealthcareInstitution() , entity.getPatientAge(), entity.getPatientGender(), entity.getPatientName(), 
					entity.getPhysicianCRM(), entity.getPhysicianName(), entity.getProcedureName());
			if (responseEntity.isPresent()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public ResponseEntity<?> getExam(String institution, Integer id) {
		try {
			Optional<ExamEntity> response = repository.findById(id.longValue());

			if (response.isPresent()) {
				if (response.get().getHealthcareInstitution().equals(institution)) {
					return new ResponseEntity<ExamEntity>(response.get(), HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Instituição sem acesso aos exames de outra instituição.",
							HttpStatus.NOT_ACCEPTABLE);
				}
			} else {
				return new ResponseEntity<String>("Exame não localizado.", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
