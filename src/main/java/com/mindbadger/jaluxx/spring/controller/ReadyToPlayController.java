package com.mindbadger.jaluxx.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mindbadger.jaluxx.Player;
import com.mindbadger.jaluxx.gamemanager.GameManager;

@SessionAttributes({"player"})
@Controller
public class ReadyToPlayController {
	Logger logger = Logger.getLogger(ReadyToPlayController.class);

	@Autowired
	GameManager gameManager;

	@RequestMapping(value = "/readyToPlay.html", method = RequestMethod.GET)
	public @ResponseBody String startNewGame(@ModelAttribute("player") Player player, Model model) {

		logger.debug("CONTROLLER: readyToPlay: player=" + player.getName());

		gameManager.readyToPlay(player);
		return "OK";
	}

}
