package com.bohniman.incomingportal.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bohniman.incomingportal.captcha.CaptchaGenerator;
import com.bohniman.incomingportal.model.Citizen;
import com.bohniman.incomingportal.service.CitizenService;
import com.bohniman.incomingportal.service.CommonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import nl.captcha.Captcha;

// import nl.captcha.Captcha;

/**
 * IndexController
 */
@Controller
public class IndexController {

	// private static Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	CitizenService citizenService;

	@Autowired
	CommonService commonService;

	@Autowired
	CaptchaGenerator captchaGenerator;

	@GetMapping(value = { "/", "/login" })
	public ModelAndView index(ServletRequest request, ModelAndView mv) {
		mv = new ModelAndView("unlogged/index");
		return mv;
	}

	@GetMapping(value = { "/ResendOtp" })
	public ModelAndView resendOtp(HttpSession session, ModelAndView mv) {
		Citizen citizen = (Citizen) session.getAttribute("citizen");
		if (commonService.fireOtp(citizen, session)) {
			mv = new ModelAndView("unlogged/otp");
			mv.addObject("msg", "OTP sent successfully ! Please enter the OTP to continue.");
			mv.addObject("mobile", citizen.getMobile());
			return mv;
		} else {
			mv = new ModelAndView("unlogged/index");
			mv.addObject("msgErr", "Failed Sending OTP ! Please try again");
			return mv;
		}
	}

	@PostMapping(value = { "/otp" })
	public ModelAndView getOtp(HttpSession session, ModelAndView mv, @RequestParam("otp") String otp,
			@RequestParam("mobile") String mobile) {
		if (session.getAttribute("citizen") == null) {
			mv = new ModelAndView("unlogged/index");
			// mv.addObject("register", new Citizen());
			mv.addObject("msgErr", "Some Error has Ocurred ! Please try again");
			return mv;
		}
		Citizen citizen = (Citizen) session.getAttribute("citizen");
		if (citizen == null) {
			mv = new ModelAndView("unlogged/index");
			// mv.addObject("register", new Citizen());
			mv.addObject("msgErr", "Some Error has Ocurred ! Please try again");
			return mv;
		}
		// System.out.println(citizen.getMobile() + " = " + mobile);
		// System.out.println(citizen.getOtp() + " = " + otp);
		if (citizen.getOtp().equals(otp) && citizen.getMobile().equals(mobile)) {
			citizen.setIsOtpValidated(true);
			citizen = citizenService.saveCitizen(citizen);
			if (citizen != null) {
				mv = new ModelAndView("unlogged/register");
				session.setAttribute("citizen", citizen);
				return mv;
			} else {
				mv = new ModelAndView("unlogged/index");
				mv.addObject("msgErr", "Registration Error ! Please try again.");
				return mv;
			}
		} else {
			mv = new ModelAndView("unlogged/otp");
			mv.addObject("msgErr", "Invalid Otp ! Please try again.");
			mv.addObject("mobile", citizen.getMobile());
			return mv;
		}
	}

	@GetMapping(value = { "/access-denied" })
	public ModelAndView accessDenied(ModelAndView mv) {
		mv = new ModelAndView("error");
		return mv;
	}

	@GetMapping(value = { "/no-role" })
	public ModelAndView noRole(ModelAndView mv) {
		mv = new ModelAndView("unlogged/index");
		// mv.addObject("register", new Citizen());
		mv.addObject("msgErr", "Unauthorised ! Please try again.");
		return mv;
	}

	@GetMapping(path = "/genCaptcha.png", produces = "image/png") // Map ONLY GET Requests
	@ResponseBody
	public byte[] genCaptcha(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

		Captcha captcha = captchaGenerator.createCaptcha(170, 50);
		httpSession.setAttribute("captcha", captcha);

		// request.setAttribute("answer", captcha.getAnswer());
		// log.trace("Captcha answer : " + captcha.getAnswer());

		response.setContentType("image/png");

		ByteArrayOutputStream bao = new ByteArrayOutputStream();

		try {

			ImageIO.write(captcha.getImage(), "png", bao);
			return bao.toByteArray();

		} catch (IOException e) {

		}

		return null;
	}

	@GetMapping(path = "/genCaptchaRegister.png", produces = "image/png") // Map ONLY GET Requests
	@ResponseBody
	public byte[] genCaptchaLogin(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

		Captcha captcha = captchaGenerator.createCaptcha(170, 50);
		httpSession.setAttribute("captchaRegister", captcha);

		// request.setAttribute("answer", captcha.getAnswer());
		// log.trace("Captcha answer : " + captcha.getAnswer());

		response.setContentType("image/png");

		ByteArrayOutputStream bao = new ByteArrayOutputStream();

		try {

			ImageIO.write(captcha.getImage(), "png", bao);
			return bao.toByteArray();

		} catch (IOException e) {

		}

		return null;
	}
}