package com.bohniman.incomingportal.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MasterThana {
    @Id
    private String thanaCode;
    private String districtName;
    private String thanaName;
    private String districtCode;
}