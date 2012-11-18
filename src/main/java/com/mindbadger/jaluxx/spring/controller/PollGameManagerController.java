package com.mindbadger.jaluxx.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mindbadger.jaluxx.gamemanager.GameManager;

@Controller
public class PollGameManagerController {
	Logger logger = Logger.getLogger(PollGameManagerController.class);

	@Autowired
	GameManager gameManager;

	@RequestMapping(value = "/pollGameMgr.html", method = RequestMethod.GET)
	public @ResponseBody String pollGameManagerForChanges(@RequestParam("lastIndex") int lastIndex) {
		int size = gameManager.getActions().size();
		return ( Integer.toString(size - 1)).toString();
	}
}
