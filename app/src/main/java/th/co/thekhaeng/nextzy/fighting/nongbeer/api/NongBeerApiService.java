package th.co.thekhaeng.nextzy.fighting.nongbeer.api;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.request.AddNewOrderBody;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.AddNewOrderResult;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.BeerProductResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.HistoryResultGroup;

import static th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerURL.URL_ADD_ORDER;
import static th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerURL.URL_BEER;
import static th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerURL.URL_HISTORY;


/**
 * Created by TheKhaeng on 9/14/2016 AD.
 */

public interface NongBeerApiService{

    @GET( URL_BEER )
    Call<BeerProductResultGroup> getBeer( @Query( "page" ) int page );

    @GET( URL_HISTORY )
    Call<HistoryResultGroup> getHistory( @Query( "page" ) int page );

    @POST( URL_ADD_ORDER )
    Call<AddNewOrderResult> addNewOrder( @Body AddNewOrderBody body );
}
