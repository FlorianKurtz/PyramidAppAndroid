package comm.example.florian.pyramid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SeekBar;
import java.util.ArrayList;

public class setupPlayer extends AppCompatActivity {

    private View.OnClickListener clickListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","setupPlayer page - Click detected") ;

            // Player list creation
            ArrayList<Player> playerList = new ArrayList<>() ;
            // Get the player number
            int playerNumber = myAdapter.getCount() ;
            // For each player, create a Player object and put it in the list
            for(int i = 0 ; i < playerNumber ; i++){
                Log.d("DEBUG",(String)myAdapter.getItem(i)) ;
                Player p = new Player((String)myAdapter.getItem(i));
                playerList.add(p);
            }

            // Le premier paramètre est le nom de l'activité actuelle
            // Le second est le nom de l'activité de destination
            Intent secondActivity = new Intent(setupPlayer.this, preliminaryTour.class);

            // On rajoute un extra
            secondActivity.putExtra(LIST, playerList);

            // Puis on lance l'intent !
            startActivity(secondActivity);
        }
    };

    private View.OnClickListener infoListenerButtons = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /* Réagir au clic pour les boutons 1 et 2*/
            Log.d("DEBUG","setupPlayer page - Info Click detected") ;

            // Le premier paramètre est le nom de l'activité actuelle
            // Le second est le nom de l'activité de destination
            Intent secondActivity = new Intent(setupPlayer.this, rulePage.class);

            // Puis on lance l'intent !
            startActivity(secondActivity);
        }
    };
    public final static String LIST = "comm.example.florian.pyramid.LIST";

    private Button b = null;
    private Button info = null ;
    private SeekBar s = null ;

    /** Affichage de la liste des sexes **/
    private GridView PlayerList = null;
    private MyAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_player);
        Log.d("DEBUG","On Create executed") ;


        b = findViewById(R.id.gobutton);
        s = findViewById(R.id.seekBar);
        info = findViewById(R.id.rules);

        b.setOnClickListener(clickListenerButtons);
        info.setOnClickListener(infoListenerButtons);

        PlayerList = findViewById(R.id.grid);
        myAdapter = new MyAdapter() ;
        PlayerList.setAdapter(myAdapter);

        // perform seek bar change listener event used for getting the progress value
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int min = 1 ;
            int progressChangedValue = 1;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress + min;
                Log.d("DEBUG","SeekBar - Value changed to : " + progressChangedValue) ;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                myAdapter.update(progressChangedValue);
            }
        });
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        public ArrayList<String> myItems = new ArrayList();

        public MyAdapter() {
            int num_player ;
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i = 0; i < 8; i++) {
                num_player = i + 1 ;
                myItems.add("Player "+ num_player);
            }
            notifyDataSetChanged();
        }

        public void update(int playerNumber){
            int num_player ;
            myItems.clear();
            for (int i = 0; i < playerNumber; i++) {
                num_player = i + 1 ;
                myItems.add("Player "+num_player);
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            return myItems.size();
        }

        public Object getItem(int position) {
            return myItems.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item, null);
                holder.playerName = convertView
                        .findViewById(R.id.ItemText);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //Fill EditText with the value you have in data source
            holder.playerName.setText(myItems.get(position));
            holder.playerName.setId(position);

            //we need to update adapter once we finish with editing
            holder.playerName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        final int position = v.getId();
                        final EditText Caption = (EditText) v;
                        myItems.set(position,Caption.getText().toString());
                    }
                }
            });
            return convertView;
        }
    }

    static class ViewHolder {
        public EditText playerName;
    }

}
