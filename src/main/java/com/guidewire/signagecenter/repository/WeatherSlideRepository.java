package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.slide.WeatherSlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherSlideRepository extends JpaRepository<WeatherSlideEntity, Long> {
}
