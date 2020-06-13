package com.bohniman.incomingportal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Journey extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Flight Number is required")
    private String flightNo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotEmpty(message = "Travel Date is Required")
    private Date dateOfTravel;

    @NotEmpty(message = "PNR Number is required")
    private String pnrNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_district")
    private MasterDistrict district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_citizen")
    private Citizen citizen;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfArrival;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfScreening;

    private String address;

    private String flat_house_no;
    private String street;
    private String thana;
    private String landmark;

    private String pincode;

    private boolean isArrivedAtAirport;

    // @Lob
    // private byte[] document;

    private String docPath;

    private String documentNumber;
    private String documentType;

    @JsonIgnore
    @OneToMany(mappedBy = "journey")
    List<ScreeningDetails> screeningDetails;

}