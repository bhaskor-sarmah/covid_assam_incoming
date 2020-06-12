package com.bohniman.incomingportal.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.bohniman.incomingportal.model.Citizen;
import com.bohniman.incomingportal.model.MasterDistrict;
import com.bohniman.incomingportal.repository.MasterDistrictRepository;
import com.bohniman.incomingportal.repository.UserRepository;
import com.bohniman.incomingportal.utils.FireSms;
import com.bohniman.incomingportal.utils.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MasterDistrictRepository masterDistrictRepository;

    public boolean fireOtp(Citizen citizen, HttpSession session) {
        String otp = RandomString.randomNumber(4);
        System.out.println(otp);
        if (FireSms.doFireSMS(citizen.getMobile(), otp)) {
            citizen.setOtp(otp);
            session.setAttribute("otp", otp);
            session.setAttribute("citizen", citizen);
            return true;
        }
        return false;
    }

    public boolean checkIfMobileExist(String mobile) {
        return userRepository.findByUsername(mobile).isPresent();
    }

    public List<MasterDistrict> getDistrictByState(String stateCode) {
        return masterDistrictRepository.findAllByState_stateCodeOrderByDistrictNameAsc(stateCode);
    }

}