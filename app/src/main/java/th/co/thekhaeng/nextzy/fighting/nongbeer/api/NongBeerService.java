package th.co.thekhaeng.nextzy.fighting.nongbeer.api;


import th.co.thekhaeng.nextzy.fighting.nongbeer.api.base.BaseService;

/**
 * Created by TheKhaeng on 9/15/2016 AD.
 */

public class NongBeerService extends BaseService<NongBeerApiService>{

    public static NongBeerService newInstance( String baseUrl ){
        NongBeerService service = new NongBeerService();
        service.setBaseUrl( baseUrl );
        return service;
    }

    private NongBeerService(){
    }

    @Override
    protected Class<NongBeerApiService> getApiClassType(){
        return NongBeerApiService.class;
    }
}
