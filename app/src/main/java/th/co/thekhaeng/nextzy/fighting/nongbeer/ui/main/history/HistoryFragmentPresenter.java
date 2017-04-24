package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history;


import com.hwangjr.rxbus.RxBus;

import th.co.thekhaeng.nextzy.fighting.nongbeer.api.NongBeerServiceManager;
import th.co.thekhaeng.nextzy.fighting.nongbeer.api.result.HistoryResultGroup;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.BaseMvpPresenter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.event.GoToOrderDetailActivityEvent;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.HistoryConverter;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItemGroup;

public class HistoryFragmentPresenter
        extends BaseMvpPresenter<HistoryFragmentInterface.View>
        implements HistoryFragmentInterface.Presenter{

    private final NongBeerServiceManager serviceManager;
    private HistoryItemGroup itemGroup;

    public static HistoryFragmentInterface.Presenter create(){
        return new HistoryFragmentPresenter();
    }

    public HistoryFragmentPresenter(){
        serviceManager = NongBeerServiceManager.getInstance();
    }

    @Override
    public void goToOrderDetailActivity( HistoryItem item ){
        RxBus.get().post( new GoToOrderDetailActivityEvent( item ) );
    }

    @Override
    public void requestHistory(){
        serviceManager.requestHistory( getNextPage(), new NongBeerServiceManager.NongBeerManagerCallback<HistoryResultGroup>(){
            @Override
            public void onSuccess( HistoryResultGroup result ){
                getView().dismissSwipeLayout();
                HistoryItemGroup newGroup = HistoryConverter.createHistoryItemFromResult( result );
                addOldHistoryToNewHistoryGroupIfAvailable( newGroup );
                itemGroup = newGroup;
                setHistoryToAdapter( itemGroup );
            }

            @Override
            public void onFailure( Throwable t ){
                itemGroup = null;
                getView().dismissSwipeLayout();
                getView().showServiceUnavailableView();
            }


            private void addOldHistoryToNewHistoryGroupIfAvailable( HistoryItemGroup newGroup ){
                if( itemGroup != null ){
                    newGroup.getOrders().addAll( 0, itemGroup.getOrders() );
                }
            }
        } );
    }

    @Override
    public void setHistoryToAdapter( HistoryItemGroup group ){
        getView().setHistoryItemToAdapter( group.getBaseItems(), group.isNextOrderAvailable() );
    }

    @Override
    public HistoryItemGroup getHistoryItemGroup(){
        return itemGroup;
    }

    @Override
    public void setHistoryItemGroup( HistoryItemGroup itemGroup ){
        this.itemGroup = itemGroup;
    }

    private int getNextPage(){
        if( itemGroup == null ) return 0;
        return itemGroup.getNextOrderIndex();
    }

}
