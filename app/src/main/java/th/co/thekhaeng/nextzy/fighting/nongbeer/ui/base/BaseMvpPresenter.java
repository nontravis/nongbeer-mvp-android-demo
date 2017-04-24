package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base;

import java.lang.ref.WeakReference;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.exception.MvpViewNotAttachedException;


/**
 * Created by TheKhaeng on 12/18/2016.
 */

public abstract class BaseMvpPresenter<V extends BaseMvpInterface.View>
        implements BaseMvpInterface.Presenter<V>{

    // View reference. We use as a WeakReference
    // because the Activity could be destroyed at any time
    // and we don't want to create a memory leak
    private WeakReference<V> mMvpView;

    @Override
    public void attachView( V mvpView ){
        mMvpView = new WeakReference<>( mvpView );
    }


    @Override
    public void detachView(){
        mMvpView = null;
    }

    @Override
    public V getView() throws NullPointerException{
        if( mMvpView != null ) return mMvpView.get();
        throw new MvpViewNotAttachedException();
    }

    @Override
    public void onViewCreate(){
    }


    @Override
    public void onViewStart(){
    }

    @Override
    public void onViewStop(){
    }

    @Override
    public void onViewDestroy(){
    }

}
