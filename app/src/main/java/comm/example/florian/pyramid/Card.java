package comm.example.florian.pyramid;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Card implements Parcelable{
    // Card value
    int value ;
    // Card flush
    String flush ;

    public Card(){
        Log.d("DEBUG","Card creation") ;
        this.value = 0 ;
        this.flush = "Unknown" ;
    }
    public Card(int val, String flu){
        Log.d("DEBUG","Card creation with parameters") ;
        this.value = val ;
        this.flush = flu ;
    }
    public String toString() {
        Log.d("DEBUG","Value : " + this.value + " - Flush : " + this.flush) ;
        return "Value " + this.value +
                " : Flush " + this.flush ;
    }
    public int getResource(Context context){
        String string_value ;
        switch(this.value)
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
        //
        int res = context.getResources().getIdentifier(flush + "_" + string_value,"drawable","comm.example.florian.pyramid");
        return res ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.value);
        dest.writeString(this.flush);
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public Card(Parcel in) {
        value = in.readInt();
        flush = in.readString();
    }

}
