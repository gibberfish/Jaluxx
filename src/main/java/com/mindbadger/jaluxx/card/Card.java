package com.mindbadger.jaluxx.card;

import java.util.List;

import com.mindbadger.jaluxx.instruction.Instruction;

public class Card implements Comparable<Card> {
	private static final int DEFAULT_SEQUENCE = 50;

	private String cardName;
	private CardType cardType;
	private String image;
	private List<Instruction> instructions;
	private String groupName;
	private int sequence;

	public Card(String cardName, CardType cardType, String image) {
		this.cardName = cardName;
		this.cardType = cardType;
		this.image = image;

		sequence = DEFAULT_SEQUENCE;
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

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public int compareTo(Card card) {
		return getSequence() - card.getSequence();
	}
}
