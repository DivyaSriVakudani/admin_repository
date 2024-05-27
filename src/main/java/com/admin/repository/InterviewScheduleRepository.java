package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.Interview;

@Repository
public interface InterviewScheduleRepository extends JpaRepository<Interview, Integer>{

}
