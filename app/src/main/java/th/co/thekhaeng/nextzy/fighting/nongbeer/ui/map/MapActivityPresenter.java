package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.map;


import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerServiceManager;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.AddNewOrderResult;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

public class MapActivityPresenter
    extends BaseMvpPresenter<MapActivityInterface.View>
        implements MapActivityInterface.Presenter{

    private final NongBeerServiceManager manager;

    public static MapActivityInterface.Presenter create(){
        return new MapActivityPresenter();
    }

    public MapActivityPresenter(){
        manager = NongBeerServiceManager.getInstance();
    }

    @Override
    public void goToSuccessOrderActivity(){
        getView().goToSuccessOrderActivity();
    }

    @Override
    public void requestAddNewOrder( double latitude, double longitude, List<BeerProductItem> items){
       manager.addNewOrder( latitude, longitude, items, new NongBeerServiceManager.NongBeerManagerCallback<AddNewOrderResult>(){
           @Override
           public void onSuccess( AddNewOrderResult result ){
                goToSuccessOrderActivity();
           }

           @Override
           public void onFailure( Throwable t ){
                getView().showFailMessage();
           }
       } );
    }

    @Override
    public void cancelRequest(){
        getView().clearButtonLoadingState();
        manager.cancelAddNewOrder();
    }
}
