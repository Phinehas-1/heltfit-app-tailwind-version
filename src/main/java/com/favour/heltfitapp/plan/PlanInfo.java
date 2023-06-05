package com.favour.heltfitapp.plan;

import java.util.List;

import com.favour.heltfitapp.exercise.Exercise;
import com.favour.heltfitapp.meal.Meal;

public record PlanInfo(Plan plan, List<Meal> meals, List<Exercise> exercises) {
}
