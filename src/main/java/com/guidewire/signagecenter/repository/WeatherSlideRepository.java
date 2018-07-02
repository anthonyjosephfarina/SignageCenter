package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.slide.WeatherSlide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherSlideRepository extends JpaRepository<WeatherSlide, Long> {
}
