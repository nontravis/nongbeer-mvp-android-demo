package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

/**
 * Created by thekhaeng on 4/10/2017 AD.
 */

public class ClearAddedButtonStateEvent{
    private BeerProductItem item;

    public ClearAddedButtonStateEvent( BeerProductItem item ){
        this.item = item;
    }

    public BeerProductItem getItem(){
        return item;
    }
}
