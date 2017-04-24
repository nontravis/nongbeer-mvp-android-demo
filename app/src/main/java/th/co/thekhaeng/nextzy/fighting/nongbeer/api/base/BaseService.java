package th.co.thekhaeng.nextzy.fighting.nongbeer.api.base;


import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.co.thekhaeng.nextzy.fighting.nongbeer.BuildConfig;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.interceptor.HttpLogger;


/**
 * Created by thekhaeng on 3/20/2017 AD.
 */
public abstract class BaseService<T>{
    private static final int TIMEOUT = 10000;

    private String baseUrl;
    private T api;

    public String getBaseUrl(){
        return baseUrl;
    }

    public void setBaseUrl( String baseUrl ){
        this.baseUrl = baseUrl;
    }


    public T getApi( T api ){
        this.api = api;
        if( this.api == null ){
            this.api = createApi();
        }
        return this.api;
    }

    /* ============================= Private method ============================================= */
    private T createApi(){
        return getBaseRetrofitBuilder()
                .build()
                .create( getApiClassType() );
    }

    private Retrofit.Builder getBaseRetrofitBuilder(){
        return new Retrofit.Builder()
                .baseUrl( getBaseUrl() )
                .addConverterFactory( addConverter() )
                .client( getClient() );
    }

    private Converter.Factory addConverter(){
        return GsonConverterFactory.create( new GsonBuilder().setPrettyPrinting().create() );
    }

    private OkHttpClient getClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder
                .addNetworkInterceptor( getDefaultHttpLogging( BuildConfig.DEBUG ) )
                .certificatePinner( getDefaultCertificatePinner() )
                .readTimeout( TIMEOUT, TimeUnit.MILLISECONDS )
                .writeTimeout( TIMEOUT, TimeUnit.MILLISECONDS )
                .connectTimeout( TIMEOUT, TimeUnit.MILLISECONDS )
                .build();
    }

    private HttpLoggingInterceptor getDefaultHttpLogging( boolean isLog ){
        if( isLog ){
            return new HttpLoggingInterceptor( new HttpLogger() ).setLevel( HttpLoggingInterceptor.Level.BODY );
        }
        return new HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.NONE );
    }

    private CertificatePinner getDefaultCertificatePinner(){
        return new CertificatePinner.Builder().build();
    }


    //every network service class must inherit this class and set the class type, too
    protected abstract Class<T> getApiClassType();

}
