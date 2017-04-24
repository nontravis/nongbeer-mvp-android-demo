package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.successorder;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpActivity;

/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class SuccessOrderActivity extends BaseMvpActivity<SuccessOrderActivityInterface.Presenter>
        implements SuccessOrderActivityInterface.View{

    private View btnClose;

    public SuccessOrderActivity(){
        super();
    }


    @Override
    public SuccessOrderActivityInterface.Presenter createPresenter(){
        return SuccessOrderActivityPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.activity_success_order;
    }

    @Override
    public void bindView(){
        btnClose = findViewById( R.id.btn_close );
    }

    @Override
    public void setupView(){
        btnClose.setOnClickListener( onClickClose() );
    }

    @Override
    public void setupInstance(){

    }

    @Override
    public void initialize(){

    }

    @Override
    public void restoreView( Bundle savedInstanceState ){

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
    private View.OnClickListener onClickClose(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                onBackPressed();
            }
        };
    }

}

