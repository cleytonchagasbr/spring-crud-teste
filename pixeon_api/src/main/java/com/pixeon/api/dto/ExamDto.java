package com.pixeon.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto implements Serializable {

	private static final long serialVersionUID = -850968341978721694L;
	
	private String healthcareInstitutionCnpj;

	private String patientName;

	private Integer patientAge;

	private String patientGender;

	private String physicianName;

	private String physicianCRM;

	private String procedureName;

}
