package com.favour.heltfitapp;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.favour.heltfitapp.appuser.AppUser;
import com.favour.heltfitapp.appuser.AppUserRepository;
import com.favour.heltfitapp.plan.Plan;
import com.favour.heltfitapp.plan.PlanRepository;

@SpringBootApplication
public class HeltfitAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeltfitAppApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	PlanRepository plans;

	@Bean
	@Transactional
	CommandLineRunner commandLineRunner(AppUserRepository appUsers) {
		String password = passwordEncoder.encode("cantguessever");
		return args -> {

			AppUser admin = appUsers.findByUsername("admin").orElse(new AppUser());
			Plan ulcer1 = new Plan();
			Plan ulcer2 = new Plan();
			Plan ulcer3 = new Plan();

			if (admin.getUsername() == null) {
				admin = new AppUser("admin", password, null, null, null, null);
				appUsers.save(admin);
			}

			List<Plan> adminPlans = plans.findAllByAppUser(admin).orElse(Arrays.asList(new Plan()));
			if (adminPlans.size() < 3) {
				ulcer1 = plans.findByPlanname("ulcer-1").orElse(new Plan(admin, "ulcer-1",
						"suggested", true));
				ulcer2 = plans.findByPlanname("ulcer-2").orElse(new Plan(admin, "ulcer-2",
						"suggested", true));
				ulcer3 = plans.findByPlanname("ulcer-3").orElse(new Plan(admin, "ulcer-3",
						"suggested", true));

				plans.saveAll(Arrays.asList(ulcer1, ulcer2, ulcer3));
			}

		};
	}

}
