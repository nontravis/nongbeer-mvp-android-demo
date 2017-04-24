package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.BeerProductResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.BeerProductResult;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItemGroup;

/**
 * Created by thekhaeng on 4/10/2017 AD.
 */

public class BeerConverter{


    public static BeerProductItemGroup createBeerGroupItemFromResult( BeerProductResultGroup result ){
        BeerProductItemGroup group = new BeerProductItemGroup();
        group.setStatus( result.getStatus() );
        group.setMessage( result.getMessage() );
        group.setNextBeerAvailable( result.isNextBeerAvailable() );
        group.setNextBeerIndex( result.getNextBeerIndex() );
        group.setBeers( BeerConverter.createBeerProductItemsFromResult( result.getBeers() ) );
        return group;
    }

    public static List<BeerProductItem> createBeerProductItemsFromResult( List<BeerProductResult> result){
        List<BeerProductItem> items = new ArrayList<>();
        for( BeerProductResult beerProductResult : result ){
            BeerProductItem item = new BeerProductItem()
                    .setId( beerProductResult.getId() )
                    .setAlcohol( beerProductResult.getAlcohol() )
                    .setImage( beerProductResult.getImage() )
                    .setName( beerProductResult.getName() )
                    .setPrice( beerProductResult.getPrice() )
                    .setAmount( beerProductResult.getAmount() )
                    .setVolume( beerProductResult.getVolume() );
            items.add( item );
        }
        return items;
    }
}
