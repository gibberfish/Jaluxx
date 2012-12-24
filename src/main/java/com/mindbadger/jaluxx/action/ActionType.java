package com.mindbadger.jaluxx.action;

public enum ActionType {
	// $aname = name of the actioning player
   // $rname = name of the receiving player
	// $card = name of the card
	// [a:render if you are the actioning player|render otherwise]
	// [r:render if you are the receiving player|render otherwise]
	GAME_STARTED ("The game has started"),
	LOGGED_IN ("[a:You have|$aname has] logged in"),
	LOGGED_OUT ("[a:You have|$aname has] logged out"),
	NEW_GAME ("[a:You have|$aname has] started a new game"),
	JOIN_GAME ("[a:You have|$aname has] joined a game"),
	READY_TO_PLAY ("[a:You are|$aname is] ready to play"),
	PLAYERS_TURN ("[a:Your|$aname's] turn"),
	
	DRAW ("[a:You drew card $card|$aname drew a card]"),
	PLAY ("[a:You played|$aname played] card $card")
	;
	
	private final String message;
	
	private ActionType (String message) {
		this.message = message;
	}
	
	public String getMessage () {
		return message;
	}
}
