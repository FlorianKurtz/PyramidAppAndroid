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


public class secondStep extends AppCompatActivity {
    ArrayList<Player> playerList ;
    Card lastCard ;
    Deck myDeck ;
    int sipNumber ;
    int playerNumber ;
    int playerTour = 0 ;
    int tour ;
    private TextView playerName = null ;
    private TextView sipNumberView = null ;
    private TextView haveyou = null ;
    private ImageView currentCard = null ;
    private Button yesbutton = null ;
    private Button nobutton = null ;
    private Button p1 = null ;
    private Button p2 = null ;
    private Button p3 = null ;
    private Button p4 = null ;
    private Button p5 = null ;
    private Button p6 = null ;
    private Button p7 = null ;
    private Button p8 = null ;
    public final static String RECEIVINGPLAYER = "comm.example.florian.pyramid.RECEIVINGPLAYER";
    public final static String GIVINGPLAYER = "comm.example.florian.pyramid.GIVINGPLAYER";
    public final static String SIPS = "comm.example.florian.pyramid.SIPS";
    public final static String CARD = "comm.example.florian.pyramid.CARD";
    public final static int CHOOSE_BUTTON_REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_step);
        Log.d("DEBUG","On Create executed") ;

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Player names verification
        playerList = i.getParcelableArrayListExtra(pyramidClass.LIST);
        myDeck = i.getParcelableExtra(pyramidClass.DECK);
        lastCard = i.getParcelableExtra(pyramidClass.CARD);
        tour = i.getIntExtra(pyramidClass.TOUR,0);
        playerNumber = playerList.size();
        for (int j = 0; j < playerNumber; j++) {
            Log.d("DEBUG", playerList.get(j).name);
        }
        Log.d("DEBUG", "Left cards : " + myDeck.mainDeck.size());
        Log.d("DEBUG", "Last card : " + lastCard.flush + lastCard.value);

        playerName = findViewById(R.id.player_name);
        sipNumberView = findViewById(R.id.challenge) ;
        haveyou = findViewById(R.id.question);
        currentCard = findViewById(R.id.card) ;
        yesbutton = findViewById(R.id.yes_button);
        nobutton = findViewById(R.id.no_button);

        p1 = findViewById(R.id.player1_button);
        p1.setVisibility(View.GONE);
        p2 = findViewById(R.id.player2_button);
        p2.setVisibility(View.GONE);
        p3 = findViewById(R.id.player3_button);
        p3.setVisibility(View.GONE);
        p4 = findViewById(R.id.player4_button);
        p4.setVisibility(View.GONE);
        p5 = findViewById(R.id.player5_button);
        p5.setVisibility(View.GONE);
        p6 = findViewById(R.id.player6_button);
        p6.setVisibility(View.GONE);
        p7 = findViewById(R.id.player7_button);
        p7.setVisibility(View.GONE);
        p8 = findViewById(R.id.player8_button);
        p8.setVisibility(View.GONE);

        for(int j = 0 ; j < playerNumber -1 ; j++)
        {
            switch(j)
            {
                case 0 :
                    p1.setText(playerList.get(j).name);
                    break ;
                case 1 :
                    p2.setText(playerList.get(j).name);
                    break ;
                case 2 :
                    p3.setText(playerList.get(j).name);
                    break ;
                case 3 :
                    p4.setText(playerList.get(j).name);
                    break ;
                case 4 :
                    p5.setText(playerList.get(j).name);
                    break ;
                case 5 :
                    p6.setText(playerList.get(j).name);
                    break ;
                case 6 :
                    p7.setText(playerList.get(j).name);
                    break ;
                case 7 :
                    p8.setText(playerList.get(j).name);
                    break ;
            }
        }

        if(tour < 5) {
            sipNumber = 1;
        }
        else if((tour >= 5)&&(tour < 9))
        {
            sipNumber = 2 ;
        }
        else if((tour >= 9)&&(tour < 12))
        {
            sipNumber = 3 ;
        }
        else if((tour >= 12)&&(tour < 14))
        {
            sipNumber = 4 ;
        }
        else{
            sipNumber = 5 ;
        }

        playerName.setText(playerList.get(playerTour).name);
        displayCard(lastCard);
        sipNumberView.setText("For " + sipNumber + " sip");
        yesbutton.setOnClickListener(yesClickListenerButtons);
        nobutton.setOnClickListener(noClickListenerButtons);
    }
    private View.OnClickListener yesClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","secondStep page - Yes Click detected") ;
            for(int j = 0 ; j < playerNumber  ; j++)
            {
                switch(j)
                {
                    case 0 :
                        p1.setVisibility(View.VISIBLE);
                        p1.setOnClickListener(p1ClickListenerButtons);
                        break ;
                    case 1 :
                        p2.setVisibility(View.VISIBLE);
                        p2.setOnClickListener(p2ClickListenerButtons);
                        break ;
                    case 2 :
                        p3.setVisibility(View.VISIBLE);
                        p3.setOnClickListener(p3ClickListenerButtons);
                        break ;
                    case 3 :
                        p4.setVisibility(View.VISIBLE);
                        p4.setOnClickListener(p4ClickListenerButtons);
                        break ;
                    case 4 :
                        p5.setVisibility(View.VISIBLE);
                        p5.setOnClickListener(p5ClickListenerButtons);
                        break ;
                    case 5 :
                        p6.setVisibility(View.VISIBLE);
                        p6.setOnClickListener(p6ClickListenerButtons);
                        break ;
                    case 6 :
                        p7.setVisibility(View.VISIBLE);
                        p7.setOnClickListener(p7ClickListenerButtons);
                        break ;
                    case 7 :
                        p8.setVisibility(View.VISIBLE);
                        p8.setOnClickListener(p8ClickListenerButtons);
                        break ;
                }
            }
            yesbutton.setVisibility(View.GONE);
            nobutton.setVisibility(View.GONE);
            haveyou.setVisibility(View.GONE);
            currentCard.setVisibility(View.GONE);
        }
    };
    private View.OnClickListener noClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","secondStep page - No Click detected") ;
            if(playerTour < playerNumber -1) {
                playerTour++;
                playerName.setText(playerList.get(playerTour).name);
            }
            else
            {
                setResult(RESULT_OK);
                finish();
            }

        }
    };
    private View.OnClickListener p1ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","secondStep page - p1 Click detected") ;

            int sipNumber;
            if (tour < 5) {
                sipNumber = 1;
            } else if ((tour >= 5) && (tour < 9)) {
                sipNumber = 2;
            } else if ((tour >= 9) && (tour < 12)) {
                sipNumber = 3;
            } else if ((tour >= 12) && (tour < 14)) {
                sipNumber = 4;
            } else {
                sipNumber = 5;
            }

            Player receivingPlayer ;
            receivingPlayer = playerList.get(0) ;

            Player givingPlayer ;
            givingPlayer = playerList.get(playerTour) ;

            Intent secondActivity = new Intent(secondStep.this, thirdStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS,sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
    private View.OnClickListener p2ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","secondStep page - p2 Click detected") ;

            int sipNumber;
            if (tour < 5) {
                sipNumber = 1;
            } else if ((tour >= 5) && (tour < 9)) {
                sipNumber = 2;
            } else if ((tour >= 9) && (tour < 12)) {
                sipNumber = 3;
            } else if ((tour >= 12) && (tour < 14)) {
                sipNumber = 4;
            } else {
                sipNumber = 5;
            }

            Player receivingPlayer ;
            receivingPlayer = playerList.get(1) ;

            Player givingPlayer ;
            givingPlayer = playerList.get(playerTour) ;

            Intent secondActivity = new Intent(secondStep.this, thirdStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS,sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
    private View.OnClickListener p3ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "secondStep page - p3 Click detected");

            int sipNumber;
            if (tour < 5) {
                sipNumber = 1;
            } else if ((tour >= 5) && (tour < 9)) {
                sipNumber = 2;
            } else if ((tour >= 9) && (tour < 12)) {
                sipNumber = 3;
            } else if ((tour >= 12) && (tour < 14)) {
                sipNumber = 4;
            } else {
                sipNumber = 5;
            }

            Player receivingPlayer;
            receivingPlayer = playerList.get(2);

            Player givingPlayer;
            givingPlayer = playerList.get(playerTour);

            Intent secondActivity = new Intent(secondStep.this, thirdStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS, sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
    private View.OnClickListener p4ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","secondStep page - p4 Click detected") ;

            int sipNumber;
            if (tour < 5) {
                sipNumber = 1;
            } else if ((tour >= 5) && (tour < 9)) {
                sipNumber = 2;
            } else if ((tour >= 9) && (tour < 12)) {
                sipNumber = 3;
            } else if ((tour >= 12) && (tour < 14)) {
                sipNumber = 4;
            } else {
                sipNumber = 5;
            }

            Player receivingPlayer ;
            receivingPlayer = playerList.get(3) ;

            Player givingPlayer ;
            givingPlayer = playerList.get(playerTour) ;

            Intent secondActivity = new Intent(secondStep.this, thirdStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS,sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
    private View.OnClickListener p5ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","secondStep page - p5 Click detected") ;

            int sipNumber;
            if (tour < 5) {
                sipNumber = 1;
            } else if ((tour >= 5) && (tour < 9)) {
                sipNumber = 2;
            } else if ((tour >= 9) && (tour < 12)) {
                sipNumber = 3;
            } else if ((tour >= 12) && (tour < 14)) {
                sipNumber = 4;
            } else {
                sipNumber = 5;
            }

            Player receivingPlayer ;
            receivingPlayer = playerList.get(4) ;

            Player givingPlayer ;
            givingPlayer = playerList.get(playerTour) ;

            Intent secondActivity = new Intent(secondStep.this, thirdStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS,sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
    private View.OnClickListener p6ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "secondStep page - p6 Click detected");

            int sipNumber;
            if (tour < 5) {
                sipNumber = 1;
            } else if ((tour >= 5) && (tour < 9)) {
                sipNumber = 2;
            } else if ((tour >= 9) && (tour < 12)) {
                sipNumber = 3;
            } else if ((tour >= 12) && (tour < 14)) {
                sipNumber = 4;
            } else {
                sipNumber = 5;
            }

            Player receivingPlayer;
            receivingPlayer = playerList.get(5);

            Player givingPlayer;
            givingPlayer = playerList.get(playerTour);

            Intent secondActivity = new Intent(secondStep.this, thirdStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS, sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
    private View.OnClickListener p7ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "secondStep page - p7 Click detected");

            int sipNumber;
            if (tour < 5) {
                sipNumber = 1;
            } else if ((tour >= 5) && (tour < 9)) {
                sipNumber = 2;
            } else if ((tour >= 9) && (tour < 12)) {
                sipNumber = 3;
            } else if ((tour >= 12) && (tour < 14)) {
                sipNumber = 4;
            } else {
                sipNumber = 5;
            }

            Player receivingPlayer;
            receivingPlayer = playerList.get(6);

            Player givingPlayer;
            givingPlayer = playerList.get(playerTour);

            Intent secondActivity = new Intent(secondStep.this, thirdStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS, sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
    private View.OnClickListener p8ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "secondStep page - p8 Click detected");

            int sipNumber;
            if (tour < 5) {
                sipNumber = 1;
            } else if ((tour >= 5) && (tour < 9)) {
                sipNumber = 2;
            } else if ((tour >= 9) && (tour < 12)) {
                sipNumber = 3;
            } else if ((tour >= 12) && (tour < 14)) {
                sipNumber = 4;
            } else {
                sipNumber = 5;
            }

            Player receivingPlayer;
            receivingPlayer = playerList.get(7);

            Player givingPlayer;
            givingPlayer = playerList.get(playerTour);

            Intent secondActivity = new Intent(secondStep.this, thirdStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS, sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };

    public void displayCard(Card card){
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
        currentCard.setImageResource(res);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == CHOOSE_BUTTON_REQUEST) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // On affiche le bouton qui a été choisi
                if(playerTour < playerNumber -1) {
                    playerTour++;
                    playerName.setText(playerList.get(playerTour).name);
                    yesbutton.setVisibility(View.VISIBLE);
                    nobutton.setVisibility(View.VISIBLE);
                    currentCard.setVisibility(View.VISIBLE);
                    haveyou.setVisibility(View.VISIBLE);

                    p1.setVisibility(View.GONE);
                    p2.setVisibility(View.GONE);
                    p3.setVisibility(View.GONE);
                    p4.setVisibility(View.GONE);
                    p5.setVisibility(View.GONE);
                    p6.setVisibility(View.GONE);
                    p7.setVisibility(View.GONE);
                    p8.setVisibility(View.GONE);
                }
                else
                {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }
    }
}
