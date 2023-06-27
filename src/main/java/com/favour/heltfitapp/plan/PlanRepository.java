package com.favour.heltfitapp.plan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.favour.heltfitapp.appuser.AppUser;

public interface PlanRepository extends JpaRepository<Plan, UUID>{
    Optional<List<Plan>> findAllByAppUser(AppUser appUser);

    Optional<Plan> findByActive(Boolean active);
    
    Optional<Plan> findByPlanname(String planname);
}
