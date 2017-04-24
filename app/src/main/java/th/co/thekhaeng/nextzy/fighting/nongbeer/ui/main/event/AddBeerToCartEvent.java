package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

/**
 * Created by thekhaeng on 4/8/2017 AD.
 */

public class AddBeerToCartEvent{


    private BeerProductItem item;

    public AddBeerToCartEvent( BeerProductItem item ){
        this.item = item;
    }

    public BeerProductItem getItem(){
        return item;
    }
}
