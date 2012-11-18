package com.mindbadger.jaluxx;

public class Player {
	private String name;
	private String password;
	private Game game;
	private PlayerStatus status;
	
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
}
