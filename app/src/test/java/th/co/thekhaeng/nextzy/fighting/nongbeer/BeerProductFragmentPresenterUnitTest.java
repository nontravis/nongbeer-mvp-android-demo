package th.co.thekhaeng.nextzy.fighting.nongbeer;

import android.util.Log;

import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.RxBus;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerServiceManager;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.BeerProductResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.BeerProductFragmentInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.BeerProductFragmentPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.AddBeerToCartEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.ClearAddedButtonStateEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.RemoveBeerFromCartEvent;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by TheKhaeng on 11/30/2016 AD.
 */

@RunWith( PowerMockRunner.class )
@PrepareForTest( {Log.class, RxBus.class, NongBeerServiceManager.class} )
public class BeerProductFragmentPresenterUnitTest{

    @Mock
    BeerProductFragmentInterface.View mockView;
    BeerProductFragmentPresenter presenter;
    BeerProductFragmentPresenter spyPresenter;
    NongBeerServiceManager mockManager;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks( this );
        mockStatic( NongBeerServiceManager.class );
        mockStatic( RxBus.class );

        mockManager = mock( NongBeerServiceManager.class );
        when( NongBeerServiceManager.getInstance() ).thenReturn( mockManager );

        presenter = new BeerProductFragmentPresenter();
        presenter.attachView( mockView );
        spyPresenter = spy( presenter );
        spyPresenter.attachView( mockView );


        Bus mockBus = mock( Bus.class );
        when( RxBus.get() ).thenReturn( mockBus );
    }

    @Test
    public void register_rx(){
        spyPresenter.onViewCreate();
        verify( RxBus.get(), times( 1 ) ).register( spyPresenter );
    }

    @Test
    public void unregister_rx(){
        spyPresenter.onViewDestroy();
        verify( RxBus.get(), times( 1 ) ).unregister( spyPresenter );
    }


    @Test
    public void requestBeerProduct(){
        presenter.requestBeerProduct();

        verify( mockView, times( 1 ) ).showServiceAvailableView();
        verify( mockManager, times( 1 ) ).requestBeer(
                eq( 0 ),
                (NongBeerServiceManager.NongBeerManagerCallback<BeerProductResultGroup>) any() );
    }

    @Test
    public void addBeerItemToCart(){
        presenter.addBeerItemToCart( new BeerProductItem() );

        verify( RxBus.get(), times( 1 ) ).post( any( AddBeerToCartEvent.class ) );
    }

    @Test
    public void deleteBeerItemFromCart(){
        presenter.deleteBeerItemFromCart( new BeerProductItem(), 0 );
        verify( RxBus.get(), times( 1 ) ).post( any( RemoveBeerFromCartEvent.class ) );
    }

    @Test
    public void onChangeAddToCartButtonEvent(){
        presenter.onClearAddedButtonStateEvent( new ClearAddedButtonStateEvent( new BeerProductItem() ) );
        verify( mockView, times( 1 ) ).onClearAddedButtonStateEvent( any( BeerProductItem.class ) );
    }

}