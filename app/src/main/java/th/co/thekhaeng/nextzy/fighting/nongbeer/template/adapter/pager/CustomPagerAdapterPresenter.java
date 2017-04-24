package th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.pager;


import java.lang.ref.WeakReference;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.exception.MvpViewNotAttachedException;

public class CustomPagerAdapterPresenter
        implements CustomPagerAdapterInterface.Presenter{

    private WeakReference<CustomPagerAdapterInterface.Adapter> adapter;

    public static CustomPagerAdapterInterface.Presenter create(){
        return new CustomPagerAdapterPresenter();
    }


    @Override
    public void setAdapter( CustomPagerAdapterInterface.Adapter adapter ){
        this.adapter = new WeakReference<>( adapter );
    }

    @Override
    public CustomPagerAdapterInterface.Adapter getAdapter(){
        if( adapter != null ) return adapter.get();
        throw new MvpViewNotAttachedException();
    }

}
