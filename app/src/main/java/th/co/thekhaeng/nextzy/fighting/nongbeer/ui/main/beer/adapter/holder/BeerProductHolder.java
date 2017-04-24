package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.holder;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.utils.StringUtils;

/**
 * Created by thekhaeng on 4/6/2017 AD.
 */

public class BeerProductHolder extends BaseViewHolder{

    private ImageView imgBeer;
    private TextView tvBeerName;
    private TextView tvBeerPrice;
    private TextView tvBeerPercent;
    private TextView tvBeerVolume;
    private Button btnAddToCart;
    private Button btnAdded;
    private OnClickBeerListener listener;

    public BeerProductHolder( ViewGroup viewGroup ){
        super( viewGroup, R.layout.holder_beer_product );
    }

    public interface OnClickBeerListener{
        void onClickAddToCart( BeerProductHolder view, int position );

        void onClickAdded( BeerProductHolder beerProductHolder, int position );

        void onClickItem( BeerProductHolder beerProductHolder, int position );
    }

    @Override
    public void bindView( View view ){
        imgBeer = (ImageView) view.findViewById( R.id.img_beer );
        tvBeerName = (TextView) view.findViewById( R.id.tv_beer_name );
        tvBeerPercent = (TextView) view.findViewById( R.id.tv_beer_percent );
        tvBeerVolume = (TextView) view.findViewById( R.id.tv_beer_volume );
        tvBeerPrice = (TextView) view.findViewById( R.id.tv_beer_price );
        btnAddToCart = (Button) view.findViewById( R.id.btn_add_to_cart );
        btnAdded = (Button) view.findViewById( R.id.btn_added );

        itemView.setOnClickListener( onClickItem() );
        btnAddToCart.setOnClickListener( onClickAddToCart() );
        btnAdded.setOnClickListener( onClickAdded() );
    }


    public void setOnClickBeerListener( OnClickBeerListener listener ){
        this.listener = listener;
    }

    @SuppressLint( "SetTextI18n" )
    public void onBind( BeerProductItem item ){
        setBeerImage( item.getImage() );
        tvBeerName.setText( item.getName() );
        tvBeerPercent.setText( item.getAlcohol() );
        tvBeerVolume.setText( item.getVolume() );
        tvBeerPrice.setText( StringUtils.getCommaPriceWithBaht( getContext(), item.getPrice() ) );

        if( item.isAdded() ){
            btnAdded.setVisibility( View.VISIBLE );
            btnAddToCart.setVisibility( View.GONE );
        }else{
            btnAdded.setVisibility( View.GONE );
            btnAddToCart.setVisibility( View.VISIBLE );
        }
    }


    private void setBeerImage( String url ){
        Glide.with( getContext() )
                .load( url )
                .into( imgBeer );
    }

    private void toggleButton(){
        if( btnAddToCart.getVisibility() == View.VISIBLE ){
            btnAdded.setVisibility( View.VISIBLE );
            btnAddToCart.setVisibility( View.GONE );
        }else{
            btnAdded.setVisibility( View.GONE );
            btnAddToCart.setVisibility( View.VISIBLE );
        }
    }

    @NonNull
    private View.OnClickListener onClickAdded(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClickAdded( BeerProductHolder.this, getAdapterPosition() );
                }
                toggleButton();
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickAddToCart(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClickAddToCart( BeerProductHolder.this, getAdapterPosition() );
                }
                toggleButton();
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickItem(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClickItem( BeerProductHolder.this, getAdapterPosition() );
                }
            }
        };
    }
}
