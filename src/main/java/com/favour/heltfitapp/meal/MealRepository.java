package com.favour.heltfitapp.meal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, UUID>{
    public Optional<List<Meal>> findAllByMealplanid(UUID mealplanid);
}
