package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.lang.ref.WeakReference;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.exception.MvpPresenterNotCreateException;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.BeerProductFragment;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.HistoryFragment;
import th.co.thekhaeng.nextzy.fighting.nongbeer.utils.StringUtils;


/**
 * Created by Nonthawit on 7/30/2016.
 */

public class NongBeerPagerStateAdapter extends FragmentStatePagerAdapter implements NongBeerPagerStateInterface.Adapter{
    private final static String TAG = NongBeerPagerStateAdapter.class.getSimpleName();
    private static final int TOTAL_PAGE = 2;
    private final WeakReference<FragmentManager> fm;
    private final NongBeerPagerStateInterface.Presenter presenter;
    private final Context context;


    public NongBeerPagerStateAdapter( FragmentActivity view ){
        super( view.getSupportFragmentManager() );
        fm = new WeakReference<>( view.getSupportFragmentManager() );
        context = view;
        presenter = NongBeerPagerStatePresenter.create();
        getPresenter().setAdapter( this );
    }

    @Override
    public FragmentManager getFragmentManager(){
        return fm.get();
    }

    @Override
    public NongBeerPagerStateInterface.Presenter getPresenter(){
        if( presenter != null ) return presenter;
        throw new MvpPresenterNotCreateException();
    }

    @Override
    public Fragment getItem( int position ){
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch( position ){
            case 0:
                return BeerProductFragment.newInstance();
            case 1:
                return HistoryFragment.newInstance();
            default:
                throw new NullPointerException( "Position more than 2." );
        }

    }

    @Override
    public int getCount(){
        return TOTAL_PAGE;
    }

    @Override
    public CharSequence getPageTitle( int position ){
        switch( position ){
            case 0:
                return StringUtils.getBeerString( context );
            case 1:
                return StringUtils.getHistoryString( context );
        }
        return null;
    }
}
