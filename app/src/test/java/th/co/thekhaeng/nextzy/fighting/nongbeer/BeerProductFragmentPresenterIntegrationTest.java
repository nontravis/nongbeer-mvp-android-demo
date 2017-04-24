package th.co.thekhaeng.nextzy.fighting.nongbeer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerApiService;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerServiceManager;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.BeerProductResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.BeerProductFragmentInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.BeerProductFragmentPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.utils.JsonMockUtility;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by TheKhaeng on 11/30/2016 AD.
 */

@RunWith( PowerMockRunner.class )
public class BeerProductFragmentPresenterIntegrationTest{

    @Mock
    BeerProductFragmentInterface.View mockView;
    BeerProductFragmentPresenter presenter;
    BeerProductFragmentPresenter spyPresenter;

    @Captor
    private ArgumentCaptor<Callback<BeerProductResultGroup>> retrofitCallBack;

    @Mock
    private Call<BeerProductResultGroup> mockCall;

    @Mock
    NongBeerApiService mockApi;

    private NongBeerServiceManager spyManager;
    private JsonMockUtility jsonUtil;

    @Before
    public void setUp(){
        jsonUtil = new JsonMockUtility();
        MockitoAnnotations.initMocks( this );

        NongBeerServiceManager manager = NongBeerServiceManager.getInstance();
        spyManager = spy( manager );
        spyManager.setApi( mockApi );

        presenter = new BeerProductFragmentPresenter();
        presenter.setManager( spyManager );
        presenter.attachView( mockView );
        spyPresenter = spy( presenter );
        spyPresenter.setManager( spyManager );
        spyPresenter.attachView( mockView );
    }

    @Test
    public void requestBeerProduct_success(){
        BeerProductResultGroup mockResult = jsonUtil.getJsonToMock(
                "get_beer_list_success.json",
                BeerProductResultGroup.class );
        Response<BeerProductResultGroup> mockResponse = Response.success( mockResult );
        when( spyManager.requestBeerCall( anyInt() ) ).thenReturn( mockCall );

        // start test
        presenter.requestBeerProduct();

        verify( mockCall ).enqueue( retrofitCallBack.capture() );
        retrofitCallBack.getValue().onResponse( mockCall, mockResponse );
        verify( mockView, times( 1 ) ).setBeerProductItemToAdapter( (List<BaseItem>) anyObject(), eq(true) );
    }


    @Test
    public void requestBeerProduct_fail(){
        Throwable throwable = new Throwable();
        BeerProductResultGroup mockResult = jsonUtil.getJsonToMock(
                "get_beer_list_success.json",
                BeerProductResultGroup.class );
        mockResult.setStatus( "error" );
        when( spyManager.requestBeerCall( anyInt() ) ).thenReturn( mockCall );

        // start test
        presenter.requestBeerProduct();

        verify( mockCall ).enqueue( retrofitCallBack.capture() );
        retrofitCallBack.getValue().onFailure( mockCall, throwable );
        verify( mockView, times( 1 ) ).showServiceUnavailableView();
    }

    @Test
    public void requestBeerProduct_fail_status(){
        BeerProductResultGroup mockResult = jsonUtil.getJsonToMock(
                "get_beer_list_success.json",
                BeerProductResultGroup.class );
        mockResult.setStatus( "error" );
        Response<BeerProductResultGroup> mockResponse = Response.success( mockResult );
        when( spyManager.requestBeerCall( anyInt() ) ).thenReturn( mockCall );

        // start test
        presenter.requestBeerProduct();

        verify( mockCall ).enqueue( retrofitCallBack.capture() );
        retrofitCallBack.getValue().onResponse( mockCall, mockResponse );
        verify( mockView, times( 1 ) ).showServiceUnavailableView();
    }

    @Test
    public void requestBeerProduct_fail_beer_list_null(){
        BeerProductResultGroup mockResult = jsonUtil.getJsonToMock(
                "get_beer_list_success.json",
                BeerProductResultGroup.class );
        mockResult.setBeers( null );
        Response<BeerProductResultGroup> mockResponse = Response.success( mockResult );
        when( spyManager.requestBeerCall( anyInt() ) ).thenReturn( mockCall );

        // start test
        presenter.requestBeerProduct();

        verify( mockCall ).enqueue( retrofitCallBack.capture() );
        retrofitCallBack.getValue().onResponse( mockCall, mockResponse );
        verify( mockView, times( 1 ) ).showServiceUnavailableView();
    }
}