package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.map;


import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class MapActivityInterface{


    public interface View extends BaseMvpInterface.View{
        void goToSuccessOrderActivity();

        void showFailMessage();

        void clearButtonLoadingState();
    }

    public interface Presenter extends BaseMvpInterface.Presenter<MapActivityInterface.View>{

        void goToSuccessOrderActivity();

        void requestAddNewOrder( double latitude, double longitude, List<BeerProductItem> items );

        void cancelRequest();
    }
}
