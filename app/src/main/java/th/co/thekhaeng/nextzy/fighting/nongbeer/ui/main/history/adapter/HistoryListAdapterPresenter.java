package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore.LoadmoreAdapterPresenter;

public class HistoryListAdapterPresenter
    extends LoadmoreAdapterPresenter<HistoryListAdapterInterface.Adapter>
        implements HistoryListAdapterInterface.Presenter{

    public static HistoryListAdapterInterface.Presenter create(){
        return new HistoryListAdapterPresenter();
    }
}
