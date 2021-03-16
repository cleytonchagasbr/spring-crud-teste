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
public class HealthcareInstitutionDto implements Serializable {

	private static final long serialVersionUID = -8350363493770003549L;

	private String name;
	
	private String cnpj;

}
