package com.favour.heltfitapp.appuser;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.favour.heltfitapp.exercise.Exercise;
import com.favour.heltfitapp.exercise.ExerciseService;
import com.favour.heltfitapp.meal.Meal;
import com.favour.heltfitapp.meal.MealService;
import com.favour.heltfitapp.plan.Plan;
import com.favour.heltfitapp.plan.PlanService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GenericController {
    private final RegistrationService registrationService;
    private final PlanService planService;
    private final MealService mealService;
    private final ExerciseService exerciseService;

    @GetMapping("/")
    public String landing(Model model) {
        model.addAttribute("serverTime", LocalDate.now());
        return "landing";
    }

    @GetMapping("/signup")
    public String signup(Model model, final Registration regForm) {
        return "signup";
    }

    @PostMapping("/register")
    public String onboarding(final Registration registration) {
        return registrationService.signupUser(registration);
    }

    @GetMapping("/onboarding")
    public String onboarding() {
        return "onboarding";
    }

    @GetMapping("/plan")
    public String plan(Model model, Principal principal) {
        model.addAttribute("activePlan", planService.getActivePlan(principal.getName()));
        return "plan";
    }

    @GetMapping("/planboard")
    public String planboard(Model model, final Plan plan, final Meal meal, final Exercise exercise, Principal principal) {
        List<Plan> userPlans = planService.getAllPlansByUser(principal.getName());
        model.addAttribute("userPlans", userPlans);
        return "planboard";
    }

    @PostMapping("/createPlan")
    public String createPlan(Model model, final Plan plan) {
        planService.savePlan(plan.getPlanname());
        return "redirect:/planboard";
    }

    @PostMapping("/saveMeal")
    public String saveMeal(final Meal meal) {
        mealService.saveMeal(meal);
        return "redirect:/planboard";
    }

    @PostMapping("/saveExercise")
    public String saveExercise(final Exercise exercise) {
        exerciseService.saveExercise(exercise);
        return "redirect:/planboard";
    }

    @GetMapping("/features")
    public String features() {
        return "planfeatures";
    }

    @PostMapping("/activatePlan")
    public String activatePlan(final Plan plan, Principal principal) {
        planService.activatePlan(plan, principal.getName());
        return "redirect:/plan";
    }

    
}
