package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;


/**
 * Created by thekhaeng on 4/6/2017 AD.
 */

public class BeerProductItemGroup implements Parcelable{

    private String status;
    private String message;
    private boolean nextBeerAvailable;
    private int nextBeerIndex;
    private List<BeerProductItem> beers;

    public String getStatus(){
        return status;
    }

    public void setStatus( String status ){
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage( String message ){
        this.message = message;
    }

    public boolean isNextBeerAvailable(){
        return nextBeerAvailable;
    }

    public void setNextBeerAvailable( boolean nextBeerAvailable ){
        this.nextBeerAvailable = nextBeerAvailable;
    }

    public List<BeerProductItem> getBeers(){
        return beers;
    }

    public List<BaseItem> getBaseItems(){
        List<BaseItem> baseItems = new ArrayList<>(  );
        for( BeerProductItem beer : beers ){
            baseItems.add(beer);
        }
        return baseItems;
    }

    public void setBeers( List<BeerProductItem> beers ){
        this.beers = beers;
    }

    public int getNextBeerIndex(){
        return nextBeerIndex;
    }

    public void setNextBeerIndex( int nextBeerIndex ){
        this.nextBeerIndex = nextBeerIndex;
    }

    public BeerProductItemGroup(){
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        dest.writeString( this.status );
        dest.writeString( this.message );
        dest.writeByte( this.nextBeerAvailable ? (byte) 1 : (byte) 0 );
        dest.writeInt( this.nextBeerIndex );
        dest.writeTypedList( this.beers );
    }

    protected BeerProductItemGroup( Parcel in ){
        this.status = in.readString();
        this.message = in.readString();
        this.nextBeerAvailable = in.readByte() != 0;
        this.nextBeerIndex = in.readInt();
        this.beers = in.createTypedArrayList( BeerProductItem.CREATOR );
    }

    public static final Creator<BeerProductItemGroup> CREATOR = new Creator<BeerProductItemGroup>(){
        @Override
        public BeerProductItemGroup createFromParcel( Parcel source ){
            return new BeerProductItemGroup( source );
        }

        @Override
        public BeerProductItemGroup[] newArray( int size ){
            return new BeerProductItemGroup[size];
        }
    };
}
