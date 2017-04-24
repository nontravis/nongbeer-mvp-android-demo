package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter;

import java.util.List;


/**
 * Created by TheKhaeng on 12/18/2016.
 */

public interface BaseMvpListAdapterInterface{

    /**
     * Base interface that any class that wants to act as a Adapter in the MVP (Model View Presenter)
     * pattern must implement. Generally this interface will be extended by a more specific interface
     * that then usually will be implemented by an Activity or Fragment.
     */
    interface Adapter{
        Presenter getPresenter();

        void notifyDataSetChanged();

        void notifyItemInserted( int index );

        void notifyItemRemoved( int index );
    }

    /**
     * Required Adapter methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     * Presenter to View
     */
    interface Presenter<A extends BaseMvpListAdapterInterface.Adapter>{
        void setAdapter( A adapter );

        A getAdapter();

        int getItemViewType( int pos );

        int getItemCount();

        boolean hasItems();

        List<BaseItem> getItems();

        BaseItem getItem( int pos );

        void setItems( List<BaseItem> items );

        void addItem( BaseItem item );

        void removeItem( int index );

        void removeAllItems();

    }
}
