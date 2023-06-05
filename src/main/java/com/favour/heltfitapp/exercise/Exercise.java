package com.favour.heltfitapp.exercise;

import java.util.UUID;

import com.favour.heltfitapp.appuser.AppUser;
import com.favour.heltfitapp.plan.Plan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exercise")
@Getter
@Setter
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private UUID exerciseplanid;
    @Column(nullable = false, unique = true)
    private String exercisename;
    @Column(nullable = false)
    private String exercisetime;
    private String exercisedesc;
    /**
     * @param planid
     * @param name
     * @param time
     * @param description
     */
    public Exercise(UUID planid, String name, String time, String description) {
        this.exerciseplanid = planid;
        this.exercisename = name;
        this.exercisetime = time;
        this.exercisedesc = description;
    }
}
