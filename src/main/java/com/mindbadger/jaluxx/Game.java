package com.mindbadger.jaluxx;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.gamemanager.GameManager;

public class Game {
	Logger logger = Logger.getLogger(Game.class);
	
	List<Player> players = new ArrayList<Player> ();
	private long gameId;
	private int minimumPlayers = 2;
	private Pack pack;
	private List<Card> drawPile = new LinkedList<Card> ();

	public Game(Player player, Pack pack) {
		//gameId = UUID.randomUUID().toString();
		gameId = Calendar.getInstance().getTime().getTime();
		players.add(player);
		this.pack = pack;
		
		for (Card card : pack.getCardsInPack()) {
			drawPile.add(card);
		}
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public long getGameId() {
		return gameId;
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public int getMinimumPlayers () {
		return this.minimumPlayers;
	}

	public boolean isPlaying() {
		if (players.size() < minimumPlayers) return false;
		
		for (Player playerInGame : players) {
			if (playerInGame.getStatus() != PlayerStatus.READY_TO_PLAY) {
				return false;
			}
		}
		
		return true;
	}

	public List<Card> getDrawPile() {
		return drawPile;
	}

}
