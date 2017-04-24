package th.co.thekhaeng.nextzy.fighting.nongbeer.api.result;

import java.util.List;

/**
 * Created by thekhaeng on 3/20/2017 AD.
 */

public class HistoryResult{
    /**
     * id : 939506
     * status : DELIVERED|UPCOMING
     * date : 03/03/2017
     * time : 23:04
     * totalPrice : 3000
     * totalAmount : 20
     * location : {"latitude":13.7413877,"longitude":100.573252}
     * beers : [{"id":"93024","name":"U Beer","price":80,"amount":10,"image":"www.photobucket.com/s93lkewfl/askldjcb03lksdf.jpg"},{"id":"93030","name":"Singha","price":100,"amount":5,"image":"www.photobucket.com/s93lkewfl/askldjcb03sglkdfdkgjf.jpg"}]
     */

    private String id;
    private String status;
    private String date;
    private String time;
    private int totalPrice;
    private int totalAmount;
    private LocationResult location;
    private List<BeerProductResult> beers;

    public String getId(){
        return id;
    }

    public void setId( String id ){
        this.id = id;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus( String status ){
        this.status = status;
    }

    public String getDate(){
        return date;
    }

    public void setDate( String date ){
        this.date = date;
    }

    public String getTime(){
        return time;
    }

    public void setTime( String time ){
        this.time = time;
    }

    public int getTotalPrice(){
        return totalPrice;
    }

    public void setTotalPrice( int totalPrice ){
        this.totalPrice = totalPrice;
    }

    public int getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount( int totalAmount ){
        this.totalAmount = totalAmount;
    }

    public LocationResult getLocation(){
        return location;
    }

    public void setLocation( LocationResult location ){
        this.location = location;
    }

    public List<BeerProductResult> getBeers(){
        return beers;
    }

    public void setBeers( List<BeerProductResult> beers ){
        this.beers = beers;
    }

    public static class LocationResult{
        /**
         * latitude : 13.7413877
         * longitude : 100.573252
         */

        private double latitude;
        private double longitude;

        public double getLatitude(){
            return latitude;
        }

        public void setLatitude( double latitude ){
            this.latitude = latitude;
        }

        public double getLongitude(){
            return longitude;
        }

        public void setLongitude( double longitude ){
            this.longitude = longitude;
        }


    }


}
