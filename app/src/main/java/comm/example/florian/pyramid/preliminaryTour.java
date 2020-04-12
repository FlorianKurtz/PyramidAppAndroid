package comm.example.florian.pyramid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class preliminaryTour extends AppCompatActivity {
    int step = 1 ;
    int playerTour = 0 ;
    int playerNumber ;
    ArrayList<Player> playerList ;
    Deck myDeck ;
    private TextView playerName = null ;
    private TextView challenge = null ;
    private Button c1 = null;
    private Button c2 = null;
    private Button c3 = null;
    private Button c4 = null;
    private ImageView card1 = null ;
    private ImageView card2 = null ;
    private ImageView card3 = null ;
    private ImageView card4 = null ;
    private Button next = null;
    public final static String LIST = "comm.example.florian.pyramid.LIST";
    public final static String DECK = "comm.example.florian.pyramid.DECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_step);
        Log.d("DEBUG", "On Create executed");

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Player names verification
        playerList = i.getParcelableArrayListExtra(setupPlayer.LIST);
        playerNumber = playerList.size();
        for (int j = 0; j < playerNumber; j++) {
            Log.d("DEBUG", playerList.get(j).name);
        }

        // Getting all components
        playerName = findViewById(R.id.player_name);
        challenge = findViewById(R.id.challenge);
        c1 = findViewById(R.id.choice1);
        c2 = findViewById(R.id.choice2);
        c3 = findViewById(R.id.choice3);
        c4 = findViewById(R.id.choice4);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        next = findViewById(R.id.nextturn);

        playerName.setText(playerList.get(playerTour).name);
        challenge.setText("Red or Black ?") ;
        c1.setText("Red");
        c2.setText("Black");
        c3.setVisibility(View.GONE);
        c4.setVisibility(View.GONE);
        card1.setVisibility(View.GONE);
        card2.setVisibility(View.GONE);
        card3.setVisibility(View.GONE);
        card4.setVisibility(View.GONE);
        next.setVisibility(View.GONE);

        c1.setOnClickListener(step1choice1clickListenerButtons);
        c2.setOnClickListener(step1choice2clickListenerButtons);
        myDeck = new Deck();
        myDeck.shuffle();
        myDeck.display();
    }
    public void displayCard(Card card, int which){
        String string_value ;
        int value = card.value ;
        switch(value)
        {
            case 11 :
                string_value = "j" ;
                break ;
            case 12 :
                string_value = "q" ;
                break ;
            case 13 :
                string_value = "k" ;
                break ;
            case 14 :
                string_value = "1" ;
                break ;
            default :
                string_value = String.valueOf(value) ;
                break ;
        }
        int res = this.getResources().getIdentifier(card.flush + "_" + string_value,"drawable","comm.example.florian.pyramid");
        Log.d("DEBUG","displayCard - Card name : " + card.flush + "_" + string_value) ;
        Log.d("DEBUG","displayCard - res : " + res) ;
        // Set corresponding card image
        switch(which)
        {
            case 1 :
                card1.setVisibility(View.VISIBLE);
                card1.setImageResource(res);
                break ;
            case 2 :
                card2.setVisibility(View.VISIBLE);
                card2.setImageResource(res);
                break ;
            case 3 :
                card3.setVisibility(View.VISIBLE);
                card3.setImageResource(res);
                break ;
            case 4 :
                card4.setVisibility(View.VISIBLE);
                card4.setImageResource(res);
                break ;
            default :
                break ;
        }

    }
    private View.OnClickListener step1choice1clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","First step - Choice 1") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if((cardPicked.flush == "hearts")||(cardPicked.flush == "diamonds"))
            {
                // Winner
                challenge.setText("Winner ! Give one sip !") ;
                playerList.get(playerTour).sipGiven++ ;
            }
            else
            {
                // Looser
                challenge.setText("Looser ! Drink one sip !") ;
                playerList.get(playerTour).sipTaken++ ;
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step1choice2clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","First step - Choice 2") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if((cardPicked.flush == "hearts")||(cardPicked.flush == "diamonds"))
            {
                // Winner
                challenge.setText("Looser ! Drink one sip !") ;
                playerList.get(playerTour).sipTaken++ ;
            }
            else
            {
                // Looser
                challenge.setText("Winner ! Give one sip !") ;
                playerList.get(playerTour).sipGiven++ ;
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step2choice1clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Second step - Choice 1") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if(playerList.get(playerTour).privateDeck.get(0).value < cardPicked.value)
            {
                // Winner
                challenge.setText("Winner ! Give two sips !") ;
                playerList.get(playerTour).sipGiven += 2 ;
            }
            else if(playerList.get(playerTour).privateDeck.get(0).value > cardPicked.value)
            {
                // Winner
                challenge.setText("Looser ! Drink two sips !") ;
                playerList.get(playerTour).sipTaken += 2 ;
            }
            else
            {
                // Looser
                challenge.setText("Bad luuuck ! Drink four sips !") ;
                playerList.get(playerTour).sipTaken += 4 ;
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step2choice2clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Second step - Choice 2") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if(playerList.get(playerTour).privateDeck.get(0).value > cardPicked.value)
            {
                // Winner
                challenge.setText("Winner ! Give two sips !") ;
                playerList.get(playerTour).sipGiven += 2 ;
            }
            else if(playerList.get(playerTour).privateDeck.get(0).value < cardPicked.value)
            {
                // Winner
                challenge.setText("Looser ! Drink two sips !") ;
                playerList.get(playerTour).sipTaken += 2 ;
            }
            else
            {
                // Looser
                challenge.setText("Bad luuuck ! Drink four sips !") ;
                playerList.get(playerTour).sipTaken += 4 ;
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step3choice1clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Third step - Choice 1") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if(playerList.get(playerTour).privateDeck.get(0).value > playerList.get(playerTour).privateDeck.get(1).value) {
                if ((playerList.get(playerTour).privateDeck.get(0).value > cardPicked.value)&&(playerList.get(playerTour).privateDeck.get(1).value < cardPicked.value)) {
                    // Winner
                    challenge.setText("Winner ! Give three sips !");
                    playerList.get(playerTour).sipGiven += 3 ;
                } else if ((playerList.get(playerTour).privateDeck.get(0).value < cardPicked.value)||(playerList.get(playerTour).privateDeck.get(1).value > cardPicked.value)) {
                    // Winner
                    challenge.setText("Looser ! Drink three sips !");
                    playerList.get(playerTour).sipTaken += 3 ;
                } else {
                    // Looser
                    challenge.setText("Bad luuuck ! Drink six sips !");
                    playerList.get(playerTour).sipTaken += 6 ;
                }
            }
            else if(playerList.get(playerTour).privateDeck.get(0).value < playerList.get(playerTour).privateDeck.get(1).value)
            {
                if ((playerList.get(playerTour).privateDeck.get(0).value < cardPicked.value)&&(playerList.get(playerTour).privateDeck.get(1).value > cardPicked.value)) {
                    // Winner
                    challenge.setText("Winner ! Give three sips !");
                    playerList.get(playerTour).sipGiven += 3 ;
                } else if ((playerList.get(playerTour).privateDeck.get(0).value > cardPicked.value)||(playerList.get(playerTour).privateDeck.get(1).value < cardPicked.value)) {
                    // Winner
                    challenge.setText("Looser ! Drink three sips !");
                    playerList.get(playerTour).sipTaken += 3 ;
                } else {
                    // Looser
                    challenge.setText("Bad luuuck ! Drink six sips !");
                    playerList.get(playerTour).sipTaken += 6 ;
                }
            }
            else
            {
                if(playerList.get(playerTour).privateDeck.get(0).value != cardPicked.value)
                {
                    // Winner
                    challenge.setText("Gambler ! Drink six sips !");
                    playerList.get(playerTour).sipTaken += 6 ;
                }
                else
                {
                    // Looser
                    challenge.setText("EAZYY ! Give three sips !");
                    playerList.get(playerTour).sipGiven += 3 ;
                }
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step3choice2clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Third step - Choice 2") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if(playerList.get(playerTour).privateDeck.get(0).value > playerList.get(playerTour).privateDeck.get(1).value) {
                if ((playerList.get(playerTour).privateDeck.get(0).value > cardPicked.value)&&(playerList.get(playerTour).privateDeck.get(1).value < cardPicked.value)) {
                    // Looser
                    challenge.setText("Looser ! Drink three sips !");
                    playerList.get(playerTour).sipTaken += 3 ;
                } else if ((playerList.get(playerTour).privateDeck.get(0).value < cardPicked.value)||(playerList.get(playerTour).privateDeck.get(1).value > cardPicked.value)) {
                    // Winner
                    challenge.setText("Winner ! Give three sips !");
                    playerList.get(playerTour).sipGiven += 3 ;
                } else {
                    // Looser
                    challenge.setText("Bad luuuck ! Drink six sips !");
                    playerList.get(playerTour).sipTaken += 6 ;
                }
            }
            else if(playerList.get(playerTour).privateDeck.get(0).value < playerList.get(playerTour).privateDeck.get(1).value)
            {
                if ((playerList.get(playerTour).privateDeck.get(0).value < cardPicked.value)&&(playerList.get(playerTour).privateDeck.get(1).value > cardPicked.value)) {
                    // Looser
                    challenge.setText("Looser ! Drink three sips !");
                    playerList.get(playerTour).sipTaken += 3 ;
                } else if ((playerList.get(playerTour).privateDeck.get(0).value > cardPicked.value)||(playerList.get(playerTour).privateDeck.get(1).value < cardPicked.value)) {
                    // Winner
                    challenge.setText("Winner ! Give three sips !");
                    playerList.get(playerTour).sipGiven += 3 ;
                } else {
                    // Looser
                    challenge.setText("Bad luuuck ! Drink six sips !");
                    playerList.get(playerTour).sipTaken += 6 ;
                }
            }
            else
            {
                if(playerList.get(playerTour).privateDeck.get(0).value != cardPicked.value)
                {
                    // Winner
                    challenge.setText("Winner ! Give three sips !");
                    playerList.get(playerTour).sipGiven += 3 ;
                }
                else
                {
                    // Looser
                    challenge.setText("Bad luuuck ! Drink six sips !");
                    playerList.get(playerTour).sipTaken += 6 ;
                }
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step4choice1clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Second step - Choice 1") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if("hearts" == cardPicked.flush)
            {
                // Winner
                challenge.setText("Winner ! Give four sips !") ;
                playerList.get(playerTour).sipGiven += 4 ;
            }
            else
            {
                // Looser
                challenge.setText("Looser ! Drink four sips !") ;
                playerList.get(playerTour).sipTaken += 4 ;
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step4choice2clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Second step - Choice 2") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if("spades" == cardPicked.flush)
            {
                // Winner
                challenge.setText("Winner ! Give four sips !") ;
                playerList.get(playerTour).sipGiven += 4 ;
            }
            else
            {
                // Looser
                challenge.setText("Looser ! Drink four sips !") ;
                playerList.get(playerTour).sipTaken += 4 ;
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step4choice3clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Second step - Choice 1") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if("clubs" == cardPicked.flush)
            {
                // Winner
                challenge.setText("Winner ! Give four sips !") ;
                playerList.get(playerTour).sipGiven += 4 ;
            }
            else
            {
                // Looser
                challenge.setText("Looser ! Drink four sips !") ;
                playerList.get(playerTour).sipTaken += 4 ;
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener step4choice4clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Second step - Choice 2") ;
            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            // Display the card
            displayCard(cardPicked,step);
            // Give the card to the player
            playerList.get(playerTour).takeCard(cardPicked);
            // Hide buttons
            c1.setVisibility(View.GONE);
            c2.setVisibility(View.GONE);
            c3.setVisibility(View.GONE);
            c4.setVisibility(View.GONE);
            // Activate next button
            next.setVisibility(View.VISIBLE);
            // Determine whether it's won or lost
            if("diamonds" == cardPicked.flush)
            {
                // Winner
                challenge.setText("Winner ! Give four sips !") ;
                playerList.get(playerTour).sipGiven += 4 ;
            }
            else
            {
                // Looser
                challenge.setText("Looser ! Drink four sips !") ;
                playerList.get(playerTour).sipTaken += 4 ;
            }
            next.setOnClickListener(nextClickListenerButtons);
        }
    };
    private View.OnClickListener nextClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(playerTour < playerNumber-1)
            {
                playerTour++ ;
                switch(step)
                {
                    case 1 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("Red or Black ?") ;
                        card1.setVisibility(View.GONE);
                        c1.setVisibility(View.VISIBLE);
                        c2.setVisibility(View.VISIBLE);
                        next.setVisibility(View.GONE);
                        break ;
                    case 2 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("Above or Below ?") ;
                        displayCard(playerList.get(playerTour).privateDeck.get(0),1);
                        card1.setVisibility(View.VISIBLE);
                        card2.setVisibility(View.GONE);
                        c1.setVisibility(View.VISIBLE);
                        c2.setVisibility(View.VISIBLE);
                        next.setVisibility(View.GONE);
                        break ;
                    case 3 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("In or Out ?") ;
                        displayCard(playerList.get(playerTour).privateDeck.get(0),1);
                        displayCard(playerList.get(playerTour).privateDeck.get(1),2);
                        card1.setVisibility(View.VISIBLE);
                        card2.setVisibility(View.VISIBLE);
                        card3.setVisibility(View.GONE);
                        c1.setVisibility(View.VISIBLE);
                        c2.setVisibility(View.VISIBLE);
                        next.setVisibility(View.GONE);
                        break ;
                    case 4 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("Hold on and choose a flush !") ;
                        displayCard(playerList.get(playerTour).privateDeck.get(0),1);
                        displayCard(playerList.get(playerTour).privateDeck.get(1),2);
                        displayCard(playerList.get(playerTour).privateDeck.get(2),3);
                        card1.setVisibility(View.VISIBLE);
                        card2.setVisibility(View.VISIBLE);
                        card3.setVisibility(View.VISIBLE);
                        card4.setVisibility(View.GONE);
                        c1.setVisibility(View.VISIBLE);
                        c2.setVisibility(View.VISIBLE);
                        c3.setVisibility(View.VISIBLE);
                        c4.setVisibility(View.VISIBLE);
                        next.setVisibility(View.GONE);
                        break ;
                    case 5 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("Remember your card's value in order !") ;
                        displayCard(playerList.get(playerTour).privateDeck.get(0),1);
                        displayCard(playerList.get(playerTour).privateDeck.get(1),2);
                        displayCard(playerList.get(playerTour).privateDeck.get(2),3);
                        displayCard(playerList.get(playerTour).privateDeck.get(3),4);
                        card1.setVisibility(View.VISIBLE);
                        card2.setVisibility(View.VISIBLE);
                        card3.setVisibility(View.VISIBLE);
                        card4.setVisibility(View.VISIBLE);
                        break ;
                }
            }
            else
            {
                step++ ;
                playerTour = 0 ;
                switch(step)
                {
                    case 2 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("Above or Below ?") ;
                        displayCard(playerList.get(playerTour).privateDeck.get(0),1);
                        card1.setVisibility(View.VISIBLE);
                        c1.setVisibility(View.VISIBLE);
                        c1.setText("Above");
                        c2.setVisibility(View.VISIBLE);
                        c2.setText("Below");
                        c1.setOnClickListener(step2choice1clickListenerButtons);
                        c2.setOnClickListener(step2choice2clickListenerButtons);
                        next.setVisibility(View.GONE);
                        break ;
                    case 3 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("In or Out ?") ;
                        displayCard(playerList.get(playerTour).privateDeck.get(0),1);
                        displayCard(playerList.get(playerTour).privateDeck.get(1),2);
                        card1.setVisibility(View.VISIBLE);
                        card2.setVisibility(View.VISIBLE);
                        c1.setVisibility(View.VISIBLE);
                        c1.setText("In");
                        c2.setVisibility(View.VISIBLE);
                        c2.setText("Out");
                        c1.setOnClickListener(step3choice1clickListenerButtons);
                        c2.setOnClickListener(step3choice2clickListenerButtons);
                        next.setVisibility(View.GONE);
                        break ;
                    case 4 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("Hold on and choose a flush !") ;
                        displayCard(playerList.get(playerTour).privateDeck.get(0),1);
                        displayCard(playerList.get(playerTour).privateDeck.get(1),2);
                        displayCard(playerList.get(playerTour).privateDeck.get(2),3);
                        card1.setVisibility(View.VISIBLE);
                        card2.setVisibility(View.VISIBLE);
                        card3.setVisibility(View.VISIBLE);
                        c1.setVisibility(View.VISIBLE);
                        c1.setText("Hearts");
                        c2.setVisibility(View.VISIBLE);
                        c2.setText("Spades");
                        c3.setVisibility(View.VISIBLE);
                        c3.setText("Clubs");
                        c4.setVisibility(View.VISIBLE);
                        c4.setText("Diamonds");
                        c1.setOnClickListener(step4choice1clickListenerButtons);
                        c2.setOnClickListener(step4choice2clickListenerButtons);
                        c3.setOnClickListener(step4choice3clickListenerButtons);
                        c4.setOnClickListener(step4choice4clickListenerButtons);
                        next.setVisibility(View.GONE);
                        break ;
                    case 5 :
                        playerName.setText(playerList.get(playerTour).name);
                        challenge.setText("Remember your card's value in order !") ;
                        displayCard(playerList.get(playerTour).privateDeck.get(0),1);
                        displayCard(playerList.get(playerTour).privateDeck.get(1),2);
                        displayCard(playerList.get(playerTour).privateDeck.get(2),3);
                        displayCard(playerList.get(playerTour).privateDeck.get(3),4);
                        c1.setVisibility(View.GONE);
                        c2.setVisibility(View.GONE);
                        c3.setVisibility(View.GONE);
                        c4.setVisibility(View.GONE);
                        card1.setVisibility(View.VISIBLE);
                        card2.setVisibility(View.VISIBLE);
                        card3.setVisibility(View.VISIBLE);
                        card4.setVisibility(View.VISIBLE);
                        break ;
                    case 6 :
                        // Le premier paramètre est le nom de l'activité actuelle
                        // Le second est le nom de l'activité de destination
                        Intent secondActivity = new Intent(preliminaryTour.this, pyramidClass.class);

                        // On rajoute un extra
                        secondActivity.putExtra(LIST, playerList);
                        secondActivity.putExtra(DECK, myDeck);

                        // Puis on lance l'intent !
                        startActivity(secondActivity);
                        finish();
                        break ;
                }
            }
        }
    };

}
