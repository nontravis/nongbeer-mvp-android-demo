package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.HistoryResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.HistoryResult;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.BeerConverter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItemGroup;

/**
 * Created by thekhaeng on 4/10/2017 AD.
 */

public class HistoryConverter{


    public static HistoryItemGroup createHistoryItemFromResult( HistoryResultGroup result ){
        HistoryItemGroup itemGroup = new HistoryItemGroup();
        itemGroup.setStatus( result.getStatus() );
        itemGroup.setMessage( result.getMessage() );
        itemGroup.setNextOrderAvailable( result.isNextOrderAvailable() );
        itemGroup.setNextOrderIndex( result.getNextOrderIndex() );
        itemGroup.setOrders( createHistoryItemFromResult( result.getOrders() ) );
        return itemGroup;
    }


    private static List<BaseItem> createHistoryItemFromResult( List<HistoryResult> orders ){
        List<BaseItem> items = new ArrayList<>();
        for( HistoryResult history : orders ){
            HistoryItem item = new HistoryItem()
                    .setBeerProductItem( BeerConverter.createBeerProductItemsFromResult( history.getBeers() ) )
                    .setDate( history.getDate() )
                    .setTime( history.getTime() )
                    .setTotalAmount( history.getTotalAmount() )
                    .setTotalPrice( history.getTotalPrice() );
            items.add( item );
        }
        return items;
    }
}
