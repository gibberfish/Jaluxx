package com.mindbadger.jaluxx.card;

public class Card {
	private String cardName;
	private CardType cardType;
	private String image;
	
	public Card (String cardName, CardType cardType, String image) {
		this.cardName = cardName;
		this.cardType = cardType;
		this.image = image;
	}
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
