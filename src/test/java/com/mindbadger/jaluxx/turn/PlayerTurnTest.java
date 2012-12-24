package com.mindbadger.jaluxx.turn;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.mockito.*;

import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.card.CardType;
import com.mindbadger.jaluxx.game.Game;
import com.mindbadger.jaluxx.instruction.Instruction;
import com.mindbadger.jaluxx.player.Player;
import com.mindbadger.jaluxx.turn.PlayerTurn;

public class PlayerTurnTest {

   private PlayerTurn playerTurnUnderTest;
   
   private List<Card> basicRulesCards;
   private List<Card> currentRulesCards;
   private List<Instruction> instructions;
   private List<Player> players;
   
   private Card card1 = new Card ("card1", CardType.ACTION, "image1");
   private Card card2 = new Card ("card2", CardType.ACTION, "image2");
   
   private Player player1 = new Player ();;
   private Player player2 = new Player ();;
   
   @Mock private Game mockGame;

   
   @Before
   public void setup () {
      MockitoAnnotations.initMocks(this);
      
      basicRulesCards = new ArrayList<Card> ();
      currentRulesCards = new ArrayList<Card> ();
      players = new ArrayList<Player> ();
      instructions = new ArrayList<Instruction> ();
      
      players.add(player1);
      players.add(player2);
      
   }
   
   @Test
   public void singleBasicRulesCardWithOneInstruction () {
      // Given
	   basicRulesCards.add (card1);
	   card1.setInstructions(instructions);
	   instructions.add(Instruction.DRAW);
      
	   playerTurnUnderTest = new PlayerTurn (basicRulesCards, player1, players, currentRulesCards, mockGame);

	   // When
      Instruction instruction = playerTurnUnderTest.getNextInstructionForPlayer (player1);
      
      // Then
      assertEquals (Instruction.DRAW, instruction);

      // When
      Instruction instruction2 = playerTurnUnderTest.getNextInstructionForPlayer (player2);
      
      // Then
      assertNull (instruction2);
   }
}
