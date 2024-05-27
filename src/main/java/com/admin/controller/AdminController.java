package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.CandidateDto;
import com.admin.dto.InterviewDto;
import com.admin.entity.Candidate;
import com.admin.entity.Feedback;
import com.admin.entity.Interview;
import com.admin.entity.PanelMember;
import com.admin.exception.CandidateNotFoundException;
import com.admin.exception.InterviewNotFoundException;
import com.admin.exception.PanelMemberNotFoundException;
import com.admin.repository.FeedbackRepository;
import com.admin.service.CandidateService;
import com.admin.service.InterviewScheduleService;
import com.admin.service.PanelMemberService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CandidateService candidateSerive;
	@Autowired
	private InterviewScheduleService interviewScheduleService;
	@Autowired
	private PanelMemberService panelMemberService;

	@PostMapping("/addCandidate")
	public ResponseEntity<Candidate> addCandidate(@RequestBody CandidateDto candidateDto) {
		return new ResponseEntity<Candidate>(candidateSerive.addCandidate(candidateDto), HttpStatus.CREATED);
	}

	@GetMapping("/getAllCandidates")
	public ResponseEntity<List<Candidate>> getAllCandidates() {
		return new ResponseEntity<List<Candidate>>(candidateSerive.getAllCandidates(), HttpStatus.OK);
	}

	@PostMapping("/assignInterview/{candidateId}/{interviewId}")
	public ResponseEntity<Candidate> assignInterview(@PathVariable int interviewId,
			@PathVariable int candidateId) {
		return new ResponseEntity<Candidate>(interviewScheduleService.assignInterview(interviewId, candidateId),
				HttpStatus.OK);
	}

	@PostMapping("/addInterview")
	public ResponseEntity<Interview> addInterview(@RequestBody InterviewDto interviewDto) {
		return new ResponseEntity<Interview>(interviewScheduleService.addInterview(interviewDto),
				HttpStatus.CREATED);
	}

	@PostMapping("/addPanel")
	public ResponseEntity<PanelMember> addPanel(@RequestBody PanelMember panelMember) {
		return new ResponseEntity<PanelMember>(panelMemberService.addPanel(panelMember), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteCandidate/{candidateId}")
	public ResponseEntity<String> deleteCandidate(@PathVariable int candidateId) throws CandidateNotFoundException {
		return new ResponseEntity<String>(candidateSerive.deleteCandidate(candidateId), HttpStatus.OK);
	}

	@GetMapping("/getAllInterviews")
	public ResponseEntity<List<Interview>> getAllInterviews() {
		return new ResponseEntity<List<Interview>>(interviewScheduleService.getAllInterviews(), HttpStatus.OK);
	}

	@PutMapping("/declareRatings/{candidateId}")
	public ResponseEntity<Candidate> declareRatings(@PathVariable int candidateId,
			@RequestParam("interviewId") int interviewId, @RequestParam("hrRating") int hrRating,
			@RequestParam("techRating") int techRating) throws CandidateNotFoundException, InterviewNotFoundException {
		return new ResponseEntity<Candidate>(
				panelMemberService.declareRatings(candidateId, interviewId, hrRating, techRating), HttpStatus.OK);
	}
	
	@GetMapping("/getAllPanelMembers")
	public ResponseEntity<List<PanelMember>> getAllPanelMembers() {
		return new ResponseEntity<List<PanelMember>>(panelMemberService.getAllPanelMembers(), HttpStatus.OK);
	}
	
	@DeleteMapping("/deletePanelMember/{employeeId}")
	public ResponseEntity<String> deletePanelMember(@PathVariable int employeeId) throws PanelMemberNotFoundException {
		return new ResponseEntity<String>(panelMemberService.deletePanelMember(employeeId),HttpStatus.OK);
	}
	
	@PostMapping("/addFeedback/{candidateId}/{interviewId}")
	public ResponseEntity<Feedback> addFeedback(@PathVariable int candidateId, @PathVariable int interviewId,int hrRating,int techRating) throws InterviewNotFoundException, CandidateNotFoundException {
		return new ResponseEntity<Feedback>(panelMemberService.addFeedback(candidateId,interviewId,hrRating,techRating), HttpStatus.CREATED);
	}
	
	@GetMapping("/getFeedback")
	public ResponseEntity<List<Feedback>> getFeedbacks(){
		return new ResponseEntity<List<Feedback>>(panelMemberService.getFeedbacks(), HttpStatus.OK);
	}
	
	@PutMapping("/finalStatus/{feedbackId}")
	public ResponseEntity<Feedback> finalReview(@PathVariable int feedbackId){
		return new ResponseEntity<Feedback>(panelMemberService.finalReview(feedbackId), HttpStatus.OK);
	}
	
	
}