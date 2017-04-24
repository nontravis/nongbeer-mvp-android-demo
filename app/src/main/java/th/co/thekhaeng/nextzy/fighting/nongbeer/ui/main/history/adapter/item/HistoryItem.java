package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.HistoryListAdapter;


/**
 * Created by thekhaeng on 4/6/2017 AD.
 */

public class HistoryItem extends BaseItem implements Parcelable{

    private String date;
    private String time;
    private int totalAmount;
    private int totalPrice;
    private List<BeerProductItem> beers;

    public HistoryItem(){
        super( HistoryListAdapter.TYPE_ORDER_HISTORY );
    }

    public HistoryItem setDate( String date ){
        this.date = date;
        return this;
    }

    public HistoryItem setTime( String time ){
        this.time = time;
        return this;
    }

    public HistoryItem setTotalAmount( int totalAmount ){
        this.totalAmount = totalAmount;
        return this;
    }

    public HistoryItem setTotalPrice( int totalPrice ){
        this.totalPrice = totalPrice;
        return this;
    }

    public HistoryItem setBeerProductItem( List<BeerProductItem> beers ){
        this.beers = beers;
        return this;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public int getTotalAmount(){
        return totalAmount;
    }

    public int getTotalPrice(){
        return totalPrice;
    }

    public List<BeerProductItem> getBeerProductItem(){
        return beers;
    }

    public List<BaseItem> getBaseItems(){
        List<BaseItem> baseItems = new ArrayList<>(  );
        for( BeerProductItem beer : beers ){
            baseItems.add(beer);
        }
        return baseItems;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeString( this.date );
        dest.writeString( this.time );
        dest.writeInt( this.totalAmount );
        dest.writeInt( this.totalPrice );
        dest.writeTypedList( this.beers );
    }

    protected HistoryItem( Parcel in ){
        super( in );
        this.date = in.readString();
        this.time = in.readString();
        this.totalAmount = in.readInt();
        this.totalPrice = in.readInt();
        this.beers = in.createTypedArrayList( BeerProductItem.CREATOR );
    }

    public static final Creator<HistoryItem> CREATOR = new Creator<HistoryItem>(){
        @Override
        public HistoryItem createFromParcel( Parcel source ){
            return new HistoryItem( source );
        }

        @Override
        public HistoryItem[] newArray( int size ){
            return new HistoryItem[size];
        }
    };
}
