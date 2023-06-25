package com.java.avito.converters;

import com.java.avito.DTO.AdvertisementDto;
import com.java.avito.models.Advertisement;
import com.java.avito.models.Category;
import com.java.avito.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertisementConverter {
    private final CategoryService categoryService;

    public AdvertisementDto entityToDto(Advertisement advertisement) {
        return new AdvertisementDto(advertisement.getId(),
                advertisement.getTitle(),
                advertisement.getPrice());
//                advertisement.getCategory().getTitle());
    }

    public Advertisement dtoToEntity(AdvertisementDto advertisementDto) {
        Advertisement p = new Advertisement();
        p.setId(advertisementDto.getId());
        p.setTitle(advertisementDto.getTitle());
        p.setPrice(advertisementDto.getPrice());
        Category c = categoryService.findByTitle(advertisementDto.getCategoryTitle()).orElse(null);
//        p.setCategory(c);
        return p;
    }
}
