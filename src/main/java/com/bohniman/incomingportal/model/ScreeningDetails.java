package com.bohniman.incomingportal.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ScreeningDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String screeningCenterName;

    private String screeningDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_journey")
    private Journey journey;
}