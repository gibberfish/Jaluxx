package com.mindbadger.jaluxx.spring.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mindbadger.jaluxx.JaluxxException;
import com.mindbadger.jaluxx.Player;
import com.mindbadger.jaluxx.gamemanager.GameManager;

@SessionAttributes({"player"})
@Controller
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	GameManager gameManager;

	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public @ResponseBody String login(@ModelAttribute("player") Player player, Model model) {

		logger.debug("CONTROLLER: login");

		logger.debug("Name: " + player.getName());
		logger.debug("Password: " + player.getPassword());

		try {
			player = gameManager.registerNewPlayer(player);
			//request.getSession().setAttribute("player", player);
			model.addAttribute("player", player);
			return "OK";
		} catch (JaluxxException e) {
			logger.debug("Login error: " + e.getMessage());
			return e.getMessage();
		}
	}

}
