package th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.list;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore.LoadmoreAdapterPresenter;

public class CustomListAdapterPresenter extends LoadmoreAdapterPresenter<CustomListAdapterInterface.Adapter>
        implements CustomListAdapterInterface.Presenter{

    public static CustomListAdapterInterface.Presenter create(){
        return new CustomListAdapterPresenter();
    }
}
