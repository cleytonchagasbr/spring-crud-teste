package com.pixeon.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pixeon.api.dto.HealthcareInstitutionDto;
import com.pixeon.api.entity.HealthcareInstitutionEntity;
import com.pixeon.api.repository.HealthcareInstitutionRepository;

@Service
public class HealthcareInstitutionService {

	final Integer coinsDefault = 3;

	@Autowired
	private HealthcareInstitutionRepository repository;

	public ResponseEntity<?> createInstitution(HealthcareInstitutionDto dto) {

		try {
			HealthcareInstitutionEntity entity = new HealthcareInstitutionEntity();
			entity.setName(dto.getName());
			entity.setCnpj(dto.getCnpj());
			entity.setCoins(coinsDefault);

			if (containsInBase(entity)) {
				return new ResponseEntity<String>("Instituição já cadastrada", HttpStatus.BAD_REQUEST);
			}
			HealthcareInstitutionEntity response = repository.save(entity);
			return new ResponseEntity<HealthcareInstitutionEntity>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> listAllInstitutions() {
		try {
			List<HealthcareInstitutionEntity> institutions = repository.findAll();

			return new ResponseEntity<List<HealthcareInstitutionEntity>>(institutions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> getOneInstitution(Integer id) {
		try {
			Optional<HealthcareInstitutionEntity> entityResponse = repository.findById(id.longValue());
			if (!entityResponse.isPresent()) {
				return new ResponseEntity<String>("Instituição não encontrada.", HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<HealthcareInstitutionEntity>(entityResponse.get(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> delete(Integer id) {

		try {
			Optional<HealthcareInstitutionEntity> entity = repository.findById(id.longValue());

			if (!entity.isPresent()) {
				return new ResponseEntity<String>("Instituição não encontrada para deletar.", HttpStatus.BAD_REQUEST);
			}
			repository.delete(entity.get());
			return new ResponseEntity<String>("Instituição deletada.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public boolean containsInBase(HealthcareInstitutionEntity entity) {

		try {

			Optional<HealthcareInstitutionEntity> response = repository.findOne(Example.of(entity));

			if (response.isPresent()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

}
