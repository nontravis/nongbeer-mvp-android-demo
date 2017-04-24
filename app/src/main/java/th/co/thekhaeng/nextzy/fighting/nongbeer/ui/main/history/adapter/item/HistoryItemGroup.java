package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;


/**
 * Created by thekhaeng on 4/6/2017 AD.
 */

public class HistoryItemGroup implements Parcelable{
    private String status;
    private String message;
    private boolean nextOrderAvailable;
    private int nextOrderIndex;
    private List<HistoryItem> orders;

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

    public boolean isNextOrderAvailable(){
        return nextOrderAvailable;
    }

    public void setNextOrderAvailable( boolean nextOrderAvailable ){
        this.nextOrderAvailable = nextOrderAvailable;
    }

    public List<HistoryItem> getOrders(){
        return orders;
    }

    public List<BaseItem> getBaseItems(){
        List<BaseItem> baseItems = new ArrayList<>(  );
        for( HistoryItem beer : orders ){
            baseItems.add(beer);
        }
        return baseItems;
    }

    public void setOrders( List<BaseItem> orders ){
        List<HistoryItem> historyItems = new ArrayList<>(  );
        for( BaseItem beer : orders ){
            if( beer instanceof HistoryItem ){
                historyItems.add( (HistoryItem) beer );
            }
        }
        this.orders = historyItems;
    }

    public int getNextOrderIndex(){
        return nextOrderIndex;
    }

    public void setNextOrderIndex( int nextOrderIndex ){
        this.nextOrderIndex = nextOrderIndex;
    }

    public HistoryItemGroup(){
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        dest.writeString( this.status );
        dest.writeString( this.message );
        dest.writeByte( this.nextOrderAvailable ? (byte) 1 : (byte) 0 );
        dest.writeInt( this.nextOrderIndex );
        dest.writeTypedList( this.orders );
    }

    protected HistoryItemGroup( Parcel in ){
        this.status = in.readString();
        this.message = in.readString();
        this.nextOrderAvailable = in.readByte() != 0;
        this.nextOrderIndex = in.readInt();
        this.orders = in.createTypedArrayList( HistoryItem.CREATOR );
    }

    public static final Creator<HistoryItemGroup> CREATOR = new Creator<HistoryItemGroup>(){
        @Override
        public HistoryItemGroup createFromParcel( Parcel source ){
            return new HistoryItemGroup( source );
        }

        @Override
        public HistoryItemGroup[] newArray( int size ){
            return new HistoryItemGroup[size];
        }
    };
}
