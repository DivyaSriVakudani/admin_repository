package com.admin.service;

import java.util.List;

import com.admin.dto.CandidateDto;
import com.admin.entity.Candidate;
import com.admin.exception.CandidateNotFoundException;
import com.admin.exception.PanelMemberNotFoundException;

public interface CandidateService {

	Candidate addCandidate(CandidateDto candidateDto);

	List<Candidate> getAllCandidates();

	String deleteCandidate(int candidateId) throws CandidateNotFoundException;


}
