package com.java.controllers;

import com.java.DTO.AdvertisementDto;
import com.java.converters.AdvertisementConverter;
import com.java.models.Advertisement;
import com.java.services.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/advertisement")
@RequiredArgsConstructor
public class AdvertisementController {
	private final AdvertisementService advertisementService;
	private final AdvertisementConverter advertisementConverter;
	
	@GetMapping
	public List<AdvertisementDto> findAdvetisement(
			@RequestParam(required = false, name = "min_price") Integer minPrice,
			@RequestParam(required = false, name = "max_price") Integer maxPrice,
			@RequestParam(required = false, name = "title") String title,
			@RequestParam(defaultValue = "1", name = "p") Integer page) {
		if (page < 1) {
			page = 1;
		}
		Specification<Advertisement> spec = advertisementService.createSpecByFilters(minPrice, maxPrice, title);
		return advertisementService.findAll(spec, page - 1).map(advertisementConverter::entityToDto).getContent();
	}
	
	@GetMapping("/{id}")
	public AdvertisementDto findProductById(@PathVariable Long id) {
		Advertisement p = advertisementService.findById(id).orElse(null);
		return advertisementConverter.entityToDto(p);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProductById(@PathVariable Long id) {
		advertisementService.deleteById(id);
	}
}
