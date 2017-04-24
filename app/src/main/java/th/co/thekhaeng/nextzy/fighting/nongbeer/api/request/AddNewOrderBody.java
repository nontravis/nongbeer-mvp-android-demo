package th.co.thekhaeng.nextzy.fighting.nongbeer.api.request;

import java.util.List;

/**
 * Created by thekhaeng on 3/20/2017 AD.
 */

public class AddNewOrderBody{

    /**
     * location : {"latitude":13.34593845,"longitude":100.87554345}
     * beers : [{"id":"23042057","amount":10}]
     */

    private LocationBody location;
    private List<BeersBody> beers;

    public AddNewOrderBody setLocation( double latitude, double longitude ){
        LocationBody location = new LocationBody()
                .setLatitude( latitude )
                .setLongitude( longitude );
        this.location = location;
        return this;
    }

    public AddNewOrderBody setBeers( List<BeersBody> beers ){
        this.beers = beers;
        return this;
    }

    public LocationBody getLocation(){
        return location;
    }

    public List<BeersBody> getBeers(){
        return beers;
    }

    public static class LocationBody{
        /**
         * latitude : 13.34593845
         * longitude : 100.87554345
         */

        private double latitude;
        private double longitude;

        public LocationBody setLatitude( double latitude ){
            this.latitude = latitude;
            return this;
        }

        public LocationBody setLongitude( double longitude ){
            this.longitude = longitude;
            return this;
        }

        public double getLatitude(){
            return latitude;
        }

        public double getLongitude(){
            return longitude;
        }
    }

    public static class BeersBody{
        /**
         * id : 23042057
         * amount : 10
         */

        private String id;
        private int amount;

        public BeersBody setId( String id ){
            this.id = id;
            return this;
        }

        public BeersBody setAmount( int amount ){
            this.amount = amount;
            return this;
        }

        public String getId(){
            return id;
        }

        public int getAmount(){
            return amount;
        }
    }
}
