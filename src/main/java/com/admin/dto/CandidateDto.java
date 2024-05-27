package com.admin.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {

	private String candidateName;
	
	private String primarySkill;
	
	private String secondarySkill;
	
	private String qualification;
	
	private String designation;
	
	private String noticePeriod;
	
	private String location;
	
}
