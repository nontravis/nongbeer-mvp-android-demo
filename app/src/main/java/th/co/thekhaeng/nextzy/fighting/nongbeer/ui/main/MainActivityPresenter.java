package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main;


import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.AddBeerToCartEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.ClearAddedButtonStateAllEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.ClearAddedButtonStateEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.GoToOrderDetailActivityEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.RemoveBeerFromCartEvent;

public class MainActivityPresenter
        extends BaseMvpPresenter<MainActivityInterface.View>
        implements MainActivityInterface.Presenter{

    public static MainActivityInterface.Presenter create(){
        return new MainActivityPresenter();
    }


    @Override
    public void onViewStart(){
        RxBus.get().register( this );
    }

    @Override
    public void onViewStop(){
        RxBus.get().unregister( this );
    }

    @Override
    public void goToMapActivity( List<BaseItem> items ){
        getView().goToMapActivity(items);
    }

    @Override
    public void clearAddedButtonStateEvent( BeerProductItem item ){
        RxBus.get().post( new ClearAddedButtonStateEvent( item ) );
    }

    @Override
    public void clearAddedButtonStateAllEvent(){
        RxBus.get().post( new ClearAddedButtonStateAllEvent() );
    }


    @Subscribe
    public void onGoToOrderDetailActivity( GoToOrderDetailActivityEvent event ){
        getView().goToOrderDetailActivity( event );
    }

    @Subscribe
    public void onAddBeerToCartEvent( AddBeerToCartEvent event ){
        event.getItem().clearAmount();
        getView().onAddBeerToCartEvent( event.getItem() );
    }

    @Subscribe
    public void onRemoveBeerFromCartEvent( RemoveBeerFromCartEvent event ){
        getView().onRemoveBeerFromCartEvent( event.getItem() );
    }

    @Override
    public void updateEmptyCartView(){
        if( getView().hasItems() ){
            getView().showProductItemView();
        }else{
            getView().hindProductItemView();
        }
    }

}
