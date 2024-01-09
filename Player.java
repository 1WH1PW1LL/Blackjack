package blackjack;

import java.util.*;
public class Player {
    private int money;

    private int stayIn;

    private String decision;

    private int currentCardsSum;
    private int currentCardsSumAceAsEleven;

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    private Card card1;

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    private Card card2;
    private Card[] cardsInHand;

    public Player(Card card1, Card card2)
    {
       cardsInHand = new Card[] {card1, card2};
       money = 1000;
       calculateCardSum();
       this.card1 = card1;
       this.card2 = card2;
    }

    private void calculateCardSum(){
        currentCardsSum = 0;
        boolean hasAce = false;
        do {
            for (Card card : cardsInHand) {
                currentCardsSum += card.getNumber();
            }
            if (currentCardsSum > 21) {
                for (Card card : cardsInHand) {
                    if (card.getName().equals("Ace")) {
                        card.setNumber(1);
                        hasAce = true;
                        break;
                    }
                }
            }
        } while (currentCardsSum > 21 && hasAce);
    }


    public void setMoney(int money)
    {
        this.money = money;
    }

    public void setStayIn(int stayIn)
    {
        this.stayIn = stayIn;
    }

    public void setDecision(String decision)
    {
        this.decision = decision;
    }

    public void setCurrentCardsSum(int currentCardsSum)
    {
        this.currentCardsSum = currentCardsSum;
    }

    public int getMoney()
    {
        return money;
    }

    public int getStayIn()
    {
        return stayIn;
    }

    public String getDecision()
    {
        return decision;
    }

    public int getCurrentCardsSum()
    {
        return currentCardsSum;
    }

    public int getCurrentCardsSumAceAsEleven() {
        return currentCardsSumAceAsEleven;
    }

    public void setCurrentCardsSumAceAsEleven(int currentCardsSumAceAsEleven) {
        this.currentCardsSumAceAsEleven = currentCardsSumAceAsEleven;
    }
}
