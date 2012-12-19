package com.mindbadger.jaluxx.game;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mindbadger.jaluxx.action.Action;
import com.mindbadger.jaluxx.action.ActionType;
import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.gamemanager.GameFactory;
import com.mindbadger.jaluxx.player.Player;
import com.mindbadger.jaluxx.turn.PlayerTurn;

public class Game {
	private static final int CARDS_TO_DEAL = 3;

	private Logger logger = Logger.getLogger(Game.class);

	private long gameId;
	private GameStatus status;
	private int minimumPlayers = 2;
	private Dealer dealer;
	private int whosTurn;
	private List<Player> players = new ArrayList<Player>();
	private List<Action> actions = new ArrayList<Action>();
	private List<Card> drawPile = new LinkedList<Card>();
	private List<Card> discardPile = new LinkedList<Card>();
	private List<Card> activeRules = new LinkedList<Card>();
	private PlayerTurn currentPlayerTurn;
	private GameFactory gameFactory;
	
	public Game(Player player, Pack pack, Dealer dealer, GameFactory gameFactory) {
		gameId = Calendar.getInstance().getTime().getTime();
		players.add(player);
		this.dealer = dealer;
		this.gameFactory = gameFactory;
		status = GameStatus.SETUP;

		for (Card card : pack.getCardsInPack()) {
			drawPile.add(card);
		}
	}

	public void startGame() {
		status = GameStatus.PLAYING;

		dealer.deal(drawPile, players, CARDS_TO_DEAL);

		whosTurn = 0;
		Player player = players.get(whosTurn);
		startTurnFor(player);
		
		gameFactory.createNewTurnForPlayer(player, players, activeRules);
	}

	private void startTurnFor(Player player) {
		addAction(ActionType.PLAYERS_TURN, player);
	}

	public Card takeCardFromDrawPile() {
		return drawPile.remove(drawPile.size() - 1);
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

	public int getMinimumPlayers() {
		return this.minimumPlayers;
	}

	public List<Card> getDrawPile() {
		return drawPile;
	}

	public Object getWhosTurn() {
		return whosTurn;
	}

	protected void addAction(ActionType type, Player player) {
		actions.add(new Action(type, player));
	}

	public List<Action> getActions() {
		return actions;
	}

	public List<Card> getDiscardPile() {
		return discardPile;
	}
}
