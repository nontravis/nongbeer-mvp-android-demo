package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;

public class OrderDetailActivityPresenter
    extends BaseMvpPresenter<OrderDetailActivityInterface.View>
        implements OrderDetailActivityInterface.Presenter{

    public static OrderDetailActivityInterface.Presenter create(){
        return new OrderDetailActivityPresenter();
    }
}
