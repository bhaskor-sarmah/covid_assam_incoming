package com.bohniman.incomingportal.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "master_district")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterDistrict {

    @Id
    private String districtCode;

    private String districtName;
    private String isActive;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_state")
    private MasterState state;

    @JsonIgnore
    @OneToMany(mappedBy = "district")
    private List<MasterSubDistrict> subDistricts = new ArrayList<>();

    @Override
    public String toString() {
        return "{" + " districtCode='" + getDistrictCode() + "'" + ", districtName='" + getDistrictName() + "'"
                + ", isActive='" + getIsActive() + "'}";
    }

}