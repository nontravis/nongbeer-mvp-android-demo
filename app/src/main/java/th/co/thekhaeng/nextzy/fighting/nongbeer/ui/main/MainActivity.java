package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thekhaeng.recyclerviewmargin.LinearLayoutMargin;
import com.thekhaeng.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpActivity;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.cart.CartAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.GoToOrderDetailActivityEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.pager.NongBeerPagerStateAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.map.MapActivity;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail.OrderDetailActivity;
import th.co.thekhaeng.nextzy.fighting.nongbeer.utils.StringUtils;

import static android.view.View.GONE;
import static th.co.thekhaeng.nextzy.fighting.nongbeer.ui.map.MapActivity.EXTRA_PRODUCT_LIST;
import static th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail.OrderDetailActivity.EXTRA_HISTORY_ITEM;

/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class MainActivity extends BaseMvpActivity<MainActivityInterface.Presenter>
        implements MainActivityInterface.View{

    public static final int REQ_ORDER = 1000;

    public static final String KEY_BEER_ITEM_IN_CART = "key_beer_item_in_cart";
    private NongBeerPagerStateAdapter pagerAdapter;
    private ViewPager vpMain;
    private Toolbar toolbar;
    private ImageView icCart;
    private SlidingMenu menu;
    private RecyclerView rvCart;
    private CartAdapter cartAdapter;
    private TabLayout tabLayout;
    private LinearLayout containerEmpty;
    private TextView tvTotalPrice;
    private Button btnConfirmOrder;
    private TextView tvBeerAmount;

    public MainActivity(){
        super();
    }

    @Override
    public MainActivityInterface.Presenter createPresenter(){
        return MainActivityPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.activity_main;
    }

    @Override
    public void bindView(){
        toolbar = (Toolbar) findViewById( R.id.toolbar );
        icCart = (ImageView) findViewById( R.id.toolbar_cart );
        vpMain = (ViewPager) findViewById( R.id.container );
        tabLayout = (TabLayout) findViewById( R.id.tabs );
        tvBeerAmount = (TextView) findViewById( R.id.tv_product_count );

        menu = new SlidingMenu( this );
        menu.setMenu( R.layout.sliding_cart );
        rvCart = (RecyclerView) menu.getRootView().findViewById( R.id.rv_cart );
        containerEmpty = (LinearLayout) menu.findViewById( R.id.container_empty_cart );
        tvTotalPrice = (TextView) menu.findViewById( R.id.tv_total_price );
        btnConfirmOrder = (Button) menu.findViewById( R.id.btn_confirm_order );
    }

    @Override
    public void setupInstance(){
        cartAdapter = new CartAdapter();
        cartAdapter.setOnClickCartItem( onClickCartItem() );
        pagerAdapter = new NongBeerPagerStateAdapter( this );
    }

    @Override
    public void setupView(){
        setupToolbar();
        setupViewPager();
        setupTabLayout();
        setupCart();
        updateEmptyCartView();
    }


    private void setupToolbar(){
        setSupportActionBar( toolbar );
        ActionBar actionbar = getSupportActionBar();
        if( actionbar != null ){
            actionbar.setDisplayHomeAsUpEnabled( false );
            actionbar.setDisplayShowTitleEnabled( false );
        }

        icCart.setOnClickListener( onClickCart() );
    }

    private void setupViewPager(){
        vpMain.setAdapter( pagerAdapter );
    }

    private void setupTabLayout(){
        tabLayout.setupWithViewPager( vpMain );
    }

    private void setupCart(){
        menu.setMode( SlidingMenu.RIGHT );
        menu.setTouchModeAbove( SlidingMenu.TOUCHMODE_MARGIN );
        menu.setShadowWidthRes( R.dimen.elevation_sliding );
        menu.setShadowDrawable( R.drawable.shadow );
        menu.setBehindOffsetRes( R.dimen.sliding_menu_offset );
        menu.setFadeDegree( 0.35f );
        menu.attachToActivity( this, SlidingMenu.SLIDING_CONTENT );

        btnConfirmOrder.setOnClickListener( onClickConfirmOrder() );

        int itemSpace = (int) getResources().getDimension( R.dimen.default_padding_margin );
        rvCart.setLayoutManager( new LinearLayoutManager( this ) );
        rvCart.addItemDecoration( new LinearLayoutMargin( itemSpace ) );
        rvCart.setAdapter( cartAdapter );
    }

    @Override
    public void initialize(){

    }

    @Override
    protected void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
        List<BaseItem> items = cartAdapter.getItems();
        outState.putParcelableArrayList( KEY_BEER_ITEM_IN_CART, (ArrayList<? extends Parcelable>) items );
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
        List<BaseItem> items = savedInstanceState.getParcelableArrayList( KEY_BEER_ITEM_IN_CART );
        cartAdapter.setItems( items );
        updateAllCartView();
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ){
        if( requestCode == REQ_ORDER ){
            if( resultCode == RESULT_OK ){
                clearOrder();
            }
        }else{
            super.onActivityResult( requestCode, resultCode, data );
        }
    }


    @Override
    public void goToOrderDetailActivity( GoToOrderDetailActivityEvent event ){
        Intent i = new Intent( this, OrderDetailActivity.class );
        i.putExtra( EXTRA_HISTORY_ITEM, event.getItem() );
        startActivity( i );
    }

    @Override
    public void goToMapActivity( List<BaseItem> items ){
        Intent i = new Intent( this, MapActivity.class );
        i.putParcelableArrayListExtra( EXTRA_PRODUCT_LIST, (ArrayList<? extends Parcelable>) items );
        startActivityForResult( i, REQ_ORDER );
    }


    @Override
    public void onAddBeerToCartEvent( BeerProductItem item ){
        cartAdapter.addItem( item );
        updateAllCartView();
    }

    @Override
    public void onRemoveBeerFromCartEvent( BeerProductItem item ){
        cartAdapter.removeItem( item );
        updateAllCartView();
    }

    private void clearOrder(){
        cartAdapter.removeAllItems();
        updateAllCartView();
        menu.showContent();
        getPresenter().clearAddedButtonStateAllEvent();
    }


    private void updateAllCartView(){
        updateBeerAmountIntoCartView();
        updateEmptyCartView();
        updateTotalPrice();
    }

    private void updateBeerAmountIntoCartView(){
        int amount = cartAdapter.getItemCount();
        tvBeerAmount.setVisibility( amount == 0 ? View.GONE : View.VISIBLE );
        tvBeerAmount.setText( String.valueOf( amount ) );
    }

    private void updateEmptyCartView(){
        if( cartAdapter.hasItems() ){
            containerEmpty.setVisibility( GONE );
            btnConfirmOrder.setEnabled( true );
            btnConfirmOrder.setBackgroundResource( R.drawable.btn_active_selector );
        }else{
            containerEmpty.setVisibility( View.VISIBLE );
            btnConfirmOrder.setEnabled( false );
            btnConfirmOrder.setBackgroundResource( R.drawable.btn_inactive );
        }
    }

    @SuppressLint( "SetTextI18n" )
    private void updateTotalPrice(){
        tvTotalPrice.setText( StringUtils.getCommaPriceWithBaht( this, cartAdapter.getTotalPrice() ) );
    }


    @NonNull
    private CartAdapter.OnClickCartItemListener onClickCartItem(){
        return new CartAdapter.OnClickCartItemListener(){
            @Override
            public void onClickIncrease( BeerProductItem item, int position ){
                updateTotalPrice();
            }

            @Override
            public void onClickDecrease( BeerProductItem item, int position ){
                updateTotalPrice();
            }

            @Override
            public void onClickDelete( BeerProductItem item, int position ){
                onRemoveBeerFromCartEvent( item );
                getPresenter().clearAddedButtonStateEvent( item );
                updateTotalPrice();
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickConfirmOrder(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                goToMapActivity( cartAdapter.getItems() );
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickCart(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                menu.toggle();
            }
        };
    }

}

