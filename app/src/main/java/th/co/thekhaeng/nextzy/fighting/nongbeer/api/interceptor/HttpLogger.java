package th.co.thekhaeng.nextzy.fighting.nongbeer.api.interceptor;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by thekhaeng on 3/20/2017 AD.
 */
public class HttpLogger implements HttpLoggingInterceptor.Logger{
    private final static String TAG = HttpLogger.class.getSimpleName();

    @Override
    public void log( String message ){
        final String logName = "OkHttp";
        if( !message.startsWith( "{" ) ){
            Log.d( logName, message );
            return;
        }
        try{
            String prettyPrintJson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson( new JsonParser().parse( message ) );
            Logger.init().methodCount( 1 ).hideThreadInfo();
            Logger.t( logName ).json( prettyPrintJson );
        }catch( JsonSyntaxException m ){
            Log.e( TAG, "html header parse failed" );
            m.printStackTrace();
            Log.e( logName, message );
        }
    }
}
