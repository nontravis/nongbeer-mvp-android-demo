package th.co.thekhaeng.nextzy.fighting.nongbeer.api.result;

import java.util.List;

/**
 * Created by thekhaeng on 3/20/2017 AD.
 */

public class HistoryResultGroup{

    /**
     * status : SUCCESS
     * message : Retrieve the list of order successfully.
     * nextOrderAvailable : false
     * orders : [{"id":"939506","status":"DELIVERED|UPCOMING","date":"03/03/2017","time":"23:04","totalPrice":3000,"totalAmount":20,"location":{"latitude":13.7413877,"longitude":100.573252},"beers":[{"id":"93024","name":"U Beer","price":80,"amount":10,"image":"www.photobucket.com/s93lkewfl/askldjcb03lksdf.jpg"},{"id":"93030","name":"Singha","price":100,"amount":5,"image":"www.photobucket.com/s93lkewfl/askldjcb03sglkdfdkgjf.jpg"}]}]
     */

    private String status;
    private String message;
    private boolean nextOrderAvailable;
    private int nextOrderIndex;
    private List<HistoryResult> orders;

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

    public boolean isNextOrderAvailable(){
        return nextOrderAvailable;
    }

    public void setNextOrderAvailable( boolean nextOrderAvailable ){
        this.nextOrderAvailable = nextOrderAvailable;
    }

    public List<HistoryResult> getOrders(){
        return orders;
    }

    public void setOrders( List<HistoryResult> orders ){
        this.orders = orders;
    }

    public int getNextOrderIndex(){
        return nextOrderIndex;
    }

    public void setNextOrderIndex( int nextOrderIndex ){
        this.nextOrderIndex = nextOrderIndex;
    }

}
