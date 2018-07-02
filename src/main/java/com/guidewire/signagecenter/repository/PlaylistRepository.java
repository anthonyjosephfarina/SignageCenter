package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
