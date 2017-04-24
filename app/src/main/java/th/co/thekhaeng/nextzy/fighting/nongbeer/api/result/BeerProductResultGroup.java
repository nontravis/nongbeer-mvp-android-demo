package th.co.thekhaeng.nextzy.fighting.nongbeer.api.result;

import java.util.List;

/**
 * Created by thekhaeng on 3/20/2017 AD.
 */

public class BeerProductResultGroup{

    /**
     * status : SUCCESS
     * message : Retrieve the list of beer successfully.
     * nextBeerAvailable : true
     * beers : [{"id":"0102023","name":"Hoegaarden","price":300,"volume":"300ml","alcohol":"Alc 7.2% vol","image":"www.photobucket.com/s1043/adsacviosdfwel.jpg"},{"id":"0104582","name":"U Beer","price":80,"volume":"620ml","alcohol":"Alc 5% vol","image":"www.photobucket.com/s1043/adslfhkweiofldglkdx.jpg"}]
     */

    private String status;
    private String message;
    private boolean nextBeerAvailable;
    private int nextBeerIndex;
    private List<BeerProductResult> beers;

    public String getStatus(){
        return status;
    }

    public void setStatus( String status ){
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage( String message ){
        this.message = message;
    }

    public boolean isNextBeerAvailable(){
        return nextBeerAvailable;
    }

    public void setNextBeerAvailable( boolean nextBeerAvailable ){
        this.nextBeerAvailable = nextBeerAvailable;
    }

    public List<BeerProductResult> getBeers(){
        return beers;
    }

    public void setBeers( List<BeerProductResult> beers ){
        this.beers = beers;
    }

    public int getNextBeerIndex(){
        return nextBeerIndex;
    }

    public void setNextBeerIndex( int nextBeerIndex ){
        this.nextBeerIndex = nextBeerIndex;
    }

    public BeerProductResultGroup(){
    }
}

