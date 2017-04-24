package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItemType;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore.LoadmoreAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.progress.ProgressHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.holder.OrderHistoryHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItem;


/**
 * Created by Nonthawit on 7/30/2016.
 */

public class HistoryListAdapter extends LoadmoreAdapter<BaseViewHolder, HistoryListAdapterInterface.Presenter>
        implements HistoryListAdapterInterface.Adapter{
    private final static String TAG = HistoryListAdapter.class.getSimpleName();

    public static final int TYPE_ORDER_HISTORY = 10;

    private OnClickHistoryListener listener;

    public interface OnClickHistoryListener{
        void onClickItem( HistoryItem item, int position );
    }

    @Override
    public HistoryListAdapterInterface.Presenter createPresenter(){
        return HistoryListAdapterPresenter.create();
    }

    public void initDefaultItemForLoadmore(){
        List<BaseItem> items = new ArrayList<>();
        getPresenter().setItems( items , true);
    }


    @Override
    public int getItemCount(){
        return getPresenter().getItemCount();
    }

    @Override
    public int getItemViewType( int position ){
        return getPresenter().getItemViewType( position );
    }

    public void setOnClickHistoryListener( OnClickHistoryListener listener ){
        this.listener = listener;
    }

    @Override
    public BaseViewHolder onCreateViewHolder( ViewGroup viewGroup, int viewType ){
        switch( viewType ){
            case TYPE_ORDER_HISTORY:
                return new OrderHistoryHolder( viewGroup );
        }
        return super.onCreateViewHolder( viewGroup, viewType );
    }

    @Override
    public void onBindViewHolder( BaseViewHolder viewHolder, final int position ){
        super.onBindViewHolder( viewHolder, position );
        BaseItem i = getPresenter().getItem( position );
        if( getItemViewType( position ) == TYPE_ORDER_HISTORY ){
            HistoryItem item = (HistoryItem) i;
            OrderHistoryHolder holder = (OrderHistoryHolder) viewHolder;
            holder.onBind( item );
            holder.setOnClickItem( onClickItem( position, item ) );
        }else if( getItemViewType( position ) == BaseItemType.TYPE_PROGRESS ){
            ProgressHolder holder = (ProgressHolder) viewHolder;
            // TODO1: 4/14/2017 AD if you want to customize progress layout.
        }
    }


    @NonNull
    private View.OnClickListener onClickItem( final int position, final HistoryItem item ){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClickItem( item, position );
                }
            }
        };
    }


}
