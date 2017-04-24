package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.cart;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseMvpListAdapterInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class CartAdapterInterface{


    public interface Adapter extends BaseMvpListAdapterInterface.Adapter{
    }

    public interface Presenter extends BaseMvpListAdapterInterface.Presenter<CartAdapterInterface.Adapter>{
        void increaseItemAmountAt( BeerProductItem item );

        void decreaseItemAmountAt( BeerProductItem item );

        int getTotalPrice();

        void removeItem( BeerProductItem item );
    }
}
