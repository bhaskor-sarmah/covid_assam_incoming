package com.bohniman.incomingportal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ScanLocation Date : 11-05-2020
 * 
 * @author Sangram Singha
 */
@Entity
@Table(name = "screening_location")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "location", length = 512)
    private String location;

    @Column(name = "is_enable", nullable = false, columnDefinition = "tinyint(1) default 1")
    private Boolean enable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_district")
    MasterDistrict district;

}