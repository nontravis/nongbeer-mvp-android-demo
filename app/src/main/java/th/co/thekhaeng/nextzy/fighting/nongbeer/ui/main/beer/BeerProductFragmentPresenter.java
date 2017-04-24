package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer;


import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerServiceManager;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.BeerProductResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.BeerConverter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItemGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.AddBeerToCartEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.ClearAddedButtonStateAllEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.ClearAddedButtonStateEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.RemoveBeerFromCartEvent;

public class BeerProductFragmentPresenter extends BaseMvpPresenter<BeerProductFragmentInterface.View>
        implements BeerProductFragmentInterface.Presenter{

    private NongBeerServiceManager serviceManager;

    private BeerProductItemGroup itemGroup;

    public static BeerProductFragmentInterface.Presenter create(){
        return new BeerProductFragmentPresenter();
    }

    public BeerProductFragmentPresenter(){
        serviceManager = NongBeerServiceManager.getInstance();
    }

    public void setManager( NongBeerServiceManager manager ){
        serviceManager = manager;
    }

    @Override
    public void onViewStart(){
        RxBus.get().register( this );
    }

    @Override
    public void onViewStop(){
        RxBus.get().unregister( this );
    }


    /* =========================== On view call ================================================= */
    //<editor-fold desc="On view call folding">
    @Override
    public BeerProductItemGroup getBeerItemGroup(){
        return itemGroup;
    }

    @Override
    public void setBeerItemGroup( BeerProductItemGroup itemGroup ){
        this.itemGroup = itemGroup;
    }

    @Override
    public void requestBeerProduct(){
        getView().showServiceAvailableView();
        serviceManager.requestBeer( getNextPage(), new NongBeerServiceManager.NongBeerManagerCallback<BeerProductResultGroup>(){
            @Override
            public void onSuccess( BeerProductResultGroup result ){
                BeerProductItemGroup newGroup = BeerConverter.createBeerGroupItemFromResult( result );
                addOldBeerToNewBeerGroupIfAvailable( newGroup );
                itemGroup = newGroup;
                setBeerProductToAdapter( itemGroup );
            }

            @Override
            public void onFailure( Throwable t ){
                itemGroup = null;
                getView().showServiceUnavailableView();
            }

            private void addOldBeerToNewBeerGroupIfAvailable( BeerProductItemGroup newGroup ){
                if( itemGroup != null ){
                    newGroup.getBeers().addAll( 0, itemGroup.getBeers() );
                }
            }

        } );
    }

    @Override
    public void setBeerProductToAdapter( BeerProductItemGroup group ){
        getView().setBeerProductItemToAdapter( group.getBaseItems(), group.isNextBeerAvailable() );
    }

    @Override
    public void addBeerItemToCart( BeerProductItem item ){
        RxBus.get().post( new AddBeerToCartEvent( item ) );
    }

    @Override
    public void deleteBeerItemFromCart( BeerProductItem item, int position ){
        RxBus.get().post( new RemoveBeerFromCartEvent( item ) );
    }
    //</editor-fold>


    @Subscribe
    public void onClearAddedButtonStateEvent( ClearAddedButtonStateEvent event ){
        getView().onClearAddedButtonStateEvent( event.getItem() );
    }

    @Subscribe
    public void onClearAddedButtonStateAllEvent( ClearAddedButtonStateAllEvent event ){
        getView().onClearAddedButtonAllStateEvent();
    }

    private int getNextPage(){
        if( itemGroup == null ) return 0;
        return itemGroup.getNextBeerIndex();
    }

}
