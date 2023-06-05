package com.favour.heltfitapp.meal;

import org.springframework.stereotype.Service;

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
}
