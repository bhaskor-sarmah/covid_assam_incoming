package com.bohniman.incomingportal.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.bohniman.incomingportal.model.Citizen;
import com.bohniman.incomingportal.model.Journey;
import com.bohniman.incomingportal.service.CitizenService;
import com.bohniman.incomingportal.service.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/citizen")
public class CitizenController {

    @Autowired
    CitizenService citizenService;

    @Autowired
    CommonService commonService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

    @GetMapping(value = { "/home" })
    public ModelAndView home(ModelAndView mv, HttpSession session) {
        if (session.getAttribute("mobile") == null) {
            mv = new ModelAndView("unlogged/index");
            mv.addObject("msgErr", "Some error ocurred ! Please try again.");
            return mv;
        }
        Citizen citizen = (Citizen) session.getAttribute("citizen");
        mv = new ModelAndView("citizen/index");
        if (citizen != null) {
            mv.addObject("citizen", citizen);
        }
        return mv;
    }

    @GetMapping(value = { "/newRequest" })
    public ModelAndView getNewRequest(HttpSession session, ModelAndView mv) {
        if (session.getAttribute("citizen") == null) {
            mv = new ModelAndView("unlogged/index");
            mv.addObject("msgErr", "Some error ocurred ! Please try again.");
            return mv;
        }
        mv = new ModelAndView("citizen/register");
        mv.addObject("journey", new Journey());
        mv.addObject("distList", commonService.getDistrictByState("18"));
        return mv;
    }

    @PostMapping(value = { "/saveRequest" })
    public ModelAndView postNewRequest(HttpSession session, ModelAndView mv,
            @Valid @ModelAttribute("journey") Journey journey, BindingResult result) {
        if (session.getAttribute("citizen") == null) {
            mv = new ModelAndView("unlogged/index");
            mv.addObject("msgErr", "Some error ocurred ! Please try again.");
            return mv;
        }
        if (result.hasErrors()) {
            mv = new ModelAndView("citizen/register");
            mv.addObject("journey", journey);
            mv.addObject("distList", commonService.getDistrictByState("18"));
            mv.addObject("msgErr", "Some error ocurred ! Please try again.");
            return mv;
        }
        Citizen citizen = (Citizen) session.getAttribute("citizen");
        citizen = citizenService.getByMobileNo(citizen.getMobile());
        if (citizen != null) {
            journey.setCitizen(citizen);
            List<Journey> old = citizenService.findJourney(journey);
            if (old == null || old.isEmpty()) {
                if (citizenService.saveJourney(journey) != null) {
                    // mv = new ModelAndView("citizen/index");
                    // if (citizen != null) {
                    // mv.addObject("citizen", citizen);
                    // }
                    // mv.addObject("successMsg", "New Application Submitted Successfully !");
                    // return mv;

                    mv = new ModelAndView("citizen/report");
                    mv.addObject("journeyList", citizenService.getAllJourneyList(citizen));
                    mv.addObject("successMsg", "New Application Submitted Successfully !");
                    return mv;
                } else {
                    mv = new ModelAndView("citizen/register");
                    mv.addObject("journey", journey);
                    mv.addObject("distList", commonService.getDistrictByState("18"));
                    mv.addObject("msgErr", "Error while saving details. Please try again!");
                    return mv;
                }
            } else {
                mv = new ModelAndView("citizen/register");
                mv.addObject("journey", journey);
                mv.addObject("distList", commonService.getDistrictByState("18"));
                mv.addObject("msgErr", "Passenger with same name, flight no, date already saved.");
                return mv;
            }
        } else {
            mv = new ModelAndView("citizen/register");
            mv.addObject("journey", journey);
            mv.addObject("distList", commonService.getDistrictByState("18"));
            mv.addObject("msgErr", "Some error ocurred ! Please try again.");
            return mv;
        }

    }

    @GetMapping(value = { "/getApplications" })
    public ModelAndView getApplications(HttpSession session, ModelAndView mv) {
        if (session.getAttribute("citizen") == null) {
            mv = new ModelAndView("unlogged/index");
            mv.addObject("msgErr", "Some error ocurred ! Please try again.");
            return mv;
        }
        Citizen citizen = (Citizen) session.getAttribute("citizen");
        citizen = citizenService.getByMobileNo(citizen.getMobile());
        if (citizen != null) {
            mv = new ModelAndView("citizen/report");
            mv.addObject("journeyList", citizenService.getAllJourneyList(citizen));
            return mv;
        }
        mv = new ModelAndView("unlogged/index");
        mv.addObject("msgErr", "Some error ocurred ! Please try again.");
        return mv;
    }

    @PostMapping(value = { "/downloadPass.pdf" }, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadPass(HttpSession session, @RequestParam("id") Long id) {
        boolean res = true;
        if (session.getAttribute("citizen") == null) {
            res = false;
        }
        Journey journey = citizenService.getJourneyById(id);
        if (journey == null) {
            res = false;
        }
        ByteArrayInputStream bis = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            if (res) {
                bis = citizenService.generatePassPdf(journey);
                headers.add("Content-Disposition", "inline;filename=ePassAssam.pdf");
                headers.setContentDispositionFormData("Content-Disposition", "ePassAssam.pdf");
                return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(bis));
            } else {
                bis = citizenService.generateErrorPdf("Requested Application Not Found");
                headers.add("Content-Disposition", "inline;filename=error.pdf");
                headers.setContentDispositionFormData("Content-Disposition", "ePassAssam.pdf");
                return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(bis));
            }
        } catch (Exception e) {
            bis = citizenService.generateErrorPdf("Internal Error");
            headers.add("Content-Disposition", "inline; filename=error.pdf");
            headers.setContentDispositionFormData("Content-Disposition", "ePassAssam.pdf");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        }
    }

    @ResponseBody
    @GetMapping(value = { "/get-screening-center/{districtCode}" })
    public ResponseEntity<Object> getScreeningCenter(@PathVariable("districtCode") String districtCode) {
        return ResponseEntity.ok(citizenService.getScreeningCenter(districtCode));
    }

}