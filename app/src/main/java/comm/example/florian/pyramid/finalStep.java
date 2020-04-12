package comm.example.florian.pyramid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class finalStep extends AppCompatActivity {
    private View.OnClickListener clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Final page - Click detected") ;

            // Le premier paramètre est le nom de l'activité actuelle
            // Le second est le nom de l'activité de destination
            Intent secondActivity = new Intent(finalStep.this, Welcome.class);

            // Puis on lance l'intent !
            startActivity(secondActivity);
            finish() ;
        }
    };

    ArrayList<Player> playerList ;
    Player tmpPlayer ;
    int playerNumber ;
    private TextView title = null ;
    private ListView listResult = null ;
    private Button go = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_step);
        Log.d("DEBUG","On Create executed") ;

        // On récupère l'intent qui a lancé cette activité
        Intent i = getIntent();

        // Player names verification
        playerList = i.getParcelableArrayListExtra(pyramidClass.LIST);

        playerNumber = playerList.size();
        for (int j = 0; j < playerNumber; j++) {
            Log.d("DEBUG", playerList.get(j).name);
        }

        title = findViewById(R.id.title);
        listResult = findViewById(R.id.list);
        go = findViewById(R.id.welcomepage);

        go.setOnClickListener(clickListenerButtons);

        title.setText("Congrats, You survived ! Your results :");

        List<String> stringList = new ArrayList<>();
        for (int j = 0; j < playerNumber; j++) {
            if(j == 0)
            {
                tmpPlayer = playerList.get(j) ;
                stringList.add(tmpPlayer.name + " - Sips given : " + tmpPlayer.sipGiven + " | Sips taken : " + tmpPlayer.sipTaken);
            }
            else if(j == 1)
            {
                tmpPlayer = playerList.get(j) ;
                stringList.add(tmpPlayer.name + " - Sips given : " + tmpPlayer.sipGiven + " | Sips taken : " + tmpPlayer.sipTaken);
            }
            else if(j == 2)
            {
                tmpPlayer = playerList.get(j) ;
                stringList.add(tmpPlayer.name + " - Sips given : " + tmpPlayer.sipGiven + " | Sips taken : " + tmpPlayer.sipTaken);
            }
            else if(j == 3)
            {
                tmpPlayer = playerList.get(j) ;
                stringList.add(tmpPlayer.name + " - Sips given : " + tmpPlayer.sipGiven + " | Sips taken : " + tmpPlayer.sipTaken);
            }
            else if(j == 4)
            {
                tmpPlayer = playerList.get(j) ;
                stringList.add(tmpPlayer.name + " - Sips given : " + tmpPlayer.sipGiven + " | Sips taken : " + tmpPlayer.sipTaken);
            }
            else if(j == 5)
            {
                tmpPlayer = playerList.get(j) ;
                stringList.add(tmpPlayer.name + " - Sips given : " + tmpPlayer.sipGiven + " | Sips taken : " + tmpPlayer.sipTaken);
            }
            else if(j == 6)
            {
                tmpPlayer = playerList.get(j) ;
                stringList.add(tmpPlayer.name + " - Sips given : " + tmpPlayer.sipGiven + " | Sips taken : " + tmpPlayer.sipTaken);
            }
            else if(j == 7)
            {
                tmpPlayer = playerList.get(j) ;
                stringList.add(tmpPlayer.name + " - Sips given : " + tmpPlayer.sipGiven + " | Sips taken : " + tmpPlayer.sipTaken);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        listResult.setAdapter(adapter);
    }

}
