package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore.LoadmoreAdapterPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

public class BeerProductAdapterPresenter
        extends LoadmoreAdapterPresenter<BeerProductAdapterInterface.Adapter>
        implements BeerProductAdapterInterface.Presenter{

    public static BeerProductAdapterInterface.Presenter create(){
        return new BeerProductAdapterPresenter();
    }


    @Override
    public void clearAddState( BeerProductItem item ){
        for( BaseItem baseItem : getItems() ){
            if( baseItem instanceof BeerProductItem ){
                BeerProductItem beerProductItem = (BeerProductItem) baseItem;
                if( item != null && item.getId().equals( beerProductItem.getId() ) ){
                    beerProductItem.setAdded( false );
                    break;
                }
            }
        }
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void clearAddStateAll(){
        for( BaseItem baseItem : getItems() ){
            if( baseItem instanceof BeerProductItem ){
                BeerProductItem beerProductItem = (BeerProductItem) baseItem;
                beerProductItem.setAdded( false );
            }
        }
        getAdapter().notifyDataSetChanged();
    }
}
