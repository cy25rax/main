package com.java.services;

import com.java.models.Advertisement;
import com.java.models.Feedback;
import com.java.models.User;
import com.java.repositoryes.FeedbackRepository;
import com.java.repositoryes.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
	
	private final FeedbackRepository feedbackRepository;
	private final AdvertisementService advertisementService;
	private final UserRepository userRepository;
	
	
	public void createFeedback(Long id, String feedbackText) {
		Advertisement advertisement = advertisementService.findById(id).orElse(null);
		assert advertisement != null;
		User user = advertisement.getUser();
		Feedback feedback = new Feedback();
		feedback.setUserId(user.getId());
		feedback.setText(feedbackText);
		feedbackRepository.save(feedback);
	}
	
	public List<Feedback> getFeedbackList(String username) {
		User user = userRepository.findByUsername(username);
		return feedbackRepository.findByUserId(user.getId());
	}
}
