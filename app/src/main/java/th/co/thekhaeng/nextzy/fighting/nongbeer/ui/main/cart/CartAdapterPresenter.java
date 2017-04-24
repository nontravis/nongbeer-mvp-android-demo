package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.cart;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseMvpListAdapterPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

public class CartAdapterPresenter extends BaseMvpListAdapterPresenter<CartAdapterInterface.Adapter>
        implements CartAdapterInterface.Presenter{


    public static CartAdapterInterface.Presenter create(){
        return new CartAdapterPresenter();
    }


    @Override
    public void increaseItemAmountAt( BeerProductItem beerItem ){
        beerItem.increaseAmount();
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public void decreaseItemAmountAt( BeerProductItem beerItem ){
        beerItem.decreaseAmount();
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public int getTotalPrice(){
        int price = 0;
        for( BaseItem baseItem : getItems() ){
            if( baseItem instanceof BeerProductItem ){
                BeerProductItem beerItem = (BeerProductItem) baseItem;
                price += beerItem.getPrice() * beerItem.getAmount();
            }
        }
        return price;
    }

    @Override
    public void removeItem( BeerProductItem item ){
        for( int i = 0; i < getItems().size(); i++ ){
            if( getItem( i ) instanceof BeerProductItem ){
                if( ( (BeerProductItem) getItem( i ) ).getId().equals( item.getId() ) ){
                    removeItem( i );
                }
            }
        }

    }
}
