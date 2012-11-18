package com.mindbadger.jaluxx;

import java.util.LinkedList;
import java.util.List;

import com.mindbadger.jaluxx.card.Card;

public class Pack {
	
	private List<Card> cardsInPack = new LinkedList<Card> ();

	public List<Card> getCardsInPack() {
		return cardsInPack;
	}

	public void setCardsInPack(List<Card> cardsInPack) {
		this.cardsInPack = cardsInPack;
	}
}
