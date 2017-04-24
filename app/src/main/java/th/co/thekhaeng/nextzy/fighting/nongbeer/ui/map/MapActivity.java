package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.map;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpActivity;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.successorder.SuccessOrderActivity;

/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class MapActivity extends BaseMvpActivity<MapActivityInterface.Presenter>
        implements MapActivityInterface.View, OnMapReadyCallback{

    public static final String EXTRA_PRODUCT_LIST = "extra_product_list";

    public static final int DEFAULT_ZOOM = 16;

    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private ImageView btnBack;
    private Button btnOrder;
    private List<BeerProductItem> items;
    private CardView cardLoading;

    @Override
    public MapActivityInterface.Presenter createPresenter(){
        return MapActivityPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.activity_maps;
    }

    @Override
    public void bindView(){
        btnBack = (ImageView) findViewById( R.id.btn_back );
        btnOrder = (Button) findViewById( R.id.btn_order );
        cardLoading = (CardView) findViewById( R.id.card_loading );
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
    }

    @Override
    public void setupInstance(){
        items = getIntent().getParcelableArrayListExtra( EXTRA_PRODUCT_LIST );
        if( items == null ){
            throw new NullPointerException( "You must send BeerProductItems to this activity." );
        }
        checkMapPermissionAndStartMap();
    }

    private void checkMapPermissionAndStartMap(){
        Dexter.withActivity( this )
                .withPermissions( Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION )
                .withListener( new MultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked( MultiplePermissionsReport report ){
                        if( !hasDeniedPermission( report ) ){
                            startMap();
                        }else{
                            finish();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown( List<PermissionRequest> permissions, PermissionToken token ){
                        token.continuePermissionRequest(); // show request dialog
                    }

                    private boolean hasDeniedPermission( MultiplePermissionsReport report ){
                        List<PermissionDeniedResponse> denyPermission = report.getDeniedPermissionResponses();
                        return denyPermission != null && denyPermission.size() > 0;
                    }

                } ).check();
    }

    private void startMap(){
        if( isLocationEnable() ){
            mapFragment.getMapAsync( this );
        }else{
            Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
            startActivity( myIntent );
        }
    }

    @Override
    public void setupView(){
        btnBack.setOnClickListener( onClickBlack() );
        btnOrder.setOnClickListener( onClickOrder() );
    }

    @Override
    public void initialize(){
    }

    @Override
    protected void onResume(){
        super.onResume();
        checkMapPermissionAndStartMap();
    }

    @Override
    protected void onDestroy(){
        getPresenter().cancelRequest();
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        getPresenter().cancelRequest();
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
    }

    @Override
    public void onMapReady( GoogleMap googleMap ){
        map = googleMap;
        if( isLocationEnable() ){
            Location location = getLastKnownLocation();
            if( location != null ){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                LatLng current = new LatLng( latitude, longitude );
                map.moveCamera( CameraUpdateFactory.newLatLngZoom( current, DEFAULT_ZOOM ) );
            }
        }
        setupMap();
    }

    @Override
    public void goToSuccessOrderActivity(){
        Intent i = new Intent( this, SuccessOrderActivity.class );
        startActivity( i );
        setResult( Activity.RESULT_OK );
        finish();
    }

    @Override
    public void showFailMessage(){
        clearButtonLoadingState();
        Toast.makeText( this, "Cannot order beer.", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void clearButtonLoadingState(){
        btnOrder.setVisibility( View.VISIBLE );
        cardLoading.setVisibility( View.GONE );
    }

    private void setupMap(){
        if( ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ){
            return;
        }
        map.setMyLocationEnabled( true );
    }

    private Location getLastKnownLocation(){
        if( ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ){
            return null;
        }
        LocationManager locationManager =
                (LocationManager) this.getSystemService( LOCATION_SERVICE );
        List<String> providers = locationManager.getProviders( true );
        Location bestLocation = null;
        for( String provider : providers ){
            Location l = locationManager.getLastKnownLocation( provider );
            if( l == null ){
                continue;
            }
            if( bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy() ){
                bestLocation = l; // Found best last known location;
            }
        }
        return bestLocation;
    }


    private boolean isLocationEnable(){
        LocationManager locationManager =
                (LocationManager) this.getSystemService( Context.LOCATION_SERVICE );
        return locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER );
    }

    private LatLng getCenterLatLngPosition(){
        return map.getCameraPosition().target;
    }

    @NonNull
    private View.OnClickListener onClickBlack(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                onBackPressed();
            }
        };
    }

    @NonNull
    private View.OnClickListener onClickOrder(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                showButtonLoadingState();
                LatLng latlng = getCenterLatLngPosition();
                getPresenter().requestAddNewOrder( latlng.latitude, latlng.longitude, items );
            }
        };
    }

    private void showButtonLoadingState(){
        btnOrder.setVisibility( View.GONE );
        cardLoading.setVisibility( View.VISIBLE );
    }

}

