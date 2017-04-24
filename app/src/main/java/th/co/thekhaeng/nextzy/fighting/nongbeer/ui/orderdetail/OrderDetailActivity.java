package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thekhaeng.recyclerviewmargin.LinearLayoutMargin;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpActivity;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail.adapter.OrderDetailAdapter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.utils.StringUtils;

/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class OrderDetailActivity extends BaseMvpActivity<OrderDetailActivityInterface.Presenter>
        implements OrderDetailActivityInterface.View{
    public static final String EXTRA_HISTORY_ITEM = "extra_item";

    private TextView tvDate;
    private TextView tvTotalPrice;
    private RecyclerView rvOrder;
    private Button btnClose;
    private OrderDetailAdapter orderAdapter;
    private Toolbar toolbar;
    private ImageView btnBack;
    private HistoryItem item;

    public OrderDetailActivity(){
        super();
    }


    @Override
    public OrderDetailActivityInterface.Presenter createPresenter(){
        return OrderDetailActivityPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.activity_order_detail;
    }

    @Override
    public void bindView(){
        btnClose = (Button) findViewById( R.id.btn_close );
        rvOrder = (RecyclerView) findViewById( R.id.rv_order );
        tvTotalPrice = (TextView) findViewById( R.id.tv_total_price );
        tvDate = (TextView) findViewById( R.id.tv_date );
        toolbar = (Toolbar) findViewById( R.id.toolbar );
        btnBack = (ImageView) toolbar.findViewById( R.id.toolbar_back );
    }

    @Override
    public void setupInstance(){
        item = getIntent().getParcelableExtra( EXTRA_HISTORY_ITEM );
        if( item == null ){
            throw new NullPointerException( "Item cannot null." );
        }
        orderAdapter = new OrderDetailAdapter( item.getBaseItems()  );
    }

    @SuppressLint( "SetTextI18n" )
    @Override
    public void setupView(){
        int itemSpace = (int) getResources().getDimension( R.dimen.default_padding_margin_large );
        rvOrder.addItemDecoration( new LinearLayoutMargin( itemSpace ) );
        rvOrder.setLayoutManager( new LinearLayoutManager( this ) );
        rvOrder.setAdapter( orderAdapter );
        btnClose.setOnClickListener( onClickBack() );
        btnBack.setOnClickListener( onClickBack() );

        setSupportActionBar( toolbar );
        ActionBar actionbar = getSupportActionBar();
        if( actionbar != null ){
            actionbar.setDisplayHomeAsUpEnabled( false );
            actionbar.setDisplayShowTitleEnabled( false );
        }

        tvDate.setText( item.getDate() + StringUtils.getAtString(this) + item.getTime() );
        tvTotalPrice.setText( StringUtils.getCommaPriceWithBaht( this, item.getTotalPrice() ) );
    }


    @Override
    public void initialize(){

    }

    @Override
    protected void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
    }

    @NonNull
    private View.OnClickListener onClickBack(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                finish();
            }
        };
    }
}

