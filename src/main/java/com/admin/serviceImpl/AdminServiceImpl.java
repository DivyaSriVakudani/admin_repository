package com.admin.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dto.CandidateDto;
import com.admin.dto.InterviewDto;
import com.admin.entity.Candidate;
import com.admin.entity.Feedback;
import com.admin.entity.Interview;
import com.admin.entity.PanelMember;
import com.admin.exception.CandidateNotFoundException;
import com.admin.exception.InterviewNotFoundException;
import com.admin.exception.PanelMemberNotFoundException;
import com.admin.repository.CandidateRepository;
import com.admin.repository.FeedbackRepository;
import com.admin.repository.InterviewScheduleRepository;
import com.admin.repository.PanelMemberRepository;
import com.admin.service.CandidateService;
import com.admin.service.InterviewScheduleService;
import com.admin.service.PanelMemberService;

@Service
public class AdminServiceImpl implements CandidateService, PanelMemberService, InterviewScheduleService {

	@Autowired
	private CandidateRepository candidateRepo;
	
	@Autowired
	private FeedbackRepository feedbackRepo;

	@Autowired
	private InterviewScheduleRepository interviewScheduleRepo;

	@Autowired
	private PanelMemberRepository panelMemberRepo;

	@Override
	public Candidate addCandidate(CandidateDto candidateDto) {
		Candidate a = new Candidate();

		a.setCandidateName(candidateDto.getCandidateName());
		a.setDesignation(candidateDto.getDesignation());
		a.setLocation(candidateDto.getLocation());
		a.setNoticePeriod(candidateDto.getNoticePeriod());
		a.setPrimarySkill(candidateDto.getPrimarySkill());
		a.setQualification(candidateDto.getQualification());
		a.setSecondarySkill(candidateDto.getSecondarySkill());
		return candidateRepo.save(a);
	}

	@Override
	public List<Candidate> getAllCandidates() {
		// TODO Auto-generated method stub
		return candidateRepo.findAll();
	}

	@Override
	public Candidate assignInterview(int interviewId, int candidateId) {
		Optional<Interview> findById = interviewScheduleRepo.findById(interviewId);
		Optional<Candidate> findById2 = candidateRepo.findById(candidateId);
		if (findById.isPresent() && findById2.isPresent() && !findById2.get().getInterviews().contains(findById.get())) {
			Interview interview = findById.get();
			Candidate candidate = findById2.get();
			interview.getCandidates().add(candidate);
			candidate.getInterviews().add(interview);
			interviewScheduleRepo.save(interview);
			candidateRepo.save(candidate);
			return candidate;
		} else {
			throw new IllegalArgumentException("Interview or Candidate not found with provided IDs");
		}
	}

	@Override
	public Interview addInterview(InterviewDto interviewDto) {
		// TODO Auto-generated method stub
		Interview interview = new Interview();
		interview.setCompany(interviewDto.getCompany());
		interview.setSalaryPackage(interviewDto.getSalaryPackage());
		return interviewScheduleRepo.save(interview);
	}
	@Override
	public PanelMember addPanel(PanelMember panelMember) {
		return panelMemberRepo.save(panelMember);
	}
	
	@Override
	public String deleteCandidate(int candidateId) throws CandidateNotFoundException {
		Optional<Candidate> findById = candidateRepo.findById(candidateId);
		if (findById.isPresent()) {
			candidateRepo.deleteById(candidateId);
			return "candidate with id " + candidateId + " is deleted";
		} else {
			throw new CandidateNotFoundException("candidate not found!!");
		}
	}
	@Override
	public List<Interview> getAllInterviews() {
		return interviewScheduleRepo.findAll();
	}
	@Override
	public Candidate declareRatings(int candidateId, int interviewId, int hrRating, int techRating)
			throws CandidateNotFoundException, InterviewNotFoundException {
		Optional<Candidate> findById = candidateRepo.findById(candidateId);
		Interview interview = interviewScheduleRepo.findById(interviewId).get();
		if (interview == null) {
			throw new InterviewNotFoundException("interview not found!!");
		}
		if (findById.isPresent()) {
			Candidate candidate = candidateRepo.findById(candidateId).get();
			if (candidate.getInterviews().contains(interview)) {
				interview.setInterviewId(interviewId);
				interview.setHrRating(hrRating);
				interview.setTechRating(techRating);
				for (Interview interview1 : candidate.getInterviews()) {
					if (interview1.getInterviewId() == interviewId) {
						candidate.getInterviews().set(candidate.getInterviews().indexOf(interview1), interview1);
						break;
					}
				}
				candidateRepo.save(candidate);
				return candidate;
			}

		} else {
			throw new CandidateNotFoundException("candidate not found");
		}
		return null;
	}

	@Override
	public List<PanelMember> getAllPanelMembers() {
		// TODO Auto-generated method stub
		return panelMemberRepo.findAll();
	}

	@Override
	public String deletePanelMember(int employeeId) throws PanelMemberNotFoundException {
		// TODO Auto-generated method stub
		Optional<PanelMember> findById = panelMemberRepo.findById(employeeId);
		if (findById.isPresent()) {
			panelMemberRepo.deleteById(employeeId);
			return "panelMember with id " + employeeId + " is deleted";
		} else {
			throw new PanelMemberNotFoundException("Panel member not found!!");
		}
	}

	@Override
	public Feedback addFeedback(int candidateId, int interviewId, Integer hrRating, int techRating) throws InterviewNotFoundException, CandidateNotFoundException {
	    // TODO Auto-generated method stub
	    Optional<Candidate> candidateOptional = candidateRepo.findById(candidateId);
	    Optional<Interview> interviewOptional = interviewScheduleRepo.findById(interviewId);
	    Feedback feedback = new Feedback();
	    if (candidateOptional.isPresent() && interviewOptional.isPresent()) {
	        Candidate candidate = candidateOptional.get();
	        Interview interview = interviewOptional.get();
	        List<Interview> interviews = candidate.getInterviews();
	        if (interviews.contains(interview)) {
	            if (interview.getHrRating() == 0 && interview.getTechRating() == 0) {
	                feedback.setHrrating(hrRating);
	                feedback.setCandidateName(candidate.getCandidateName());
	                feedback.setTechrating(techRating);
	                feedback.setCandiateId(candidateId);
	                feedback.setInterviewId(interviewId);
	                feedback.setFinalStatus("N/A");
	                feedbackRepo.save(feedback);
	            } else {
	                throw new InterviewNotFoundException("Ratings already declared");
	            }
	        } else {
	            throw new InterviewNotFoundException("Interview not found for this candidate");
	        }
	    } else {
	        throw new CandidateNotFoundException("Candidate/Interview not found");
	    }

	    return feedback;
	}

	@Override
	public List<Feedback> getFeedbacks() {
		// TODO Auto-generated method stub
		return feedbackRepo.findAll();
	}

	@Override
	public Feedback finalReview(int feedbackId) {
		// TODO Auto-generated method stub
		Optional<Feedback> findById = feedbackRepo.findById(feedbackId);
		if(findById.isPresent()) {
			if(findById.get().getHrrating()<3 &&findById.get().getTechrating()<3) {
				findById.get().setFinalStatus("Not Selected");
				feedbackRepo.save(findById.get());
			}else {
				findById.get().setFinalStatus("Selected");
				feedbackRepo.save(findById.get());
			}
		}
		return findById.get();
	}

	

}
