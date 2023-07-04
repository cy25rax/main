package com.java.services;

import com.java.DTO.AdvertisementDto;
import com.java.models.Advertisement;
import com.java.models.Category;
import com.java.models.User;
import com.java.repositoryes.AdvetisementRepository;
import com.java.repositoryes.CategoryRepository;
import com.java.repositoryes.specifications.AdvertisementSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementService {
    private final AdvetisementRepository advertisementRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    
    public Page<Advertisement> findAll(Specification<Advertisement> spec, int page) {
        return advertisementRepository.findAll(spec, PageRequest.of(page, 5));
    }

    public Optional<Advertisement> findById(Long id) {
        return advertisementRepository.findById(id);
    }

    public void deleteById(Long id) {
        advertisementRepository.deleteById(id);
    }

    public Advertisement createNewProduct(AdvertisementDto advertisementDto, String username) {
        Advertisement advertisement = new Advertisement();
        advertisement.setPrice(advertisementDto.getPrice());
        advertisement.setTitle(advertisementDto.getTitle());
        advertisement.setUser(userService.findUser(username));
        Category category = null;
        try {
            category = categoryRepository.findByTitle(advertisementDto.getCategoryTitle()).orElseThrow(() -> new ExpressionException("Category not found"));
        } catch (ExpressionException e) {
        }
        if (category == null) {
            category = new Category();
            category.setTitle(advertisementDto.getCategoryTitle());
            categoryRepository.save(category);
            System.out.println(categoryRepository.findByTitle(advertisementDto.getCategoryTitle()));
            advertisement.setCategory(category);
        }
        advertisementRepository.save(advertisement);
        return advertisement;
    }

    public Specification<Advertisement> createSpecByFilters(Integer minPrice, Integer maxPrice, String title) {
        Specification<Advertisement> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(AdvertisementSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(AdvertisementSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (title != null) {
            spec = spec.and(AdvertisementSpecifications.titleLike(title));
        }
        return spec;
    }
}
