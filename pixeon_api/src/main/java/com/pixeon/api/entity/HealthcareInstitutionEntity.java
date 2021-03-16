package com.pixeon.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter
@Setter
@Table(name = "healthcare_institution")
public class HealthcareInstitutionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "coins")
	private Integer coins;
}
