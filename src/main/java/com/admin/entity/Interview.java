package com.admin.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "INTERVIEWSCHEDULE")
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int interviewId;

	@JsonIgnore
	private int techRating;

	@JsonIgnore
	private int hrRating;

	private String company;

	private double salaryPackage;

	@JsonIgnore
	private String finalStatus;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "CANDIDATE_INTERVIEWSCHEDULE", joinColumns = @JoinColumn(name = "interview_id"), inverseJoinColumns = @JoinColumn(name = "candidate_id"))
	private List<Candidate> candidates;
}
