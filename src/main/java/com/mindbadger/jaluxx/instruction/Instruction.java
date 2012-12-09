package com.mindbadger.jaluxx.instruction;

public enum Instruction {
	DRAW ("Draw", 10),
	PLAY ("Play", 20),
	;
	
	private final String message;
	private final int sequence;

   private Instruction (String message, int sequence) {
		this.message = message;
		this.sequence = sequence;
	}
	
	public String getMessage () {
		return message;
	}

	public int getSequence() {
	   return sequence;
	}
}
