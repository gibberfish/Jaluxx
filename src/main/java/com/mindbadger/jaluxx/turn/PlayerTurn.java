package com.mindbadger.jaluxx.turn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mindbadger.jaluxx.JaluxxException;
import com.mindbadger.jaluxx.action.Action;
import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.game.Game;
import com.mindbadger.jaluxx.instruction.Instruction;
import com.mindbadger.jaluxx.player.Player;

public class PlayerTurn {
	private List<Card> basicRulesCards;
	private List<Card> rulesCards = new ArrayList<Card>();
	private List<Player> players;
	private Player currentPlayer;
	private Game game;
	private Map <Player, List<Instruction>> playerInstructions = new HashMap<Player, List<Instruction>> ();

	public PlayerTurn(List<Card> basicRulesCards, Player currentPlayer, List<Player> players, List<Card> rulesCards, Game game) {
		this.basicRulesCards = basicRulesCards;
		this.rulesCards = rulesCards;
		this.currentPlayer = currentPlayer;
		this.players = players;
		this.game = game;
		
		for (Player player : players) {
			playerInstructions.put(player,  new ArrayList<Instruction> ());
		}
		
		List<Instruction> currentPlayersInstructions = playerInstructions.get(currentPlayer);
		
		Collections.sort(basicRulesCards);
		
		for (Card card : basicRulesCards) {
			currentPlayersInstructions.addAll(card.getInstructions());
		}
	}

	public Instruction getNextInstructionForPlayer(Player player) {
		List<Instruction> instructions = playerInstructions.get(player);
		return (instructions.size()>0 ? instructions.get(0) : null);
	}

   public void actionPerformedByPlayer(Player player, Action action) {
      Instruction nextInstructionForPlayer = getNextInstructionForPlayer(player);
      if (nextInstructionForPlayer != action.getInstruction()) {
         throw new JaluxxException ("Action " + action.getActionType() + " is not allowed for Instruction " + nextInstructionForPlayer);
      }
      
      List<Instruction> instructions = playerInstructions.get(player);
      instructions.remove(0);
   }
}
