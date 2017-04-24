package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.pager;


import java.lang.ref.WeakReference;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.exception.MvpViewNotAttachedException;

public class NongBeerPagerStatePresenter
        implements NongBeerPagerStateInterface.Presenter{

    private WeakReference<NongBeerPagerStateInterface.Adapter> adapter;

    public static NongBeerPagerStateInterface.Presenter create(){
        return new NongBeerPagerStatePresenter();
    }

    @Override
    public void setAdapter( NongBeerPagerStateInterface.Adapter adapter ){
        this.adapter = new WeakReference<>( adapter );
    }

    @Override
    public NongBeerPagerStateInterface.Adapter getAdapter(){
        if( adapter != null ) return adapter.get();
        throw new MvpViewNotAttachedException();
    }

}
