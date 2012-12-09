package com.mindbadger.jaluxx.instruction;

public enum InstructionType {
	DRAW ("Draw"),
	PLAY ("Play"),
	//TAKE_A_TURN, PICK_UP_CARD, PLAY_CARD, GIVE_CARD, TAKE_CARD, DISCARD_CARD, TRASH_RULE, TAKE_FROM_DISCARD_PILE, FINISH_TURN, EMPTY_TRASH, SHUFFLE; 
	;
	
	private final String message;
	
	private InstructionType (String message) {
		this.message = message;
	}
	
	public String getMessage () {
		return message;
	}
}
