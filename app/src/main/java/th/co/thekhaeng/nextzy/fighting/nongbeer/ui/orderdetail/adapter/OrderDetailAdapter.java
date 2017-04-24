package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail.adapter;

import android.view.ViewGroup;

import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseMvpListAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail.adapter.holder.BeerOrderDetailHolder;

import static th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.BeerProductAdapter.TYPE_BEER_PRODUCT;


/**
 * Created by Nonthawit on 7/30/2016.
 */

public class OrderDetailAdapter extends BaseMvpListAdapter<BaseViewHolder, OrderDetailAdapterInterface.Presenter>
        implements OrderDetailAdapterInterface.Adapter{
    private final static String TAG = OrderDetailAdapter.class.getSimpleName();


    public OrderDetailAdapter( List<BaseItem> items ){
        getPresenter().setItems( items );
    }


    @Override
    public OrderDetailAdapterInterface.Presenter createPresenter(){
        return OrderDetailAdapterPresenter.create();
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
    public BeerOrderDetailHolder onCreateViewHolder( ViewGroup viewGroup, int viewType ){
        return new BeerOrderDetailHolder( viewGroup );
    }

    @Override
    public void onBindViewHolder( BaseViewHolder viewHolder, int position ){
        BaseItem i = getPresenter().getItem( position );
        if( getItemViewType( position ) == TYPE_BEER_PRODUCT ){
            BeerProductItem item = (BeerProductItem) i;
            BeerOrderDetailHolder holder = (BeerOrderDetailHolder) viewHolder;
            holder.onBind( item );
        }
    }
}
