package th.co.thekhaeng.nextzy.fighting.nongbeer.api;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.request.AddNewOrderBody;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.AddNewOrderResult;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.BeerProductResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.HistoryResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;

import static th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerURL.BASE_URL;

public class NongBeerServiceManager{

    public static final String SUCCESS = "SUCCESS";

    private static NongBeerServiceManager instance;
    private static NongBeerApiService api;
    private Call<AddNewOrderResult> callAddNewOrder;

    public interface NongBeerManagerCallback<T>{
        void onSuccess( T result );

        void onFailure( Throwable t );
    }

    public static NongBeerServiceManager getInstance(){
        if( instance == null ){
            instance = new NongBeerServiceManager();
        }
        return instance;
    }

    private NongBeerServiceManager(){
    }

    public static void setApi( NongBeerApiService mockApi ){
        api = mockApi;
    }


    public void requestBeer( int page, final NongBeerManagerCallback<BeerProductResultGroup> callback ){
        requestBeerCall( page ).enqueue( new Callback<BeerProductResultGroup>(){
            @Override
            public void onResponse( Call<BeerProductResultGroup> call, Response<BeerProductResultGroup> response ){
                if( callback != null ){
                    if( beerChecker( response ) ){
                        callback.onSuccess( response.body() );
                    }else{
                        callback.onFailure( new Throwable( "Response invalid." ) );
                    }
                }
            }

            @Override
            public void onFailure( Call<BeerProductResultGroup> call, Throwable t ){
                if( !call.isCanceled() ){
                    if( callback != null ){
                        callback.onFailure( t );
                    }
                }
            }
        } );
    }


    public void addNewOrder( double latitude,
                             double longitude,
                             List<BeerProductItem> items,
                             final NongBeerManagerCallback<AddNewOrderResult> callback ){
        List<AddNewOrderBody.BeersBody> beers = new ArrayList<>();
        for( BeerProductItem item : items ){
            beers.add( new AddNewOrderBody.BeersBody()
                    .setId( item.getId() )
                    .setAmount( item.getAmount() ) );
        }

        AddNewOrderBody body = new AddNewOrderBody()
                .setLocation( latitude, longitude )
                .setBeers( beers );

        callAddNewOrder = requestAddNewOrder( body );
        callAddNewOrder.enqueue( new Callback<AddNewOrderResult>(){
            @Override
            public void onResponse( Call<AddNewOrderResult> call, Response<AddNewOrderResult> response ){
                if( callback != null ){
                    if( addNewOrderResultChecker( response ) ){
                        callback.onSuccess( response.body() );
                    }else{
                        callback.onFailure( new Throwable( "response invalid." ) );
                    }
                }
                callAddNewOrder = null;
            }

            @Override
            public void onFailure( Call<AddNewOrderResult> call, Throwable t ){
                if( callback != null ){
                    callback.onFailure( t );
                }
                callAddNewOrder = null;
            }
        } );
    }


    public Call<AddNewOrderResult> requestAddNewOrder( AddNewOrderBody body ){
        return NongBeerService.newInstance( BASE_URL )
                .getApi( api )
                .addNewOrder( body );
    }

    private boolean addNewOrderResultChecker( Response<AddNewOrderResult> response ){
        if( response.isSuccessful() ){
            AddNewOrderResult result = response.body();
            return SUCCESS.equals( result.getStatus() );
        }
        return false;
    }


    public void cancelAddNewOrder(){
        if( callAddNewOrder != null ){
            callAddNewOrder.cancel();
        }
    }


    public Call<BeerProductResultGroup> requestBeerCall( int page ){
        return NongBeerService.newInstance( BASE_URL )
                .getApi( api )
                .getBeer( page );
    }

    public void requestHistory( int page, final NongBeerManagerCallback<HistoryResultGroup> callback ){
        requestHistoryCall( page ).enqueue( new Callback<HistoryResultGroup>(){
            @Override
            public void onResponse( Call<HistoryResultGroup> call, Response<HistoryResultGroup> response ){
                if( callback != null ){
                    if( historyChecker( response ) ){
                        callback.onSuccess( response.body() );
                    }else{
                        callback.onFailure( new Throwable("Response invalid.") );
                    }
                }
            }

            @Override
            public void onFailure( Call<HistoryResultGroup> call, Throwable t ){
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        } );
    }

    public Call<HistoryResultGroup> requestHistoryCall( int page ){
        return NongBeerService.newInstance( BASE_URL )
                .getApi( api )
                .getHistory( page );
    }

    private boolean historyChecker( Response<HistoryResultGroup> response ){
        if( response.isSuccessful() ){
            HistoryResultGroup result = response.body();
            return SUCCESS.equals( result.getStatus() ) && result.getOrders() != null;
        }
        return false;
    }

    private boolean beerChecker( Response<BeerProductResultGroup> response ){
        if( response.isSuccessful() ){
            BeerProductResultGroup result = response.body();
            return SUCCESS.equals( result.getStatus() ) && result.getBeers() != null;
        }
        return false;
    }


}
