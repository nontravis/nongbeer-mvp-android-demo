package th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.list;


import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore.LoadmoreAdapterInterface;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class CustomListAdapterInterface{


    public interface Adapter extends LoadmoreAdapterInterface.Adapter{
    }

    public interface Presenter extends  LoadmoreAdapterInterface.Presenter<Adapter>{
    }
}
