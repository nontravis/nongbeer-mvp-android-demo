package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.exception;

/**
 * Created by TheKhaeng on 12/18/2016.
 */

public class MvpPresenterNotCreateException extends RuntimeException{
    public MvpPresenterNotCreateException(){
        super( "Please call createPresenter() before" +
                " requesting data to the Presenter" );
    }
}

