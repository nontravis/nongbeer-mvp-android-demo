package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail.adapter;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseMvpListAdapterPresenter;

public class OrderDetailAdapterPresenter extends BaseMvpListAdapterPresenter<OrderDetailAdapterInterface.Adapter>
        implements OrderDetailAdapterInterface.Presenter{

    public static OrderDetailAdapterInterface.Presenter create(){
        return new OrderDetailAdapterPresenter();
    }
}
