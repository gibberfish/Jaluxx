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
	private List<Player> players;

	private Player player1 = new Player();;
	private Player player2 = new Player();;

	@Mock
	private Game mockGame;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		basicRulesCards = new ArrayList<Card>();
		currentRulesCards = new ArrayList<Card>();
		players = new ArrayList<Player>();

		players.add(player1);
		players.add(player2);
	}

	@Test
	public void singleBasicRulesCardWithOneInstruction() {
		// Given
		basicRulesCards.add(getCardDrawOne());

		playerTurnUnderTest = new PlayerTurn(basicRulesCards, player1, players, currentRulesCards, mockGame);

		// When
		Instruction instruction = playerTurnUnderTest.getNextInstructionForPlayer(player1);

		// Then
		assertEquals(Instruction.DRAW, instruction);

		// When
		Instruction instruction2 = playerTurnUnderTest.getNextInstructionForPlayer(player2);

		// Then
		assertNull(instruction2);
	}

	@Test
	public void standardBasicRulesCardsAddedBackwards() {
		// Given
		basicRulesCards.add(getCardPlayOne());
		basicRulesCards.add(getCardDrawOne());

		playerTurnUnderTest = new PlayerTurn(basicRulesCards, player1, players, currentRulesCards, mockGame);

		// When
		Instruction instruction = playerTurnUnderTest.getNextInstructionForPlayer(player1);

		// Then
		assertEquals(Instruction.DRAW, instruction);

		// When
		Instruction instruction2 = playerTurnUnderTest.getNextInstructionForPlayer(player2);

		// Then
		assertNull(instruction2);
	}

	@Test
	public void standardBasicRulesCardsAddedForwards() {
		// Given
		basicRulesCards.add(getCardDrawOne());
		basicRulesCards.add(getCardPlayOne());

		playerTurnUnderTest = new PlayerTurn(basicRulesCards, player1, players, currentRulesCards, mockGame);

		// When
		Instruction instruction = playerTurnUnderTest.getNextInstructionForPlayer(player1);

		// Then
		assertEquals(Instruction.DRAW, instruction);

		// When
		Instruction instruction2 = playerTurnUnderTest.getNextInstructionForPlayer(player2);

		// Then
		assertNull(instruction2);
	}

//	@Test
//	public void getNextInstructionAfterAnAction() {
//		// Given
//		basicRulesCards.add(getCardDrawOne());
//		basicRulesCards.add(getCardPlayOne());
//
//		playerTurnUnderTest = new PlayerTurn(basicRulesCards, player1, players, currentRulesCards, mockGame);
//
//		// When
//		playerTurnUnderTest.actionPerformedByPlayer ()
//		
//		Instruction instruction = playerTurnUnderTest.getNextInstructionForPlayer(player1);
//
//		// Then
//		assertEquals(Instruction.DRAW, instruction);
//
//		// When
//		Instruction instruction2 = playerTurnUnderTest.getNextInstructionForPlayer(player2);
//
//		// Then
//		assertNull(instruction2);
//	}

	private Card getCardDrawOne() {
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(Instruction.DRAW);
		
		Card card = new Card("Draw 1", CardType.RULE, "image1");
		
		card.setInstructions(instructions);
		card.setGroupName("DRAW");
		card.setSequence(10);
		
		return card;
	}
	
	private Card getCardPlayOne() {
		List<Instruction> instructions = new ArrayList<Instruction>();
		instructions.add(Instruction.PLAY);
		
		Card card = new Card("Play 1", CardType.RULE, "image1");
		
		card.setInstructions(instructions);
		card.setGroupName("PLAY");
		card.setSequence(70);
		
		return card;
	}

}
