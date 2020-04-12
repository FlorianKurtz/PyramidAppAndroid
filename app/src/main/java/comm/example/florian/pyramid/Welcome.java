package comm.example.florian.pyramid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class Welcome extends AppCompatActivity {

    private View.OnClickListener clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","Welcome page - Click detected") ;

            // Le premier paramètre est le nom de l'activité actuelle
            // Le second est le nom de l'activité de destination
            Intent secondActivity = new Intent(Welcome.this, setupPlayer.class);

            // Puis on lance l'intent !
            startActivity(secondActivity);
        }
    };

    private ImageButton b = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Log.d("DEBUG","On Create executed") ;

        b = findViewById(R.id.begin);
        b.setOnClickListener(clickListenerButtons);
    }

}
