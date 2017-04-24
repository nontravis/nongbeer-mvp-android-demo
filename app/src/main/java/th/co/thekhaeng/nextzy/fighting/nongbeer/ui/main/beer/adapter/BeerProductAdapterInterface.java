package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore.LoadmoreAdapterInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class BeerProductAdapterInterface{


    public interface Adapter extends LoadmoreAdapterInterface.Adapter{
    }

    public interface Presenter extends LoadmoreAdapterInterface.Presenter<BeerProductAdapterInterface.Adapter>{
        void clearAddState( BeerProductItem item );
        void clearAddStateAll();
    }
}
