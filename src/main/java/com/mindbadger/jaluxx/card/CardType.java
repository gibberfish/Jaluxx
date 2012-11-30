package com.mindbadger.jaluxx.card;

public enum CardType {
	ACTION ("Action"),
	KEEPER ("Keeper"),
	RULE ("Rule"),
	GOAL ("Goal");
	
	private String cardTypeText;
	
	private CardType (String cardTypeText) {
		this.cardTypeText = cardTypeText;
	}
	
	public String getText () {
		return cardTypeText;
	}
}
