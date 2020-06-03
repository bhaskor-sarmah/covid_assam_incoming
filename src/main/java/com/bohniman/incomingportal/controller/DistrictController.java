package com.bohniman.incomingportal.controller;

import java.io.Console;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.bohniman.incomingportal.model.UserScope;
// import com.bohniman.incomingportal.payload.JourneyFormStatusCount;
// import com.bohniman.incomingportal.payload.JourneyRequestReport;
// import com.bohniman.incomingportal.payload.ReportFilter;
// import com.bohniman.incomingportal.payload.RequestReport;
// import com.bohniman.incomingportal.payload.StateWiseReport;
// import com.bohniman.incomingportal.payload.SubDistrictCount;
import com.bohniman.incomingportal.security.MyUserDetails;
// import com.bohniman.incomingportal.services.DistrictService;
import com.bohniman.incomingportal.utils.AppStaticData;
import com.bohniman.incomingportal.utils.DateUtil;
import com.bohniman.incomingportal.utils.LoggedInUser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/district")
public class DistrictController {

}