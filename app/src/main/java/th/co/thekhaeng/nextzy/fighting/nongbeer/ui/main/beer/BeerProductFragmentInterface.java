package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer;


import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItemGroup;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class BeerProductFragmentInterface{


    public interface View extends BaseMvpInterface.View{

        void showServiceUnavailableView();

        void showServiceAvailableView();

        void setBeerProductItemToAdapter( List<BaseItem> beerItemFromResult , boolean isNextBeerAvailable);

        void onClearAddedButtonStateEvent( BeerProductItem item );


        List<BaseItem> getItemsFromAdapter();

        void onClearAddedButtonAllStateEvent();
    }

    public interface Presenter extends BaseMvpInterface.Presenter<BeerProductFragmentInterface.View>{

        BeerProductItemGroup getBeerItemGroup();

        void setBeerItemGroup( BeerProductItemGroup result );

        void requestBeerProduct();

        void setBeerProductToAdapter( BeerProductItemGroup result );

        void addBeerItemToCart( BeerProductItem item );

        void deleteBeerItemFromCart( BeerProductItem item, int position );

    }
}
