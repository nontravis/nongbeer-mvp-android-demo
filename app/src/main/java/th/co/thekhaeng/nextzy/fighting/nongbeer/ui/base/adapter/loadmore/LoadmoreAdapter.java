package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore;

import android.view.ViewGroup;

import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItemType;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseMvpListAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.progress.ProgressHolder;

/**
 * Created by thekhaeng on 4/24/2017 AD.
 */

public abstract class LoadmoreAdapter<VH extends BaseViewHolder, P extends LoadmoreAdapterInterface.Presenter>
        extends BaseMvpListAdapter<VH, P>
        implements LoadmoreAdapterInterface.Adapter{

    private OnLoadMoreListener loadMoreListener;

    public void setOnLoadMoreListener( OnLoadMoreListener listener ){
        this.loadMoreListener = listener;
    }

    public void setItems( List<BaseItem> items, boolean isNextItemAvailable ){
        getPresenter().setItems( items, isNextItemAvailable );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public VH onCreateViewHolder( ViewGroup parent, int viewType ){
        if( viewType == BaseItemType.TYPE_PROGRESS ){
            return (VH) new ProgressHolder( parent );
        }
        return null;
    }

    @Override
    public void onBindViewHolder( VH holder, int position ){
        if( getItemViewType( position ) == BaseItemType.TYPE_PROGRESS ){
            if( loadMoreListener != null ){
                loadMoreListener.onLoadMore();
            }
        }
    }
}
