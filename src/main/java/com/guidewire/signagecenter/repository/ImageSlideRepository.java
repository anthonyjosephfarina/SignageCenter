package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.slide.ImageSlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageSlideRepository extends JpaRepository<ImageSlideEntity, Long> {
}
