package th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.list;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.list.holder.CustomHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.list.item.CustomItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore.LoadmoreAdapter;

/**
 * Created by Nonthawit on 7/30/2016.
 */

public class CustomListAdapter
        extends LoadmoreAdapter<BaseViewHolder, CustomListAdapterInterface.Presenter>
        implements CustomListAdapterInterface.Adapter{
    private final static String TAG = CustomListAdapter.class.getSimpleName();

    public static final int CUSTOM_TYPE = 10;

    private OnClickCustomItemListener listener;

    public interface OnClickCustomItemListener{
        void onClick1( CustomItem item, int position );

        void onClick2( CustomItem item, int position );

        void onClick3( CustomItem item, int position );
    }

    public void setOnClickCustomItemListener( OnClickCustomItemListener listener ){
        this.listener = listener;
    }

    public CustomListAdapter(){
    }

    @Override
    public CustomListAdapterInterface.Presenter createPresenter(){
        return CustomListAdapterPresenter.create();
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
            case CUSTOM_TYPE:
                return new CustomHolder( viewGroup );
        }
        return super.onCreateViewHolder( viewGroup, viewType ); // use loadmore
    }

    @Override
    public void onBindViewHolder( BaseViewHolder viewHolder, int position ){
        super.onBindViewHolder( viewHolder, position ); // use loadmore
        BaseItem i = getPresenter().getItem( position );
        if( getItemViewType( position ) == CUSTOM_TYPE ){
            CustomItem item = (CustomItem) i;
            CustomHolder holder = (CustomHolder) viewHolder;
            holder.onBind( item );
            holder.setOnClickCustomHolderListener( onClickHolder( item ) );
        }
    }

    @NonNull
    private CustomHolder.OnClickCustomHolderListener onClickHolder( final CustomItem item ){
        return new CustomHolder.OnClickCustomHolderListener(){
            @Override
            public void onClick1( CustomHolder view, int position ){
                if( listener != null ){
                    listener.onClick1( item, position );
                }
            }

            @Override
            public void onClick2( CustomHolder view, int position ){
                if( listener != null ){
                    listener.onClick2( item, position );
                }
            }

            @Override
            public void onClick3( CustomHolder view, int position ){
                if( listener != null ){
                    listener.onClick3( item, position );
                }
            }
        };
    }


}
