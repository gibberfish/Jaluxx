package com.mindbadger.jaluxx.player;

public enum PlayerStatus {
	NOT_IN_GAME ("Not In Game"),
	JOINED_GAME ("Joined Game"),
	READY_TO_PLAY ("Ready To Play"),
	PLAYING ("Playing");
	
	private String statusText;
	
	private PlayerStatus (String statusText) {
		this.statusText = statusText;
	}
	
	public String getStatus () {
		return statusText;
	}
}
