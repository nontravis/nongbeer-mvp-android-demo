package th.co.thekhaeng.nextzy.fighting.nongbeer;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

/**
 * Created by thekhaeng on 4/4/2017 AD.
 */

public class NongBeerApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Hawk.init(getApplicationContext()).build();
    }
}
