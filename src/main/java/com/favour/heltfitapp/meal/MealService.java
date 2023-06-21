package com.favour.heltfitapp.meal;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.favour.heltfitapp.exercise.Exercise;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MealService {
    private final MealRepository meals;

    public void saveMeal(Meal meal) {
        meals.save(meal);
    }

    public Meal getMeal(String id){
        UUID mealId = UUID.fromString(id);
        return meals.findById(mealId).orElse(new Meal());
    }
}
