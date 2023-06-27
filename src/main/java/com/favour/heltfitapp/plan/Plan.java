package com.favour.heltfitapp.plan;

import java.util.UUID;

import com.favour.heltfitapp.appuser.AppUser;

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
@Table(name = "plan")
@Getter
@Setter
@NoArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID planid;
    @ManyToOne
    @JoinColumn(name = "userid")
    private AppUser appUser;
    @Column(nullable = false)
    private String planname;
    @Column(nullable = false)
    private String plancateogry;
    private Boolean active;
    /**
     * @param appUser
     * @param planname
     * @param plancateogry
     * @param active
     */
    public Plan(AppUser appUser, String planname, String plancateogry, Boolean active) {
        this.appUser = appUser;
        this.planname = planname;
        this.plancateogry = plancateogry;
        this.active = active;
    }

    public Plan(UUID planid, AppUser appUser, String planname, String plancateogry, Boolean active) {
        this.planid = planid;
        this.appUser = appUser;
        this.planname = planname;
        this.plancateogry = plancateogry;
        this.active = active;
    }

    
}
