package com.mindbadger.jaluxx.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mindbadger.jaluxx.Action;
import com.mindbadger.jaluxx.Player;
import com.mindbadger.jaluxx.gamemanager.GameManager;

@SessionAttributes({"player"})
@Controller
public class JaluxxController {
  Logger logger = Logger.getLogger(JaluxxController.class);
  
  @Autowired
  GameManager gameManager;

  @ModelAttribute("player")
  public Player populatePlayer () {
	  return new Player();
  }
  
  @RequestMapping("/jaluxx.html")
  public ModelAndView chooseGame(@ModelAttribute("player") Player player) {
    logger.debug("CONTROLLER: chooseGame (jaluxx starting point)");
    logger.debug("...player: " + player.getName());
    
    ModelAndView mav = new ModelAndView();
    mav.addObject("registeredPlayers", gameManager.getRegisteredPlayers());
    
    mav.addObject("me", player);
    mav.addObject("actions", getActionStrings(player));
    mav.addObject("games", gameManager.getGames());
    
    if (player.getGame() != null && player.getGame().isPlaying()) {
   	 mav.setViewName("jaluxx");
    } else {
   	 mav.setViewName("chooseGame");
    }
    
    return mav;
  }

  private List<String> getActionStrings (Player player) {
	  List<String> actionStrings = new ArrayList<String> ();
	  for (Action action : gameManager.getActions()) {
		  actionStrings.add(action.getActionMessageFor(player));
	  }
	  return actionStrings;
  }
}
