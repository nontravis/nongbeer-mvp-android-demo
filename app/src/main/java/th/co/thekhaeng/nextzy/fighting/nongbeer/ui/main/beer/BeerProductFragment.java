package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thekhaeng.recyclerviewmargin.StaggeredGridLayoutMargin;

import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpFragment;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.BeerProductAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItemGroup;

/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class BeerProductFragment extends
        BaseMvpFragment<BeerProductFragmentInterface.Presenter>
        implements BeerProductFragmentInterface.View{

    private static final int COLUMN = 2;
    public static final String KEY_BEER_GROUP = "key_beer_group";
    private RecyclerView rvBeer;
    private BeerProductAdapter beerAdapter;
    private View containerServiceUnavailable;
    private Button btnTryAgain;

    public BeerProductFragment(){
        super();
    }

    public static BeerProductFragment newInstance(){
        BeerProductFragment fragment = new BeerProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public BeerProductFragmentInterface.Presenter createPresenter(){
        return BeerProductFragmentPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.fragment_beer_product;
    }

    @Override
    public void bindView( View view ){
        rvBeer = (RecyclerView) view.findViewById( R.id.rv_beer );
        containerServiceUnavailable = view.findViewById( R.id.container_service_unavailable );
        btnTryAgain = (Button) view.findViewById( R.id.btn_try_again );
    }

    @Override
    public void setupInstance(){
        beerAdapter = new BeerProductAdapter();
        beerAdapter.setOnClickProductItem( onClickProduct() );
        beerAdapter.setOnLoadMoreListener( onLoadMore() );
    }

    @Override
    public void setupView(){
        int itemSpace = (int) getResources().getDimension( R.dimen.default_padding_margin );
        rvBeer.addItemDecoration( new StaggeredGridLayoutMargin( COLUMN, itemSpace ) );
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager( COLUMN, StaggeredGridLayoutManager.VERTICAL );
        rvBeer.setLayoutManager( layout );
        rvBeer.setAdapter( beerAdapter );

        btnTryAgain.setOnClickListener( onClickTryAgain() );
    }


    @Override
    public void initialize(){
        beerAdapter.initDefaultItemForLoadmore();
    }

    @Override
    public void restoreView( Bundle savedInstanceState ){
        getPresenter().setBeerProductToAdapter( getPresenter().getBeerItemGroup() );
    }

    @Override
    public void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
        outState.putParcelable( KEY_BEER_GROUP, getPresenter().getBeerItemGroup() );
    }

    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
        getPresenter().setBeerItemGroup(
                (BeerProductItemGroup) savedInstanceState.getParcelable( KEY_BEER_GROUP ) );
    }

    /** =========================== On presenter call ========================================== **/
    //<editor-fold desc="On presenter call folding">
    @Override
    public void setBeerProductItemToAdapter( List<BaseItem> beerItemFromResult , boolean isNextBeerAvailable){
        beerAdapter.setItems( beerItemFromResult, isNextBeerAvailable );
    }

    @Override
    public void onClearAddedButtonStateEvent( BeerProductItem item ){
        beerAdapter.clearAddedState(item);
    }

    @Override
    public void onClearAddedButtonAllStateEvent(){
        beerAdapter.clearAddedStateAll();
        rvBeer.smoothScrollToPosition(0);
    }

    @Override
    public List<BaseItem> getItemsFromAdapter(){
        return beerAdapter.getItems();
    }


    @Override
    public void showServiceUnavailableView(){
        rvBeer.setVisibility( View.GONE );
        containerServiceUnavailable.setVisibility( View.VISIBLE );
    }

    @Override
    public void showServiceAvailableView(){
        rvBeer.setVisibility( View.VISIBLE );
        containerServiceUnavailable.setVisibility( View.GONE );
    }
    //</editor-fold>

    @NonNull
    private BeerProductAdapter.OnClickBeerProductListener onClickProduct(){
        return new BeerProductAdapter.OnClickBeerProductListener(){
            @Override
            public void onClickItem( BeerProductItem item, int position ){
                Toast.makeText( getActivity(), "Item " + position, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onClickAddToCart( BeerProductItem item, int position ){
                getPresenter().addBeerItemToCart( item );
            }

            @Override
            public void onClickAdded( BeerProductItem item, int position ){
                getPresenter().deleteBeerItemFromCart( item, position );
            }
        };
    }


    @NonNull
    private BeerProductAdapter.OnLoadMoreListener onLoadMore(){
        return new BeerProductAdapter.OnLoadMoreListener(){
            @Override
            public void onLoadMore(){
                getPresenter().requestBeerProduct();
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickTryAgain(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                getPresenter().requestBeerProduct();
            }
        };
    }

}

