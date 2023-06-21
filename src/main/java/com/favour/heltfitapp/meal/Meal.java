package com.favour.heltfitapp.meal;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meal")
@Getter
@Setter
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private UUID mealplanid;
    @Column(nullable = false)
    private String mealname;
    @Column(nullable = false)
    private String mealtime;
    private String mealdesc;
    /**
     * @param planid
     * @param name
     * @param time
     * @param description
     */
    public Meal(UUID planid, String name, String time, String description) {
        this.mealplanid = planid;
        this.mealname = name;
        this.mealtime = time;
        this.mealdesc = description;
    }

    
}
