package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItemType;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore.LoadmoreAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.holder.BeerProductHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;


/**
 * Created by Nonthawit on 7/30/2016.
 */

public class BeerProductAdapter extends LoadmoreAdapter<BaseViewHolder, BeerProductAdapterInterface.Presenter>
        implements BeerProductAdapterInterface.Adapter{
    private final static String TAG = BeerProductAdapter.class.getSimpleName();

    public static final int TYPE_BEER_PRODUCT = 0;

    private OnClickBeerProductListener listener;


    public interface OnClickBeerProductListener{
        void onClickItem( BeerProductItem item, int position );

        void onClickAddToCart( BeerProductItem item, int position );

        void onClickAdded( BeerProductItem item, int position );
    }

    @Override
    public BeerProductAdapterInterface.Presenter createPresenter(){
        return BeerProductAdapterPresenter.create();
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
        switch( viewType ){
            case TYPE_BEER_PRODUCT:
                return new BeerProductHolder( viewGroup );
        }
        return super.onCreateViewHolder( viewGroup, viewType );
    }

    @Override
    public void onBindViewHolder( BaseViewHolder viewHolder, final int position ){
        super.onBindViewHolder( viewHolder, position );
        BaseItem i = getPresenter().getItem( position );
        if( getItemViewType( position ) == TYPE_BEER_PRODUCT ){
            final BeerProductItem item = (BeerProductItem) i;
            BeerProductHolder holder = (BeerProductHolder) viewHolder;
            holder.onBind( item );
            holder.setOnClickBeerListener( onClickBeer( item ) );
        }else if( getItemViewType( position ) == BaseItemType.TYPE_PROGRESS ){
            StaggeredGridLayoutManager.LayoutParams layoutParams =
                    (StaggeredGridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
            layoutParams.setFullSpan( true );
            viewHolder.itemView.setLayoutParams( layoutParams );
        }
    }

    public void initDefaultItemForLoadmore(){
        List<BaseItem> items = new ArrayList<>();
        getPresenter().setItems( items, true );
    }

    public void setOnClickProductItem( OnClickBeerProductListener listener ){
        this.listener = listener;
    }

    public void clearAddedState( BeerProductItem item ){
        getPresenter().clearAddState(item);
    }

    public void clearAddedStateAll(){
        getPresenter().clearAddStateAll();
    }


    @NonNull
    private BeerProductHolder.OnClickBeerListener onClickBeer( final BeerProductItem item ){
        return new BeerProductHolder.OnClickBeerListener(){
            @Override
            public void onClickItem( BeerProductHolder beerProductHolder, int position ){
                if( listener != null ){
                    listener.onClickItem( item, position );
                }
            }

            @Override
            public void onClickAddToCart( BeerProductHolder view, int position ){
                item.setAdded( true );
                if( listener != null ){
                    listener.onClickAddToCart( item, position );
                }
            }

            @Override
            public void onClickAdded( BeerProductHolder beerProductHolder, int position ){
                item.setAdded( false );
                if( listener != null ){
                    listener.onClickAdded( item, position );
                }
            }
        };
    }

}
