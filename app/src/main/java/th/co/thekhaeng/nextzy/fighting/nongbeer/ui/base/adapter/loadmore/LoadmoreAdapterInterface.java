package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.loadmore;

import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseMvpListAdapterInterface;


/**
 * Created by TheKhaeng on 12/18/2016.
 */

public interface LoadmoreAdapterInterface{

    interface Adapter extends BaseMvpListAdapterInterface.Adapter{
    }

    interface Presenter<A extends LoadmoreAdapterInterface.Adapter>
            extends BaseMvpListAdapterInterface.Presenter<A>{
        void setItems( List<BaseItem> items, boolean isNextItemAvailable );
    }
}

