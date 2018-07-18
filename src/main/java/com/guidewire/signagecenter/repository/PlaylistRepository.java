package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
}
