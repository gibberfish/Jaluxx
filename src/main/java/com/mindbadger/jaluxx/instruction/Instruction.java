package com.mindbadger.jaluxx.instruction;

public enum Instruction {
	DRAW ("Draw"),
	PLAY ("Play"),
	;
	
	private final String message;

   private Instruction (String message) {
		this.message = message;
	}
	
	public String getMessage () {
		return message;
	}
}
