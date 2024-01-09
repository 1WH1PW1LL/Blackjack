package blackjack;

public class PlayerNew {
    //setting the total cards that the player can draw as 10.
    private Card[] drawnCards = new Card[10];
    private int cardCount=0;

    public PlayerNew(){

    }

    public void addCard(Card newDrawnCard) {
        this.drawnCards[cardCount++] = newDrawnCard;
    }

    public int getCountWithAceAsOne(){
        int cnt = 0;
        for (int i=0; i<cardCount; i++) {
            cnt += this.drawnCards[i].getNumber();
        }
        return  cnt;
    }
    public int getCountWithAceAsEleven(){
        int cnt = 0;
        for (int i=0; i<cardCount; i++) {
            String name = this.drawnCards[i].getName();
            int num = this.drawnCards[i].getNumber();
            if (this.drawnCards[i].getName().equals("Ace")) {
                num=11;
            }
            cnt += num;
        }
        return  cnt;
    }

    // If both one and eleven will yield a value greater than 21 then there will be a bust.
    public boolean isBust(){
        int asOne = getCountWithAceAsOne();
        int asEleven = getCountWithAceAsEleven();

        if (asOne > 21 && asEleven > 21) {
            return true;
        } else {
            return false;
        }
    }

    //reset the cards for the next game
    public void resetCards() {
        this.cardCount = 0;
    }

    public String showCards(){
        String cards = null;
        for (int i=0; i<cardCount; i++) {
            if(cards != null) cards += " AND ";
            cards += this.drawnCards[i].toString();
        }
        return "Players Cards: " + cards;
    }

    public String showCardValues(){
        String val = "Ace as One: " + getCountWithAceAsOne();
        val = " and with Aces as Eleven: " + getCountWithAceAsEleven();
        return "Player, your total is: " + val;
    }

    private int money;

    private int stayIn;

    private String decision;

    private int currentCardsSum;

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

    public PlayerNew(Card card1, Card card2)
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
}
