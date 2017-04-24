package th.co.thekhaeng.nextzy.fighting.nongbeer.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpActivity;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.MainActivity;

/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class SplashScreenActivity extends BaseMvpActivity<SplashScreenActivityInterface.Presenter>
        implements SplashScreenActivityInterface.View{

    private static final long SPLASH_DELAY = 1000;


    @Override
    public SplashScreenActivityInterface.Presenter createPresenter(){
        return SplashScreenActivityPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.activity_splash_screen;
    }


    @Override
    public void bindView(){
    }

    @Override
    public void setupInstance(){
        goToMainActivity();
    }

    @Override
    public void setupView(){
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

    private void delaySplashScreen( Runnable runnable ){
        final Handler handler = new Handler();
        handler.postDelayed( runnable, SPLASH_DELAY );
    }

    private void goToMainActivity(){
        delaySplashScreen( new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent( SplashScreenActivity.this, MainActivity.class );
                startActivity( intent );
                finish();
                overridePendingTransition( R.anim.fade_in, R.anim.fade_out );
            }
        } );
    }
}

