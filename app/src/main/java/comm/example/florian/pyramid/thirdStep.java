package comm.example.florian.pyramid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class thirdStep extends AppCompatActivity {
    Player receivingPlayer ;
    Player givingPlayer ;
    int sipNumber ;
    Card lastCard ;
    private TextView playerName = null ;
    private TextView sip = null ;
    private TextView believing = null ;
    private Button yesbutton = null ;
    private Button nobutton = null ;
    private Button go = null ;
    public final static int CHOOSE_BUTTON_REQUEST = 0;
    public final static String RECEIVINGPLAYER = "comm.example.florian.pyramid.RECEIVINGPLAYER";
    public final static String GIVINGPLAYER = "comm.example.florian.pyramid.GIVINGPLAYER";
    public final static String SIPS = "comm.example.florian.pyramid.SIPS";
    public final static String CARD = "comm.example.florian.pyramid.CARD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_step);
        Log.d("DEBUG","On Create executed") ;

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Player names verification
        receivingPlayer = i.getParcelableExtra(secondStep.RECEIVINGPLAYER);
        givingPlayer = i.getParcelableExtra(secondStep.GIVINGPLAYER);
        sipNumber = i.getIntExtra(secondStep.SIPS, 0);
        lastCard = i.getParcelableExtra(secondStep.CARD);
        Log.d("DEBUG","thirdStep - Card received : " + lastCard.flush + lastCard.value) ;

        playerName = findViewById(R.id.player_name);
        sip = findViewById(R.id.challenge);
        believing = findViewById(R.id.question);
        yesbutton = findViewById(R.id.yes_button);
        nobutton = findViewById(R.id.no_button);
        go = findViewById(R.id.go);
        go.setVisibility(View.GONE);
        go.setText("Continue");
        go.setOnClickListener(goClickListenerButtons);

        playerName.setText(receivingPlayer.name);
        sip.setText("You have received " + sipNumber + " drinks !");
        believing.setText("Do you believe " + givingPlayer.name + " ?");
        yesbutton.setOnClickListener(yesClickListenerButtons);
        nobutton.setOnClickListener(noClickListenerButtons);
}
    private View.OnClickListener yesClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "thirdStep page - Yes Click detected");
            believing.setVisibility(View.GONE);
            sip.setText("Drink " + sipNumber + " sips !");
            receivingPlayer.sipTaken += sipNumber ;
            givingPlayer.sipGiven += sipNumber ;
            go.setVisibility(View.VISIBLE);
            yesbutton.setVisibility(View.GONE);
            nobutton.setVisibility(View.GONE);
        }
    };
    private View.OnClickListener noClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "thirdStep page - No Click detected");
            Intent secondActivity = new Intent(thirdStep.this, fourthStep.class);
            // On rajoute un extra
            secondActivity.putExtra(RECEIVINGPLAYER, receivingPlayer);
            secondActivity.putExtra(GIVINGPLAYER, givingPlayer);
            secondActivity.putExtra(SIPS, sipNumber);
            secondActivity.putExtra(CARD, lastCard);
            Log.d("DEBUG","Third step - Sending sips : " + sipNumber) ;
            Log.d("DEBUG","Third step - Sending card : " + lastCard.flush + lastCard.value) ;
            // On associe l'identifiant à notre intent
            startActivityForResult(secondActivity, CHOOSE_BUTTON_REQUEST);
        }
    };
    private View.OnClickListener goClickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG", "thirdStep page - Go Click detected");
            setResult(RESULT_OK);
            finish();
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == CHOOSE_BUTTON_REQUEST) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // On affiche le bouton qui a été choisi
                setResult(RESULT_OK);
                finish();
            }
        }
    }

}
