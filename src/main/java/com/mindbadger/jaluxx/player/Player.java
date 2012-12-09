package com.mindbadger.jaluxx.player;

import java.util.LinkedList;
import java.util.List;

import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.game.Game;

public class Player {
	private String name;
	private String password;
	private Game game;
	private PlayerStatus status;
	private List<Card> hand = new LinkedList<Card> ();
	
	public Player () {
		status = PlayerStatus.NOT_IN_GAME;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public PlayerStatus getStatus() {
		return status;
	}
	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void addCardToHand (Card card){
		this.hand.add(card);
	}
}
