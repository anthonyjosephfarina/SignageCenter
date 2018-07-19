package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.slide.WeatherSlideEntity;
import com.guidewire.signagecenter.repository.WeatherSlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * WeatherSlideService creates the new weatherslide and Retrieves the existing one
 * @author
 */
@Service
public class WeatherSlideService {
    /**
     * The Logger for WeatherSlideService.
     */
    private static final Logger logger = LoggerFactory.getLogger(WeatherSlideService.class);

    /**
     *  Inject WeatherSlideRepository.
     */
    @Autowired
    private WeatherSlideRepository weatherSlideRepository;

    /**
     *  Inject PlaylistService.
     */
    @Autowired
    private PlaylistService playlistService;

    /**
     * Creates the WeatherSlideEntity .
     * @param weatherSlide <code>WeatherSlideEntity</code>.
     * @param playlistId <code>Long</code>.
     * @return WeatherSlideEntity.
     * @throws
     */
    public WeatherSlideEntity createWeatherSlide(WeatherSlideEntity weatherSlide, Long playlistId) {

        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        weatherSlide.setPlaylistEntity(playlistEntity);

        return weatherSlideRepository.save(weatherSlide);
    }

    /**
     * Retrieves all  values from the  WeatherSlideEntity  in database.
     *  @return List<WeatherSlideEntity> .
     *  @throws
     */
    public List<WeatherSlideEntity> getAll() {
        return weatherSlideRepository.findAll();
    }
}
