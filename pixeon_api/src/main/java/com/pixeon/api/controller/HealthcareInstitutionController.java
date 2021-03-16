package com.pixeon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixeon.api.dto.HealthcareInstitutionDto;
import com.pixeon.api.service.HealthcareInstitutionService;

@RestController
public class HealthcareInstitutionController {
	
	@Autowired
	private HealthcareInstitutionService service;
	
	@PostMapping(value = "/createHealthcareIntitution")
	public ResponseEntity<?> createHealthcareInstitution(@RequestBody HealthcareInstitutionDto requestBody) {
		return service.createInstitution(requestBody);
	}
	
	@GetMapping(value = "/listAllInstitutions")
	public ResponseEntity<?> listAllInstitutions() {
		return service.listAllInstitutions();
	}
	
	@GetMapping(value = "/getOneInstitution")
	public ResponseEntity<?> getOneInstitution(@RequestParam Integer id) {
		return service.getOneInstitution(id);
	}
	
	@DeleteMapping(value = "/deleteInstitution")
	public ResponseEntity<?> deleteInstitution(@RequestParam Integer id) {
		return service.delete(id);
	}


}
