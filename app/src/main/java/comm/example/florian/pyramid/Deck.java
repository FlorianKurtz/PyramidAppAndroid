package comm.example.florian.pyramid;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements Parcelable{
    // Card's deck
    ArrayList<Card> mainDeck ;

    // Constructor
    public Deck(){
        this.mainDeck = new ArrayList<>();
        int val ;
        String flu = "" ;
        for(int i = 2; i < 15 ; i++) {
            val = i ;
            for (int j = 0; j < 4; j++) {
                switch(j)
                {
                    case 0 :
                        flu = "diamonds" ;
                        break ;
                    case 1 :
                        flu = "hearts" ;
                        break ;
                    case 2 :
                        flu = "spades" ;
                        break ;
                    case 3 :
                        flu = "clubs" ;
                        break ;
                    default :
                        break ;
                }
                Card tempCard = new Card(val,flu) ;
                mainDeck.add(tempCard) ;
            }
        }
    }
    public void shuffle(){
        Collections.shuffle(this.mainDeck) ;
    }
    public Card pickCard(){
        int index = this.mainDeck.size() ;
        Log.d("DEBUG","Deck - Left cards : " + index) ;
        Card tempCard = this.mainDeck.get(index-1) ;
        Log.d("DEBUG","Deck - Picked card : " + tempCard.flush + tempCard.value) ;
        this.mainDeck.remove(index-1) ;
        return tempCard ;
    }
    public void display() {
        for(int i = 0 ; i < mainDeck.size() ; i++){
            this.mainDeck.get(i).toString() ;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mainDeck);
    }

    public static final Parcelable.Creator<Deck> CREATOR = new Parcelable.Creator<Deck>() {
        @Override
        public Deck createFromParcel(Parcel source) {
            return new Deck(source);
        }

        @Override
        public Deck[] newArray(int size) {
            return new Deck[size];
        }
    };

    public Deck(Parcel in) {
        mainDeck = in.readArrayList(Card.class.getClassLoader());
    }
}
