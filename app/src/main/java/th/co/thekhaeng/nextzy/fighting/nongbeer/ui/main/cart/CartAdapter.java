package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.cart;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseMvpListAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.cart.holder.CartHolder;

import static th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.BeerProductAdapter.TYPE_BEER_PRODUCT;


/**
 * Created by Nonthawit on 7/30/2016.
 */

public class CartAdapter extends BaseMvpListAdapter<BaseViewHolder,CartAdapterInterface.Presenter>
        implements CartAdapterInterface.Adapter{
    private final static String TAG = CartAdapter.class.getSimpleName();
    private OnClickCartItemListener listener;


    public CartAdapter(){
        getPresenter().setItems( new ArrayList<BaseItem>() );
    }


    public interface OnClickCartItemListener{
        void onClickIncrease( BeerProductItem item, int position );

        void onClickDecrease( BeerProductItem item, int position );

        void onClickDelete( BeerProductItem item, int position );
    }

    @Override
    public CartAdapterInterface.Presenter createPresenter(){
        return CartAdapterPresenter.create();
    }


    public void setOnClickCartItem( OnClickCartItemListener listener ){
        this.listener = listener;
    }

    @Override
    public int getItemCount(){
        return getPresenter().getItemCount();
    }

    @Override
    public int getItemViewType( int position ){
        return getPresenter().getItemViewType( position );
    }


    @Override
    public BaseViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType ){
        return new CartHolder( viewGroup );
    }

    @Override
    public void onBindViewHolder( BaseViewHolder viewHolder, final int position ){
        BaseItem i = getPresenter().getItem( position );
        if( getItemViewType( position ) == TYPE_BEER_PRODUCT ){
            final BeerProductItem item = (BeerProductItem) i;
            CartHolder holder = (CartHolder) viewHolder;
            holder.onBind( item );
            holder.setOnClickCartHolderListener( onClickCartHolder( item ) );
        }
    }

    public int getTotalPrice(){
        return getPresenter().getTotalPrice();
    }


    public void removeItem( BeerProductItem item ){
        getPresenter().removeItem(item);
    }


    @NonNull
    private CartHolder.OnClickCartHolderListener onClickCartHolder( final BeerProductItem item ){
        return new CartHolder.OnClickCartHolderListener(){
            @Override
            public void onClickIncrease( CartHolder view, int position ){
                getPresenter().increaseItemAmountAt( item );
                if( listener != null ){
                    listener.onClickIncrease( item, position );
                }
            }

            @Override
            public void onClickDecrease( CartHolder view, int position ){
                getPresenter().decreaseItemAmountAt( item );
                if( listener != null ){
                    listener.onClickDecrease( item, position );
                }
            }

            @Override
            public void onClickDelete( CartHolder view, int position ){
                if( listener != null ){
                    listener.onClickDelete( item, position );
                }
            }
        };
    }

}
