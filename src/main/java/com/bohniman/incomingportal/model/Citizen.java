package com.bohniman.incomingportal.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String otp;

    private boolean isOtpValidated;

    @Pattern(regexp = "[\\d]{10}", message = "Please enter a valid Mobile No")
    private String mobile;

    public void setIsOtpValidated(boolean b) {
        this.isOtpValidated = b;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "citizen")
    List<Journey> journeyDetails;
}