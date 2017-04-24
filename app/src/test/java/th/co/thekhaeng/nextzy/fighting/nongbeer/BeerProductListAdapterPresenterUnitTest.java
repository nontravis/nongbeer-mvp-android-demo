package th.co.thekhaeng.nextzy.fighting.nongbeer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.BeerProductAdapterInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.BeerProductAdapterPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by TheKhaeng on 11/30/2016 AD.
 */

@RunWith( PowerMockRunner.class )
public class BeerProductListAdapterPresenterUnitTest{

    @Mock
    BeerProductAdapterInterface.Adapter mockAdapter;
    BeerProductAdapterPresenter presenter;
    BeerProductAdapterPresenter spyPresenter;

    @Before
    public void setUp(){
        presenter = new BeerProductAdapterPresenter();
        presenter.setAdapter( mockAdapter );
        spyPresenter = spy( presenter );
        spyPresenter.setAdapter( mockAdapter );
    }

    @Test
    public void setItems(){
        List<BaseItem> paramItems = new ArrayList<>();
        paramItems.add( new BeerProductItem() );
        paramItems.add( new BeerProductItem() );
        paramItems.add( new BeerProductItem() );
        paramItems.add( new BeerProductItem() );

        spyPresenter.setItems( paramItems, false );

        verify( mockAdapter, times( 1 ) ).notifyDataSetChanged();
        assertThat( spyPresenter.getItems(), hasSize( 4 ) );
    }

    @Test
    public void addItem(){
        BeerProductItem item = new BeerProductItem();
        spyPresenter.addItem( item );
        verify( mockAdapter, times( 1 ) ).notifyItemInserted( 0 );
        assertThat( spyPresenter.getItems(), hasSize( 1 ) );

        spyPresenter.addItem( item );
        verify( mockAdapter, times( 1 ) ).notifyItemInserted( 1 );
        assertThat( spyPresenter.getItems(), hasSize( 2 ) );
    }


    @Test
    public void removeAll(){
        List<BaseItem> paramItems = new ArrayList<>();
        paramItems.add( new BeerProductItem() );
        paramItems.add( new BeerProductItem() );
        paramItems.add( new BeerProductItem() );
        paramItems.add( new BeerProductItem() );
        spyPresenter.setItems( paramItems, false );

        spyPresenter.removeAllItems();
        verify( mockAdapter, times( 2 ) ).notifyDataSetChanged();
        assertThat( spyPresenter.getItems(), hasSize( 0 ) );
    }

}