package comm.example.florian.pyramid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class fourthStep extends AppCompatActivity {
    private TextView playerName = null ;
    private ImageView currentCard = null ;
    private TextView desc = null ;
    private ImageButton card1 = null ;
    private ImageButton card2 = null ;
    private ImageButton card3 = null ;
    private ImageButton card4 = null ;
    private Button go = null ;

    Player receivingPlayer ;
    Player givingPlayer ;
    Card lastCard ;
    int sipNumber ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_step);
        Log.d("DEBUG", "On Create executed");

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Player names verification
        receivingPlayer = i.getParcelableExtra(thirdStep.RECEIVINGPLAYER);
        givingPlayer = i.getParcelableExtra(thirdStep.GIVINGPLAYER);
        sipNumber = i.getIntExtra(thirdStep.SIPS, 0);
        lastCard = i.getParcelableExtra(thirdStep.CARD);
        Log.d("DEBUG", "Fourth step - Receiving player : " + receivingPlayer.name);
        Log.d("DEBUG", "Fourth step - Giving player : " + givingPlayer.name);
        Log.d("DEBUG", "Fourth step - Sip number : " + sipNumber);
        Log.d("DEBUG", "Fourth step - Card received : " + lastCard.flush + lastCard.value);

        playerName = findViewById(R.id.player_name);
        currentCard = findViewById(R.id.maincard);
        desc = findViewById(R.id.challenge);
        card1 = findViewById(R.id.card1_button);
        card2 = findViewById(R.id.card2_button);
        card3 = findViewById(R.id.card3_button);
        card4 = findViewById(R.id.card4_button);
        go = findViewById(R.id.go);
        go.setVisibility(View.GONE);
        go.setOnClickListener(goClickListenerButtons);
        playerName.setText(givingPlayer.name);
        displayCard(lastCard,0);
        card1.setImageResource(R.drawable.back_p);
        card2.setImageResource(R.drawable.back_p);
        card3.setImageResource(R.drawable.back_p);
        card4.setImageResource(R.drawable.back_p);
        card1.setOnClickListener(card1ClickListenerButtons);
        card2.setOnClickListener(card2ClickListenerButtons);
        card3.setOnClickListener(card3ClickListenerButtons);
        card4.setOnClickListener(card4ClickListenerButtons);
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
            case 0 :
                currentCard.setImageResource(res);
                break ;
            case 1 :
                card1.setImageResource(res);
                break ;
            case 2 :
                card2.setImageResource(res);
                break ;
            case 3 :
                card3.setImageResource(res);
                break ;
            case 4 :
                card4.setImageResource(res);
                break ;
            default :
                break ;
        }

    }
    private View.OnClickListener goClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "fourthStep page - Go Click detected");
            setResult(RESULT_OK);
            finish();

        }
    };
    private View.OnClickListener card1ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "fourthStep page - Card 1 Click detected");
            Card chosenCard = givingPlayer.privateDeck.get(0) ;
            displayCard(chosenCard,1);
            if(chosenCard.value==lastCard.value)
            {
                // Winner
                desc.setText("Nice one ! Give " + sipNumber*2 + " to " + receivingPlayer.name);
                givingPlayer.sipGiven += sipNumber*2 ;
            }
            else
            {
                // Looser
                desc.setText("Liar..... You drink " + sipNumber*2 + " sips !");
                givingPlayer.sipTaken += sipNumber*2 ;
            }
            card1.setOnClickListener(emptyClickListenerButtons);
            card2.setOnClickListener(emptyClickListenerButtons);
            card3.setOnClickListener(emptyClickListenerButtons);
            card4.setOnClickListener(emptyClickListenerButtons);
            go.setVisibility(View.VISIBLE);
        }
    };
    private View.OnClickListener card2ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "fourthStep page - Card 2 Click detected");
            Card chosenCard = givingPlayer.privateDeck.get(1) ;
            displayCard(chosenCard,2);
            if(chosenCard.value==lastCard.value)
            {
                // Winner
                desc.setText("Nice one ! Give " + sipNumber*2 + " to " + receivingPlayer.name);
                givingPlayer.sipGiven += sipNumber*2 ;
            }
            else
            {
                // Looser
                desc.setText("Liar..... You drink " + sipNumber*2 + " sips !");
                givingPlayer.sipTaken += sipNumber*2 ;
            }
            card1.setOnClickListener(emptyClickListenerButtons);
            card2.setOnClickListener(emptyClickListenerButtons);
            card3.setOnClickListener(emptyClickListenerButtons);
            card4.setOnClickListener(emptyClickListenerButtons);
            go.setVisibility(View.VISIBLE);
        }
    };
    private View.OnClickListener card3ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "fourthStep page - Card 3 Click detected");
            Card chosenCard = givingPlayer.privateDeck.get(2) ;
            displayCard(chosenCard,3);
            if(chosenCard.value==lastCard.value)
            {
                // Winner
                desc.setText("Nice one ! Give " + sipNumber*2 + " to " + receivingPlayer.name);
                givingPlayer.sipGiven += sipNumber*2 ;
            }
            else
            {
                // Looser
                desc.setText("Liar..... You drink " + sipNumber*2 + " sips !");
                givingPlayer.sipTaken += sipNumber*2 ;
            }
            card1.setOnClickListener(emptyClickListenerButtons);
            card2.setOnClickListener(emptyClickListenerButtons);
            card3.setOnClickListener(emptyClickListenerButtons);
            card4.setOnClickListener(emptyClickListenerButtons);
            go.setVisibility(View.VISIBLE);
        }
    };
    private View.OnClickListener card4ClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "fourthStep page - Card 4 Click detected");
            Card chosenCard = givingPlayer.privateDeck.get(3) ;
            displayCard(chosenCard,4);
            if(chosenCard.value==lastCard.value)
            {
                // Winner
                desc.setText("Nice one ! Give " + sipNumber*2 + " to " + receivingPlayer.name);
                givingPlayer.sipGiven += sipNumber*2 ;
            }
            else
            {
                // Looser
                desc.setText("Liar..... You drink " + sipNumber*2 + " sips !");
                givingPlayer.sipTaken += sipNumber*2 ;
            }
            card1.setOnClickListener(emptyClickListenerButtons);
            card2.setOnClickListener(emptyClickListenerButtons);
            card3.setOnClickListener(emptyClickListenerButtons);
            card4.setOnClickListener(emptyClickListenerButtons);
            go.setVisibility(View.VISIBLE);
        }
    };
    private View.OnClickListener emptyClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "fourthStep page - Empty Click detected");
        }
    };
}
