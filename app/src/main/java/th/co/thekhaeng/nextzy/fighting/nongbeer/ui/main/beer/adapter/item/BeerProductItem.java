package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item;

import android.os.Parcel;
import android.os.Parcelable;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;

import static th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.BeerProductAdapter.TYPE_BEER_PRODUCT;

/**
 * Created by thekhaeng on 4/6/2017 AD.
 */

public class BeerProductItem extends BaseItem implements Parcelable{
    private String id;
    private String alcohol;
    private String image;
    private String name;
    private int price;
    private String volume;
    private int amount = 1;
    private boolean isAdded = false;

    public BeerProductItem(){
        super( TYPE_BEER_PRODUCT );
    }

    public BeerProductItem setId( String id ){
        this.id = id;
        return this;
    }

    public BeerProductItem setAlcohol( String alcohol ){
        this.alcohol = alcohol;
        return this;
    }

    public BeerProductItem setImage( String image ){
        this.image = image;
        return this;
    }

    public BeerProductItem setName( String name ){
        this.name = name;
        return this;
    }

    public BeerProductItem setPrice( int price ){
        this.price = price;
        return this;
    }


    public BeerProductItem setVolume( String volume ){
        this.volume = volume;
        return this;
    }

    public boolean isAdded(){
        return isAdded;
    }

    public void setAdded( boolean added ){
        isAdded = added;
    }

    public String getId(){
        return id;
    }

    public String getAlcohol(){
        return alcohol;
    }


    public String getImage(){
        return image;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }

    public String getVolume(){
        return volume;
    }

    public BeerProductItem setAmount( int amount ){
        this.amount = amount;
        return this;
    }

    public void increaseAmount(){
        amount++;
    }

    public void decreaseAmount(){
        amount--;
        if( amount < 1 ) amount = 1;
    }

    public int getAmount(){
        return amount;
    }

    public void clearAmount(){
        amount = 1;
    }

    @Override
    public boolean equals( Object o ){
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;

        BeerProductItem that = (BeerProductItem) o;

        if( price != that.price ) return false;
        if( amount != that.amount ) return false;
        if( isAdded != that.isAdded ) return false;
        if( id != null ? !id.equals( that.id ) : that.id != null ) return false;
        if( alcohol != null ? !alcohol.equals( that.alcohol ) : that.alcohol != null ) return false;
        if( image != null ? !image.equals( that.image ) : that.image != null ) return false;
        if( name != null ? !name.equals( that.name ) : that.name != null ) return false;
        return volume != null ? volume.equals( that.volume ) : that.volume == null;

    }

    @Override
    public int hashCode(){
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( alcohol != null ? alcohol.hashCode() : 0 );
        result = 31 * result + ( image != null ? image.hashCode() : 0 );
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + price;
        result = 31 * result + ( volume != null ? volume.hashCode() : 0 );
        result = 31 * result + amount;
        result = 31 * result + ( isAdded ? 1 : 0 );
        return result;
    }

    @Override
    public BaseItem clone() throws CloneNotSupportedException{
        BeerProductItem beerProductItem = new BeerProductItem()
                .setId( id )
                .setAlcohol( alcohol )
                .setImage( image )
                .setName( name )
                .setPrice( price )
                .setVolume( volume )
                .setAmount( amount );
        beerProductItem.setAdded( isAdded );
        return beerProductItem;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ){
        super.writeToParcel( dest, flags );
        dest.writeString( this.id );
        dest.writeString( this.alcohol );
        dest.writeString( this.image );
        dest.writeString( this.name );
        dest.writeInt( this.price );
        dest.writeString( this.volume );
        dest.writeInt( this.amount );
        dest.writeByte( this.isAdded ? (byte) 1 : (byte) 0 );
    }

    protected BeerProductItem( Parcel in ){
        super( in );
        this.id = in.readString();
        this.alcohol = in.readString();
        this.image = in.readString();
        this.name = in.readString();
        this.price = in.readInt();
        this.volume = in.readString();
        this.amount = in.readInt();
        this.isAdded = in.readByte() != 0;
    }

    public static final Creator<BeerProductItem> CREATOR = new Creator<BeerProductItem>(){
        @Override
        public BeerProductItem createFromParcel( Parcel source ){
            return new BeerProductItem( source );
        }

        @Override
        public BeerProductItem[] newArray( int size ){
            return new BeerProductItem[size];
        }
    };
}
