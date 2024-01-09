package blackjack;

import java.util.*;

public class BlackJack {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        Random randomNumber = new Random();

        // Setting up the Deck.
        Deck firstDeck = new Deck();
        firstDeck.setDeckOfCards();
        Card[] cards = firstDeck.getDeckOfCards();

        // Printing the Entire Deck.
        for(int count = 0; count < 52; count++)
        {
            // System.out.println(cards[count].toString());
        }

        // Creating the player Cards.
        Card startingPlayerCard1 = cards[randomNumber.nextInt(51)];
        Card startingPlayerCard2 = cards[randomNumber.nextInt(51)];

        // Player Object
        Player player = new Player(startingPlayerCard1, startingPlayerCard2);

        int currentDealerSum;

        // The game begins here. All the setting up is done.
        boolean gameContinues = true;
        do
        {
            // Asking for the bet.
            int currentBet;
            System.out.println("How much are you betting?");
            currentBet = keyboard.nextInt();
            keyboard.nextLine();

            // This is the cards the dealer starts with.
            Card startingDealerCard1 = cards[randomNumber.nextInt(52)];
            Card startingDealerCard2 = cards[randomNumber.nextInt(52)];
            currentDealerSum = startingDealerCard2.getNumber();

            // Showing the user one of the dealers cards.
            System.out.println("Dealer's Card: " + startingDealerCard2);

            //shuffle
//            Dealer dealer = new Dealer(startingDealerCard1, startingDealerCard2);
//            cards = dealer.shuffle(cards);

            // Giving the player their cards
            // Creating the player Cards.
            Card playerCard1 = cards[randomNumber.nextInt(52)];
            Card playerCard2 = cards[randomNumber.nextInt(52)];

            // Setting the cards to the player object.
            player.setCard1(playerCard1);
            player.setCard2(playerCard2);

            // Displaying the player cards to the player.
            System.out.print("Players Cards: ");
            System.out.print(playerCard1.toString() + " AND ");
            System.out.println(playerCard2.toString());

            // Showing the Player their sum.
            player.setCurrentCardsSum(playerCard1.getNumber() + playerCard2.getNumber());

            //Ace as Eleven calculations
            if(playerCard1.getNumber() == 1) //first card an ace
                player.setCurrentCardsSumAceAsEleven(11); //set ace sum to 11
            else
                player.setCurrentCardsSumAceAsEleven(playerCard1.getNumber()); //if not set ace sum to actual value  of card
            if(playerCard2.getNumber() == 1) //second card an ace
                player.setCurrentCardsSumAceAsEleven(player.getCurrentCardsSumAceAsEleven() + 11); // add 11 to the ace sum
            else
                player.setCurrentCardsSumAceAsEleven(player.getCurrentCardsSumAceAsEleven() + playerCard2.getNumber());// if not add card value to ace sum
            if(playerCard1.getNumber() == 1 && playerCard2.getNumber() == 1) player.setCurrentCardsSumAceAsEleven(12); // both are aces, use 11 or 1 and 1 for the other card

            String playerDecision;
            boolean endPlayerTurn = false;

            // Player Interactions.
            boolean playerLoss = false;
            do
            {
                System.out.println();
                System.out.println("Dealer total is " + currentDealerSum + " which is the dealers first card.");
                System.out.println("Player, your total is: " + player.getCurrentCardsSum());
                if(player.getCurrentCardsSum() != player.getCurrentCardsSumAceAsEleven())
                    System.out.println("Player, your total with Aces as Eleven is: " + player.getCurrentCardsSumAceAsEleven());
                System.out.println("Player, Do you want to hit or stand?");
                playerDecision = keyboard.nextLine();
                player.setDecision(playerDecision);

                if(player.getDecision().equals("hit"))
                {
                    Card playerPickedCard = cards[randomNumber.nextInt(52)];
                    System.out.println();
                    System.out.println("Your new card is: " + playerPickedCard);
                    player.setCurrentCardsSum(player.getCurrentCardsSum() + playerPickedCard.getNumber());
                    System.out.println("Your current total is " + player.getCurrentCardsSum() + " be careful not to exceed 21!");
                    if (playerPickedCard.getName().equals("Ace")) {
                        player.setCurrentCardsSumAceAsEleven(player.getCurrentCardsSumAceAsEleven() + 11);
                        System.out.println("Your current total with Ace As Eleven is " + player.getCurrentCardsSumAceAsEleven() + " be careful not to exceed 21!");
                    }else
                        player.setCurrentCardsSumAceAsEleven(player.getCurrentCardsSumAceAsEleven() + playerPickedCard.getNumber() );
                }

                if(player.getDecision().equals("stand"))
                {
                    System.out.println("Your final total is " + player.getCurrentCardsSum() + " congratulations!");
                    if(player.getCurrentCardsSum() != player.getCurrentCardsSumAceAsEleven())
                        System.out.println("Your final total with Aces as Eleven is " + player.getCurrentCardsSumAceAsEleven() + " congratulations!");
                    endPlayerTurn = true;
                }

                if(player.getCurrentCardsSum() > 21 )
                {
                    System.out.println("Bust!!! You lose!");
                    endPlayerTurn = true;
                    playerLoss = true;
                    player.setMoney(player.getMoney()-currentBet);
                    System.out.println("Your current money is: " + player.getMoney());

                    System.out.println("Should we continue the game. Enter no to exit the game or any key to continue?");
                    if(keyboard.nextLine().equalsIgnoreCase("no"))
                    {
                        gameContinues = false;
                        System.out.println("Your total money is: " + player.getMoney());
                        System.out.println("Thanks for playing!");
                    }
                    else
                    {
                        gameContinues = true;
                    }
                }
            }while(!endPlayerTurn);

            // Dealer Interactions.
            if(!playerLoss)
            {
                boolean endDealerTurn = false;
                currentDealerSum += startingDealerCard1.getNumber();
                System.out.println("After adding the dealers other card the total before drawing is " + currentDealerSum);

                //check if dealer is 17 or more and less than or equal to 21
                if(currentDealerSum >= 17 && currentDealerSum <= 21 ){
                    if(player.getCurrentCardsSum() > currentDealerSum
                            || (player.getCurrentCardsSumAceAsEleven() <=21 && player.getCurrentCardsSumAceAsEleven() > currentDealerSum) ){

                        System.out.println("Your final total is " + player.getCurrentCardsSum());
                        if(player.getCurrentCardsSum() != player.getCurrentCardsSumAceAsEleven())
                            System.out.println("Your final total with Aces as Eleven is " + player.getCurrentCardsSumAceAsEleven());
                        System.out.println("The dealer total is: " + currentDealerSum);
                        System.out.println("The dealer hand is less than your hand. You win!!!");
                        player.setMoney(player.getMoney()+currentBet);
                        System.out.println("Your current money is: " + player.getMoney());

                        System.out.println("Should we continue the game?");
                        if(keyboard.nextLine().equals("no"))
                        {
                            gameContinues = false;
                            System.out.println("Your total money is: " + player.getMoney());
                            System.out.println("Thanks for playing!");
                        }
                        else
                        {
                            gameContinues = true;
                        }

                    }
                    else if (player.getCurrentCardsSum() < currentDealerSum
                            || (player.getCurrentCardsSumAceAsEleven() <=21 && player.getCurrentCardsSumAceAsEleven() < currentDealerSum) )
                    {
                        System.out.println("Your final total is " + player.getCurrentCardsSum());
                        if(player.getCurrentCardsSum() != player.getCurrentCardsSumAceAsEleven())
                            System.out.println("Your final total with Aces as Eleven is " + player.getCurrentCardsSumAceAsEleven());
                        System.out.println("The dealer total is: " + currentDealerSum);
                        System.out.println("The dealer hand is more than your hand. You lose!!!");
                        player.setMoney(player.getMoney()-currentBet);
                        System.out.println("Your current money is: " + player.getMoney());

                        System.out.println("Should we continue the game?");
                        if(keyboard.nextLine().equals("no"))
                        {
                            gameContinues = false;
                            System.out.println("Your total money is: " + player.getMoney());
                            System.out.println("Thanks for playing!");
                        }
                        else
                        {
                            gameContinues = true;
                        }

                    }
                    else
                    {
                        System.out.println("Push. You get your money back.");
                        System.out.println("Your current money is: " + player.getMoney());

                        System.out.println("Should we continue the game?");
                        if(keyboard.nextLine().equals("no"))
                        {
                            gameContinues = false;
                            System.out.println("Your total money is: " + player.getMoney());
                            System.out.println("Thanks for playing!");
                        }
                        else
                        {
                            gameContinues = true;
                        }
                    }
                }
                else if(currentDealerSum > 21)
                {
                    //dealer busts on two cards
                    System.out.println("The dealer busts after adding the dealers other card. You win!!!");
                    player.setMoney(player.getMoney()+currentBet);
                    System.out.println("Your current money is: " + player.getMoney());

                    System.out.println("Should we continue the game?");
                    if(keyboard.nextLine().equals("no"))
                    {
                        gameContinues = false;
                        System.out.println("Your total money is: " + player.getMoney());
                        System.out.println("Thanks for playing!");
                    }
                    else
                    {
                        gameContinues = true;
                    }
                }
                else
                { // dealer need to draw
                    do
                    {
                        Card dealerPickedCard = cards[randomNumber.nextInt(52)];
                        System.out.println("Dealer drawn card: " + dealerPickedCard.toString());

                        currentDealerSum += dealerPickedCard.getNumber();

                        System.out.println("The current dealer sum is " + currentDealerSum);

                        if(currentDealerSum >= 17 && currentDealerSum <= 21)
                        {
                            System.out.println("The current dealer sum is " + currentDealerSum);
                            if(player.getCurrentCardsSum() > currentDealerSum
                                    || (player.getCurrentCardsSumAceAsEleven() <= 21 && player.getCurrentCardsSumAceAsEleven() > currentDealerSum) )
                            {
                                System.out.println("You beat the dealer's score. You win!!!");
                                player.setMoney(player.getMoney()+currentBet);
                                System.out.println("Your current money is: " + player.getMoney());

                                System.out.println("Should we continue the game?");
                                if(keyboard.nextLine().equals("no"))
                                {
                                    gameContinues = false;
                                    System.out.println("Your total money is: " + player.getMoney());
                                    System.out.println("Thanks for playing!");
                                }
                                else
                                {
                                    gameContinues = true;
                                }
                            }

                            if(player.getCurrentCardsSum() < currentDealerSum
                                    || (player.getCurrentCardsSumAceAsEleven() <= 21 && player.getCurrentCardsSumAceAsEleven() < currentDealerSum))
                            {
                                System.out.println("The dealer beat your score. You lose.");
                                player.setMoney(player.getMoney()-currentBet);
                                System.out.println("Your current money is: " + player.getMoney());

                                System.out.println("Should we continue the game?");
                                if(keyboard.nextLine().equals("no"))
                                {
                                    gameContinues = false;
                                    System.out.println("Your total money is: " + player.getMoney());
                                    System.out.println("Thanks for playing!");
                                }
                                else
                                {
                                    gameContinues = true;
                                }
                            }
                            endDealerTurn = true;

                            if(player.getCurrentCardsSum() == currentDealerSum
                                    || (player.getCurrentCardsSumAceAsEleven() <= 21 && player.getCurrentCardsSumAceAsEleven() == currentDealerSum) )
                            {
                                System.out.println("Push. You get your money back.");
                                System.out.println("Your current money is: " + player.getMoney());

                                System.out.println("Should we continue the game?");
                                if(keyboard.nextLine().equals("no"))
                                {
                                    gameContinues = false;
                                    System.out.println("Your total money is: " + player.getMoney());
                                    System.out.println("Thanks for playing!");
                                }
                                else
                                {
                                    gameContinues = true;
                                }
                            }
                        }
                        else if(currentDealerSum > 21)
                        {
                            System.out.println("The dealer busts. You win!!!");
                            player.setMoney(player.getMoney()+currentBet);
                            System.out.println("Your current money is: " + player.getMoney());
                            endDealerTurn = true;

                            System.out.println("Should we continue the game?");
                            if(keyboard.nextLine().equals("no"))
                            {
                                gameContinues = false;
                                System.out.println("Your total money is: " + player.getMoney());
                                System.out.println("Thanks for playing!");
                            }
                            else
                            {
                                gameContinues = true;
                            }
                        }
                    }while(!endDealerTurn);

                }

            }
        }while(gameContinues);


        
    }
}


