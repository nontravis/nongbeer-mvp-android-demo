package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main;


import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.GoToOrderDetailActivityEvent;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class MainActivityInterface{


    public interface View extends BaseMvpInterface.View{
        void goToOrderDetailActivity( GoToOrderDetailActivityEvent event );

        void goToMapActivity( List<BaseItem> items );

        void onAddBeerToCartEvent( BeerProductItem item );

        void onRemoveBeerFromCartEvent( BeerProductItem item );

        boolean hasItems();

        void showProductItemView();

        void hindProductItemView();
    }

    public interface Presenter extends  BaseMvpInterface.Presenter<MainActivityInterface.View>{

        void goToMapActivity( List<BaseItem> items );

        void clearAddedButtonStateEvent( BeerProductItem item );

        void clearAddedButtonStateAllEvent();

        void updateEmptyCartView();
    }
}
