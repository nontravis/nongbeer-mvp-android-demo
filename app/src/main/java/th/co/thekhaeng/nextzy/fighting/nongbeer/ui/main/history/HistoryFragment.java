package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.thekhaeng.recyclerviewmargin.LinearLayoutMargin;

import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpFragment;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.HistoryListAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItemGroup;


/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class HistoryFragment extends BaseMvpFragment<HistoryFragmentInterface.Presenter>
        implements HistoryFragmentInterface.View{

    public static final String KEY_HISTORY_GROUP = "key_history_group";
    private RecyclerView rvHistory;
    private HistoryListAdapter historyAdapter;
    private View containerEmpty;
    private SwipeRefreshLayout swipeContainer;
    private View containerServiceUnavailable;
    private Button btnTryAgain;

    public HistoryFragment(){
        super();
    }

    public static HistoryFragment newInstance(){
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments( args );
        return fragment;
    }


    @Override
    public HistoryFragmentInterface.Presenter createPresenter(){
        return HistoryFragmentPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.fragment_order_history;
    }

    @Override
    public void bindView( View view ){
        rvHistory = (RecyclerView) view.findViewById( R.id.rv_order_history );
        containerEmpty = view.findViewById( R.id.container_empty_history );
        swipeContainer = (SwipeRefreshLayout) view.findViewById( R.id.swipe_container );
        containerServiceUnavailable = view.findViewById( R.id.container_service_unavailable );
        btnTryAgain = (Button) view.findViewById( R.id.btn_try_again );
    }

    @Override
    public void setupInstance(){
        historyAdapter = new HistoryListAdapter();
        historyAdapter.setOnClickHistoryListener( onClickHistoryItem() );
        historyAdapter.setOnLoadMoreListener( onLoadMore() );
    }


    @Override
    public void setupView(){
        int itemSpace = (int) getResources().getDimension( R.dimen.default_padding_margin );
        rvHistory.addItemDecoration( new LinearLayoutMargin( itemSpace ) );
        rvHistory.setLayoutManager( new LinearLayoutManager( getContext() ) );
        rvHistory.setAdapter( historyAdapter );

        swipeContainer.setOnRefreshListener( onPullRefresh() );
        updateEmptyCartView();

        btnTryAgain.setOnClickListener( onClickTryAgain() );
    }

    public void initialize(){
        historyAdapter.initDefaultItemForLoadmore();
    }

    public void restoreView( Bundle savedInstanceState ){
        getPresenter().setHistoryToAdapter( getPresenter().getHistoryItemGroup() );
    }

    @Override
    public void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
        outState.putParcelable( KEY_HISTORY_GROUP, getPresenter().getHistoryItemGroup() );
    }

    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
        getPresenter().setHistoryItemGroup(
                (HistoryItemGroup) savedInstanceState.getParcelable( KEY_HISTORY_GROUP ) );
    }

    @Override
    public void dismissSwipeLayout(){
        final Handler handler = new Handler();
        handler.postDelayed( new Runnable(){
            @Override
            public void run(){
                swipeContainer.setRefreshing( false );
            }
        }, 500 );
    }

    @Override
    public void showServiceUnavailableView(){
        rvHistory.setVisibility( View.GONE );
        containerEmpty.setVisibility( View.GONE );
        containerServiceUnavailable.setVisibility( View.VISIBLE );
    }


    @Override
    public void setHistoryItemToAdapter( List<BaseItem> items, boolean nextOrderAvailable ){
        historyAdapter.setItems( items, nextOrderAvailable );
        updateEmptyCartView();
    }

    private void updateEmptyCartView(){
        containerServiceUnavailable.setVisibility( View.GONE );
        if( historyAdapter.hasItems() ){
            rvHistory.setVisibility( View.VISIBLE );
            containerEmpty.setVisibility( View.GONE );
        }else{
            rvHistory.setVisibility( View.VISIBLE );
            containerEmpty.setVisibility( View.VISIBLE );
        }
    }

    @NonNull
    private HistoryListAdapter.OnLoadMoreListener onLoadMore(){
        return new HistoryListAdapter.OnLoadMoreListener(){
            @Override
            public void onLoadMore(){
                getPresenter().requestHistory();
            }
        };
    }

    @NonNull
    private HistoryListAdapter.OnClickHistoryListener onClickHistoryItem(){
        return new HistoryListAdapter.OnClickHistoryListener(){
            @Override
            public void onClickItem( HistoryItem item, int position ){
                getPresenter().goToOrderDetailActivity( item );
            }
        };
    }

    @NonNull
    private SwipeRefreshLayout.OnRefreshListener onPullRefresh(){
        return new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                getPresenter().setHistoryItemGroup( null );
                getPresenter().requestHistory();
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickTryAgain(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                getPresenter().requestHistory();
            }
        };
    }

}

