package com.mindbadger.jaluxx.game;

import java.util.List;

import com.mindbadger.jaluxx.Player;
import com.mindbadger.jaluxx.card.Card;

public class Dealer {

	public void deal(List<Card> pack, List<Player> players, int cardsToDeal) {
		for(int i=0; i<cardsToDeal; i++){
			for (Player player : players) {
				Card nextCard = pack.remove(pack.size()-1);
				player.addCardToHand(nextCard);
			}
		}
	}

}
