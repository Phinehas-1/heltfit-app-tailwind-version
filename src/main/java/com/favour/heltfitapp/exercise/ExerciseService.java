package com.favour.heltfitapp.exercise;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.favour.heltfitapp.plan.PlanService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exercises;

    @Transactional
    public void saveExercise(Exercise exercise){
        exercises.save(exercise);
        log.info("{} saved.", exercise);
    }

    public Exercise getExercise(String id){
        UUID exerciseId = UUID.fromString(id);
        return exercises.findById(exerciseId).orElse(new Exercise());
    }
}
