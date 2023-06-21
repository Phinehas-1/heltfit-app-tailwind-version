package com.favour.heltfitapp.plan;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.favour.heltfitapp.appuser.AppUser;
import com.favour.heltfitapp.appuser.AppUserRepository;
import com.favour.heltfitapp.exercise.Exercise;
import com.favour.heltfitapp.exercise.ExerciseRepository;
import com.favour.heltfitapp.meal.Meal;
import com.favour.heltfitapp.meal.MealRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository plans;
    // private final AppUserService appUserService;
    private final AppUserRepository appUsers;

    private final MealRepository meals;
    private final ExerciseRepository exercises;

    @Transactional
    public Object savePlan(String planName) {
        try {
            return plans.save(createPlan(planName));
        } catch (Exception e) {
            log.error("createPlan() : {}", e.getCause());
            throw new IllegalStateException("Create plan failed.");
        }
    }

    @Transactional
    private Plan createPlan(String planName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser appUser = appUsers.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not not found", null));
        Plan plan = new Plan(appUser, planName, "custom", false);
        return plan;
    }

    @Transactional
    public List<Plan> getAllPlansByUser(String username) {
        AppUser appUser = appUsers.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not not found", null));
        return plans.findAllByAppUser(appUser).orElse(List.of(new Plan()));
    }

    @Transactional
    public PlanInfo getActivePlan(String username) {
        Plan plan = getAllPlansByUser(username).stream().filter(userPlan -> userPlan.getActive().equals(true))
                .findFirst()
                .orElse(new Plan());
        List<Meal> allMeals = meals.findAllByMealplanid(plan.getPlanid()).orElse(Arrays.asList(new Meal()));
        List<Exercise> allExercises = exercises.findAllByExerciseplanid(plan.getPlanid())
                .orElse(List.of(new Exercise()));

        return new PlanInfo(plan, allMeals, allExercises);
    }

    @Transactional
    public PlanInfo createRecommendedPlanForUser(String username) {
        AppUser user = appUsers.findByUsername(username).orElseThrow();
        String suggestedPlanName = suggestPlanForUser(user);
        Plan plan = getAllPlansByUser("admin").stream()
                .filter(userPlan -> userPlan.getPlanname().equals(suggestedPlanName)).findFirst()
                .orElse(new Plan());
        List<Meal> allMeals = meals.findAllByMealplanid(plan.getPlanid()).orElse(Arrays.asList(new Meal()));
        List<Exercise> allExercises = exercises.findAllByExerciseplanid(plan.getPlanid())
                .orElse(List.of(new Exercise()));

        Plan recommendedPlan = plans.save(new Plan(user, user.getUsername(), "suggested", true));
        log.info("recommended plan id is {}", recommendedPlan.getPlanid());
        allMeals.stream().forEach(meal -> {
            log.info("meal name is {}", meal.getMealname());
            meals.save(new Meal(recommendedPlan.getPlanid(), meal.getMealname(), meal.getMealtime(), meal.getMealdesc()));
        });
        allExercises.stream().forEach(exercise -> {
            exercises.save(new Exercise(recommendedPlan.getPlanid(), exercise.getExercisename(), exercise.getExercisetime(), exercise.getExercisedesc()));
        });
        return new PlanInfo(recommendedPlan, allMeals, allExercises);

    }

    @Transactional
    public void activatePlan(Plan userPlan, String username) {
        getAllPlansByUser(username).stream().forEach(plan -> {
            plan.setActive(false);
            plans.save(plan);
        });
        Plan plan = plans.findById(userPlan.getPlanid())
                .orElseThrow(() -> new IllegalStateException("The plan id doesn't exist."));
        plan.setActive(true);
        plans.save(plan);
    }

    public String suggestPlanForUser(AppUser user) {
        if (user.getHistory().equals("yes") && user.getAge() < 20) {
            return "ulcer-1";
        }
        if (user.getAge() > 20 && user.getAge() < 60) {
            return "ulcer-2";
        } else {
            return "ulcer-3";
        }
    }
}
