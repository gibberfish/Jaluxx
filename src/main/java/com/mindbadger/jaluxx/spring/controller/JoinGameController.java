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

import com.mindbadger.jaluxx.gamemanager.GameManager;
import com.mindbadger.jaluxx.player.Player;

@SessionAttributes({"player"})
@Controller
public class JoinGameController {
	Logger logger = Logger.getLogger(JoinGameController.class);

	@Autowired
	GameManager gameManager;

	@RequestMapping(value = "/joinGame.html", method = RequestMethod.GET)
	public @ResponseBody String startNewGame(@ModelAttribute("player") Player player, @RequestParam("gameId") String gameId, Model model) {

		logger.debug("CONTROLLER: joinGame: player=" + player.getName() + ", gameId=" + gameId);

		gameManager.joinGame(player, gameId);
		return "OK";
	}

}
