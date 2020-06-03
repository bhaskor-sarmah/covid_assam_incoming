package com.bohniman.incomingportal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "master_state")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
public class MasterState {

    @Id
    private String stateCode;
    private String stateName;
    private String isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<MasterDistrict> districts = new ArrayList<>();

    public MasterState() {
    }

    public MasterState(String stateCode, String stateName, String isActive, List<MasterDistrict> districts) {
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.isActive = isActive;
        this.districts = districts;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return this.stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getIsActive() {
        return this.isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public List<MasterDistrict> getDistricts() {
        return this.districts;
    }

    public void setDistricts(List<MasterDistrict> districts) {
        this.districts = districts;
    }

    public MasterState stateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public MasterState stateName(String stateName) {
        this.stateName = stateName;
        return this;
    }

    public MasterState isActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public MasterState districts(List<MasterDistrict> districts) {
        this.districts = districts;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MasterState)) {
            return false;
        }
        MasterState masterState = (MasterState) o;
        return Objects.equals(stateCode, masterState.stateCode) && Objects.equals(stateName, masterState.stateName)
                && Objects.equals(isActive, masterState.isActive) && Objects.equals(districts, masterState.districts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateCode, stateName, isActive, districts);
    }

    @Override
    public String toString() {
        return "{}";
    }

}