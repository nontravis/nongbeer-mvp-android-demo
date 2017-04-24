package th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.lang.ref.WeakReference;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.exception.MvpPresenterNotCreateException;


/**
 * Created by Nonthawit on 7/30/2016.
 */

public class CustomPagerAdapter extends FragmentStatePagerAdapter implements CustomPagerAdapterInterface.Adapter {
    private final static String TAG = CustomPagerAdapter.class.getSimpleName();
    private final WeakReference<FragmentManager> fm;
    private final CustomPagerAdapterInterface.Presenter presenter;


    public CustomPagerAdapter( FragmentManager fragmentManager) {
        super(fragmentManager);
        fm = new WeakReference<>( fragmentManager );
        presenter = CustomPagerAdapterPresenter.create();
        presenter.setAdapter( this );
    }

    @Override
    public int getCount(){
        return 0;
    }

    @Override
    public Fragment getItem( int position ){
        return null;
    }

    @Override
    public FragmentManager getFragmentManager(){
        return fm.get();
    }

    @Override
    public CustomPagerAdapterInterface.Presenter getPresenter(){
        if( presenter != null ) return presenter;
        throw new MvpPresenterNotCreateException();
    }
}
