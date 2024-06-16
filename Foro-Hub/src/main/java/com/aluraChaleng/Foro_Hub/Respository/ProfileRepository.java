package com.aluraChaleng.Foro_Hub.Respository;

import com.aluraChaleng.Foro_Hub.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}