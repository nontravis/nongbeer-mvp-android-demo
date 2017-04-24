package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history;


import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpInterface;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItemGroup;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class HistoryFragmentInterface{


    public interface View extends BaseMvpInterface.View{

        void dismissSwipeLayout();

        void showServiceUnavailableView();

        void setHistoryItemToAdapter( List<BaseItem> items, boolean nextOrderAvailable );

    }

    public interface Presenter extends  BaseMvpInterface.Presenter<HistoryFragmentInterface.View>{

        void goToOrderDetailActivity( HistoryItem item );

        void requestHistory();

        void setHistoryToAdapter( HistoryItemGroup result );

        HistoryItemGroup getHistoryItemGroup();

        void setHistoryItemGroup( HistoryItemGroup historyResult );
    }
}
