package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.successorder;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;

public class SuccessOrderActivityPresenter extends BaseMvpPresenter<SuccessOrderActivityInterface.View>
        implements SuccessOrderActivityInterface.Presenter{

    public static SuccessOrderActivityInterface.Presenter create(){
        return new SuccessOrderActivityPresenter();
    }

}
