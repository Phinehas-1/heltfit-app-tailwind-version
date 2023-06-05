package com.favour.heltfitapp.exercise;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID>{
    public Optional<List<Exercise>>findAllByExerciseplanid(UUID exerciseplanid);
}
