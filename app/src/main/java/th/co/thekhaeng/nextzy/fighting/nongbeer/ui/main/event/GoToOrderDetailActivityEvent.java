package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItem;

/**
 * Created by thekhaeng on 4/8/2017 AD.
 */

public class GoToOrderDetailActivityEvent{


    private HistoryItem item;

    public GoToOrderDetailActivityEvent( HistoryItem item){
        this.item = item;
    }

    public HistoryItem getItem(){
        return item;
    }
}
