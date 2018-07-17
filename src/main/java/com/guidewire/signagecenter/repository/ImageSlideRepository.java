package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.slide.ImageSlide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageSlideRepository extends JpaRepository<ImageSlide, Long> {
}
