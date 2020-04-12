package comm.example.florian.pyramid;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Player implements Parcelable{
    // Player name
    String name ;
    // Card's deck
    ArrayList<Card> privateDeck ;
    // Records
    int sipTaken ;
    int sipGiven ;
    // Constructor
    public Player(){
        this.name = "Unknown" ;
        this.privateDeck = new ArrayList<>() ;
    }
    public Player(String name){
        this.name = name ;
        this.privateDeck = new ArrayList<>() ;
        this.sipTaken = 0 ;
        this.sipGiven = 0 ;
    }
    public void takeCard(Card tempCard)
    {
        this.privateDeck.add(tempCard);
    }
    public String toString(){
        return "Name : " + this.name ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeList(this.privateDeck);
        dest.writeInt(this.sipTaken);
        dest.writeInt(this.sipGiven);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public Player(Parcel in) {
        name = in.readString();
        privateDeck = in.readArrayList(Card.class.getClassLoader());
        sipTaken = in.readInt() ;
        sipGiven = in.readInt() ;
    }

}
