package th.co.thekhaeng.nextzy.fighting.nongbeer.api.result;

/**
 * Created by thekhaeng on 3/20/2017 AD.
 */

public class AddNewOrderResult{

    /**
     * status : SUCCESS
     * message : We have received your information successfully.
     */

    private String status;
    private String message;

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
}
