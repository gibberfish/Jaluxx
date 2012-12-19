package com.mindbadger.jaluxx.gamemanager;

import java.util.List;

import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.game.Dealer;
import com.mindbadger.jaluxx.game.Game;
import com.mindbadger.jaluxx.game.Pack;
import com.mindbadger.jaluxx.player.Player;
import com.mindbadger.jaluxx.turn.PlayerTurn;

public class GameFactory {
	private Dealer dealer;
	private Pack pack;
	private List<Card> basicRulesCards;

	public Game createNewGameForPlayer(Player player) {
		return new Game(player, pack, dealer, this);
	}

	public PlayerTurn createNewTurnForPlayer (Player player, List<Player> players, List<Card> rules) {
		return new PlayerTurn (basicRulesCards);
	}
	
	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Pack getPack() {
		return pack;
	}

	public void setPack(Pack pack) {
		this.pack = pack;
	}

	public List<Card> getBasicRulesCards() {
		return basicRulesCards;
	}

	public void setBasicRulesCards(List<Card> basicRulesCards) {
		this.basicRulesCards = basicRulesCards;
	}
}
