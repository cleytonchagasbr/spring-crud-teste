package com.pixeon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pixeon.api.dto.ExamDto;
import com.pixeon.api.service.ExamService;

@RestController
public class ExamController {
	
	@Autowired
	private ExamService service;
	
	@PostMapping(value = "/createExam")
	public ResponseEntity<?> createExam(@RequestBody ExamDto dto) {
		return service.createExam(dto);
	}
	
	@GetMapping(value = "/getExam")
	public ResponseEntity<?> getExam(@RequestParam String institution, @RequestParam Integer id_exame) {
		return service.getExam(institution, id_exame);
	}
	
	@PutMapping(value = "/putExam")
	public void putExam() {
		
	}
	
	@DeleteMapping(value = "deleteExam")
	public void deleteExam() {
		
	}
	
	

}
