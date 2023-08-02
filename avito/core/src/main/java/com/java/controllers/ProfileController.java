package com.java.controllers;

import com.java.DTO.AdvertisementDto;
import com.java.converters.AdvertisementConverter;
import com.java.models.Feedback;
import com.java.services.AdvertisementService;
import com.java.services.FeedbackService;
import com.java.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
	
	private final AdvertisementService advertisementService;
	private final ProfileService profileService;
	private final AdvertisementConverter advertisementConverter;
	
	private final FeedbackService feedbackService;
	
	
	@GetMapping
	public List<AdvertisementDto> getProfile(@RequestHeader(name = "username", required = false) String username) {
		return profileService.getProfile(username).stream()
					   .map(advertisementConverter::entityToDto).toList();
	}
	
	@PostMapping("/add")
	public String addAdvertisement(@RequestBody AdvertisementDto advertisementDto,
								 @RequestHeader(name = "username") String username) {
		advertisementService.createNewProduct(advertisementDto, username);
		return "ok";
	}
	
	@GetMapping("/feedbackList")
	public List<Feedback> getFeedbackList(@RequestHeader(name = "username", required = false) String username) {
		return feedbackService.getFeedbackList(username);
	}
}
