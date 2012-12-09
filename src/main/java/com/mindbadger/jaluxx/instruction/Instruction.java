package com.mindbadger.jaluxx.instruction;

public class Instruction {
	private InstructionType instructionType;

	public Instruction (InstructionType instructionType) {
	   this.instructionType = instructionType;
	}
	
   public InstructionType getInstructionType() {
      return instructionType;
   }
}
