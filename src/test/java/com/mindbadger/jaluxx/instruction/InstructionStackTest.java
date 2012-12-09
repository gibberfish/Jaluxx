package com.mindbadger.jaluxx.instruction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.mockito.*;

public class InstructionStackTest {
   private InstructionStack instructionStackUnderTest;

   @Before
   public void setup () {
      MockitoAnnotations.initMocks(this);
      instructionStackUnderTest = new InstructionStack();
   }
   
   @Test
   public void instructionStackIsEmptyInitially () {
      // Given
      
      // When
      
      // Then
      assertEquals (0, instructionStackUnderTest.getInstructions().size());
   }
}
