package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.cart.holder;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.utils.StringUtils;

/**
 * Created by thekhaeng on 4/6/2017 AD.
 */

public class CartHolder extends BaseViewHolder{

    private TextView tvName;
    private TextView tvPrice;
    private TextView tvAmount;
    private ImageView btnDecrease;
    private ImageView btnIncrease;
    private ImageView btnDelete;
    private OnClickCartHolderListener listener;

    public interface OnClickCartHolderListener{
        void onClickIncrease( CartHolder view, int position );

        void onClickDecrease( CartHolder view, int position );

        void onClickDelete( CartHolder view, int position );
    }

    public CartHolder( ViewGroup viewGroup ){
        super( viewGroup, R.layout.holder_cart );
    }

    @Override
    public void bindView( View view ){
        tvName = (TextView) view.findViewById( R.id.tv_beer_name );
        tvPrice = (TextView) view.findViewById( R.id.tv_beer_price );
        tvAmount = (TextView) view.findViewById( R.id.tv_beer_amount );
        btnDecrease = (ImageView) view.findViewById( R.id.btn_decrease );
        btnIncrease = (ImageView) view.findViewById( R.id.btn_increase );
        btnDelete = (ImageView) view.findViewById( R.id.btn_delete_item );
        btnDecrease.setOnClickListener( onClickDecrease() );
        btnIncrease.setOnClickListener( onClickIncrease() );
        btnDelete.setOnClickListener( onClickDelete() );

        setupView();
    }

    private void setupView(){

    }

    @SuppressLint( "SetTextI18n" )
    public void onBind( BeerProductItem item ){
        tvName.setText( item.getName() );
        tvPrice.setText( StringUtils.getCommaPriceWithBaht( getContext(), item.getPrice() ) );
        tvAmount.setText( item.getAmount() + "" );
    }

    public void setOnClickCartHolderListener( OnClickCartHolderListener listener ){
        this.listener = listener;
    }

    @NonNull
    private View.OnClickListener onClickDecrease(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClickDecrease( CartHolder.this, getAdapterPosition() );
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickIncrease(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClickIncrease( CartHolder.this, getAdapterPosition() );
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickDelete(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClickDelete( CartHolder.this, getAdapterPosition() );
                }
            }
        };
    }


}
