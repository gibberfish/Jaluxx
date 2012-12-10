package com.mindbadger.jaluxx.rules;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.mockito.*;

import com.mindbadger.jaluxx.card.Card;
import com.mindbadger.jaluxx.card.CardType;

public class RulesEngineTest {

   private RulesEngine rulesEngineUnderTest;
   
   private List<Card> basicRulesCards;
   
   private Card card1 = new Card ("card1", CardType.ACTION, "image1");
   private Card card2 = new Card ("card2", CardType.ACTION, "image2");
   
   @Before
   public void setup () {
      MockitoAnnotations.initMocks(this);
      
      basicRulesCards = new ArrayList<Card> ();
      
      rulesEngineUnderTest = new RulesEngine (basicRulesCards);
   }
   
   @Test
   public void instructionStackIsEmptyInitially () {
      // Given
      basicRulesCards.add (card1);
      basicRulesCards.add (card2);
      
      // When
      
      // Then
   }
}
