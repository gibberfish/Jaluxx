package com.mindbadger.jaluxx.action;

import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.player.Player;

public class Action {
	private static final String RECEIVING_PLAYER_NAME_TOKEN = "$rname";
	private static final String ACTIONING_PLAYER_NAME_TOKEN = "$aname";
	private static final String RECEIVING_PLAYER_TAG = "r";
	private static final String ACTIONING_PLAYER_TAG = "a";
	private static final String CARD_TOKEN = "$card";
	
	private ActionType actionType;
	private Player actioningPlayer;
	private Player receivingPlayer;
	private Card card;
	
	public Action (ActionType actionType, Player actioningPlayer) {
		this.actionType = actionType;
		this.actioningPlayer = actioningPlayer;
		this.receivingPlayer = null;
	}

	public Action (ActionType actionType, Player actioningPlayer, Player receivingPlayer) {
		this.actionType = actionType;
		this.actioningPlayer = actioningPlayer;
		this.receivingPlayer = receivingPlayer;
	}

	public Action(ActionType draw, Player player1, Card card) {
		this(draw, player1);
		this.card = card;
	}

	public ActionType getActionType() {
		return actionType;
	}
	public Player getActioningPlayer() {
		return actioningPlayer;
	}
	public Player getReceivingPlayer() {
		return receivingPlayer;
	}
	public String getActionMessageFor(Player player) {
		String message = actionType.getMessage();
		
		message = replacePlayerTags (message, ACTIONING_PLAYER_TAG, (player == actioningPlayer));
		message = replacePlayerTags (message, RECEIVING_PLAYER_TAG, (player == receivingPlayer));
		
		message = message.replace(ACTIONING_PLAYER_NAME_TOKEN, actioningPlayer.getName());
		
		if (receivingPlayer != null) {
			message = message.replace(RECEIVING_PLAYER_NAME_TOKEN, receivingPlayer.getName());
		}

		if (card != null) {
			message = message.replace(CARD_TOKEN, card.getCardName());
		}

		return message;
	}

	protected String replacePlayerTags(String message, String tagType, boolean isMatchingPlayer) {
		int open = message.indexOf("["+tagType+":");
		while (open != -1) {
			int seperator = message.indexOf("|", open);
			int close = message.indexOf("]", seperator);
			
			StringBuffer sb = new StringBuffer();
			sb.append(message.substring(0, open));

			if (isMatchingPlayer) {
				sb.append(message.substring(open+3, seperator));
			} else {
				sb.append(message.substring(seperator+1,close));
			}
			sb.append(message.substring(close+1));
			
			message = sb.toString();
			
			open = message.indexOf("["+tagType+":");
		}
		
		return message;
	}
}
