package com.admin.service;

import java.util.List;

import com.admin.entity.Candidate;
import com.admin.entity.Feedback;
import com.admin.entity.PanelMember;
import com.admin.exception.CandidateNotFoundException;
import com.admin.exception.InterviewNotFoundException;
import com.admin.exception.PanelMemberNotFoundException;

public interface PanelMemberService {

	PanelMember addPanel(PanelMember panelMember);

    Candidate declareRatings(int candidateId, int interviewId, int hrRating, int techRating) throws CandidateNotFoundException, InterviewNotFoundException;

	List<PanelMember> getAllPanelMembers() ;
	
	String deletePanelMember(int employeeId) throws PanelMemberNotFoundException;


	Feedback addFeedback(int candidateId, int interviewId, Integer hrRating, int techRating)
			throws InterviewNotFoundException, CandidateNotFoundException;

	List<Feedback> getFeedbacks();

	Feedback finalReview(int feedbackId);

}
