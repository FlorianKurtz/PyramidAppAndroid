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

public class pyramidClass extends AppCompatActivity {
    int playerNumber ;
    int tour = 0 ;
    // L'identifiant de notre requête
    public final static int CHOOSE_BUTTON_REQUEST = 0;
    public final static String LIST = "comm.example.florian.pyramid.LIST";
    public final static String DECK = "comm.example.florian.pyramid.DECK";
    public final static String CARD = "comm.example.florian.pyramid.CARD";
    public final static String TOUR = "comm.example.florian.pyramid.TOUR";
    ArrayList<Player> playerList ;
    ArrayList<Card> cardsPicked ;
    Deck myDeck ;
    private TextView title = null ;
    private Button b = null ;
    private ImageView card1 ;
    private ImageView card2 = null ;
    private ImageView card3 = null ;
    private ImageView card4 = null ;
    private ImageView card5 = null ;
    private ImageView card6 = null ;
    private ImageView card7 = null ;
    private ImageView card8 = null ;
    private ImageView card9 = null ;
    private ImageView card10 = null ;
    private ImageView card11 = null ;
    private ImageView card12 = null ;
    private ImageView card13 = null ;
    private ImageView card14 = null ;
    private ImageView card15 = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pyramidlayout);
        Log.d("DEBUG","On Create executed") ;

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Player names verification
        playerList = i.getParcelableArrayListExtra(preliminaryTour.LIST);
        myDeck = i.getParcelableExtra(preliminaryTour.DECK);
        playerNumber = playerList.size();
        for (int j = 0; j < playerNumber; j++) {
            Log.d("DEBUG", playerList.get(j).name);
        }
        Log.d("DEBUG", "Left cards : " + myDeck.mainDeck.size());

        // Get the button and the image views + text view


        title = findViewById(R.id.pyramid_challenge) ;
        b = findViewById(R.id.gobutton) ;
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        card10 = findViewById(R.id.card10);
        card11 = findViewById(R.id.card11);
        card12 = findViewById(R.id.card12);
        card13 = findViewById(R.id.card13);
        card14 = findViewById(R.id.card14);
        card15 = findViewById(R.id.card15);

        b.setText("Reveal a card");

        cardsPicked = new ArrayList<>() ;

        // Put callback on next button
        b.setOnClickListener(revealClickListenerButtons);
    }

    private View.OnClickListener revealClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Log.d("DEBUG","Pyramid page - Reveal Click detected") ;

            // Pick a card
            Card cardPicked = myDeck.pickCard() ;
            cardsPicked.add(cardPicked) ;
            displayCard(cardPicked,tour+1);
            b.setOnClickListener(changeClickListenerButtons);
            b.setText("Let\'s gamble !");
        }
    };
    private View.OnClickListener changeClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Log.d("DEBUG","Pyramid page - Change Click detected") ;

            int index ; Card lastCard ;
            // Get the last card picked
            index = cardsPicked.size() -1 ;
            lastCard = cardsPicked.get(index) ;

            Intent secondActivity = new Intent(pyramidClass.this, secondStep.class);
            // On rajoute un extra
            secondActivity.putExtra(LIST, playerList);
            secondActivity.putExtra(DECK, myDeck);
            secondActivity.putExtra(CARD,lastCard);
            secondActivity.putExtra(TOUR,tour);
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
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
            case 5 :
                card5.setImageResource(res);
                break ;
            case 6 :
                card6.setImageResource(res);
                break ;
            case 7 :
                card7.setImageResource(res);
                break ;
            case 8 :
                card8.setImageResource(res);
                break ;
            case 9 :
                card9.setImageResource(res);
                break ;
            case 10 :
                card10.setImageResource(res);
                break ;
            case 11 :
                card11.setImageResource(res);
                break ;
            case 12 :
                card12.setImageResource(res);
                break ;
            case 13 :
                card13.setImageResource(res);
                break ;
            case 14 :
                card14.setImageResource(res);
                break ;
            case 15 :
                card15.setImageResource(res);
                break ;
            default :
                break ;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == CHOOSE_BUTTON_REQUEST) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // On affiche le bouton qui a été choisi
                tour++ ;
                Log.d("DEBUG","pyramidClass - Tour number : " + tour) ;
                b.setText("Reveal a card");
                b.setOnClickListener(revealClickListenerButtons);
                if(tour < 5) {
                    title.setText("For one sip...");
                }
                else if((tour >= 5)&&(tour < 9))
                {
                    title.setText("For two sips...");
                }
                else if((tour >= 9)&&(tour < 12))
                {
                    title.setText("For three sips...");
                }
                else if((tour >= 12)&&(tour < 14))
                {
                    title.setText("For four sip...");
                }
                else if(tour == 14){
                    title.setText("For your all glass !");
                }
                else
                {
                    Intent secondActivity = new Intent(pyramidClass.this, finalStep.class);
                    // On rajoute un extra
                    secondActivity.putExtra(LIST, playerList);

                    // On associe l'identifiant à notre intent
                    startActivity(secondActivity);
                    finish() ;
                }
            }
        }
    }
}
