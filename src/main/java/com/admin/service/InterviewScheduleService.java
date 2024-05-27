package com.admin.service;

import java.util.List;

import com.admin.dto.InterviewDto;
import com.admin.entity.Candidate;
import com.admin.entity.Interview;

public interface InterviewScheduleService {

	Candidate assignInterview(int interviewId, int candidateId);

	Interview addInterview(InterviewDto interviewDto);

	List<Interview> getAllInterviews();

}
