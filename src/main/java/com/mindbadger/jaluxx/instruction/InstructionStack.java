package com.mindbadger.jaluxx.instruction;

import java.util.ArrayList;
import java.util.List;

public class InstructionStack {

   private List<Instruction> instructions = new ArrayList<Instruction> ();

   public List<Instruction> getInstructions() {
      return instructions;
   }
   
}
