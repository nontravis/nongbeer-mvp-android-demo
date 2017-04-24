package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thekhaeng on 12/31/2016 AD.
 */

public class BaseItem implements Parcelable{

    protected int type;

    public BaseItem( int type ){
        this.type = type;
    }

    public BaseItem(){
    }

    public int getType(){
        return type;
    }

    @Override
    public BaseItem clone() throws CloneNotSupportedException{
        return (BaseItem) super.clone();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        dest.writeInt( this.type );
    }

    protected BaseItem( Parcel in ){
        this.type = in.readInt();
    }

    public static final Creator<BaseItem> CREATOR = new Creator<BaseItem>(){
        @Override
        public BaseItem createFromParcel( Parcel source ){
            return new BaseItem( source );
        }

        @Override
        public BaseItem[] newArray( int size ){
            return new BaseItem[size];
        }
    };
}
