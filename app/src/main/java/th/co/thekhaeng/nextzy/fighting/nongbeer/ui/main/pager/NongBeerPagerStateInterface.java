package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.pager;


import android.support.v4.app.FragmentManager;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class NongBeerPagerStateInterface{


    public interface Adapter{
        FragmentManager getFragmentManager();

        Presenter getPresenter();
    }

    public interface Presenter{
        void setAdapter(  NongBeerPagerStateInterface.Adapter adapter );
        NongBeerPagerStateInterface.Adapter getAdapter();
    }
}
