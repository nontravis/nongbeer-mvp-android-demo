package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base;

/**
 * Created by TheKhaeng on 12/18/2016.
 */

public interface BaseMvpInterface{
    interface View{
        Presenter getPresenter();
    }


    interface Presenter<V extends BaseMvpInterface.View>{
        void attachView( V mvpView );

        void detachView();

        V getView();

        void onViewCreate();

        void onViewDestroy();

        void onViewStart();

        void onViewStop();

    }
}
