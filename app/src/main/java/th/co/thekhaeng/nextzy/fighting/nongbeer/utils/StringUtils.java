package th.co.thekhaeng.nextzy.fighting.nongbeer.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.text.DecimalFormat;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;

/**
 * Created by thekhaeng on 4/13/2017 AD.
 */

public class StringUtils{

    @NonNull
    public static String getBahtString( Context context ){
        return context.getResources().getString( R.string.baht );
    }

    @NonNull
    public static String getAtString( Context context ){
        return " " + context.getResources().getString( R.string.at ) + " ";
    }

    @NonNull
    public static String getBottleOfBeerString( Context context ){
        return " " + context.getResources().getString( R.string.bottles_of_beer );
    }

    @NonNull
    public static String getBeerString( Context context ){
        return context.getResources().getString( R.string.beer );
    }

    @NonNull
    public static String getHistoryString( Context context ){
        return context.getResources().getString( R.string.history );
    }

    @NonNull
    public static String getCommaPriceWithBaht( Context context, int price ){
        DecimalFormat formatter = new DecimalFormat( "#,###,###" );
        return formatter.format( price ) + getBahtString( context );
    }
}
