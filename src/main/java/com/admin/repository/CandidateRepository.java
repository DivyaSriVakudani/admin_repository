package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.dto.CandidateDto;
import com.admin.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer>{

	Candidate save(CandidateDto a);

}
