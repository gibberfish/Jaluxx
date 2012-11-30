package com.mindbadger.jaluxx;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mindbadger.jaluxx.card.Card;

public class Game {
	private static final int CARDS_TO_DEAL = 3;

	Logger logger = Logger.getLogger(Game.class);
	
	List<Player> players = new ArrayList<Player> ();
	private long gameId;
	private int minimumPlayers = 2;
	private List<Card> drawPile = new LinkedList<Card> ();
	private Dealer dealer;
	private GameStatus status;

	public Game(Player player, Pack pack, Dealer dealer) {
		gameId = Calendar.getInstance().getTime().getTime();
		players.add(player);
		this.dealer = dealer;
		status = GameStatus.SETUP;
		
		for (Card card : pack.getCardsInPack()) {
			drawPile.add(card);
		}
	}

	public void startGame() {
		status = GameStatus.PLAYING;
		
		dealer.deal(drawPile, players, CARDS_TO_DEAL);
	}

	public Card takeCardFromDrawPile() {
		return drawPile.remove(drawPile.size()-1);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public long getGameId() {
		return gameId;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public int getMinimumPlayers () {
		return this.minimumPlayers;
	}

	public List<Card> getDrawPile() {
		return drawPile;
	}

}
