package com.pixeon.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "exams")
public class ExamEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "healthcareInstitution")
	private HealthcareInstitutionEntity healthcareInstitution;

	@Column(name = "patientName")
	private String patientName;

	@Column(name = "patientAge")
	private Integer patientAge;

	@Column(name = "patientGender")
	private String patientGender;

	@Column(name = "physicianName")
	private String physicianName;

	@Column(name = "physicianCRM")
	private String physicianCRM;

	@Column(name = "procedureName")
	private String procedureName;

}
