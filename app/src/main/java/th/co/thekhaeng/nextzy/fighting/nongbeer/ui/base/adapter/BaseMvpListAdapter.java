package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.exception.MvpPresenterNotCreateException;


/**
 * Created by TheKhaeng on 12/18/2016.
 */

public abstract class BaseMvpListAdapter<VH extends BaseViewHolder, P extends BaseMvpListAdapterInterface.Presenter>
        extends RecyclerView.Adapter<VH>
        implements BaseMvpListAdapterInterface.Adapter{

    private P presenter;

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    @SuppressWarnings( "unchecked" )
    public BaseMvpListAdapter(){
        presenter = createPresenter();
        presenter.setAdapter( this );
    }

    public abstract P createPresenter();

    @Override
    public P getPresenter(){
        if( presenter != null ) return presenter;
        throw new MvpPresenterNotCreateException();
    }

    @SuppressWarnings( "unchecked" )
    public List<BaseItem> getItems(){
        return getPresenter().getItems();
    }

    public BaseItem getItem( int pos ){
        return getPresenter().getItem( pos );
    }

    public boolean hasItems(){
        return getPresenter().hasItems();
    }

    public void setItems( List<BaseItem> items ){
        getPresenter().setItems( items );
    }

    public void addItem( BaseItem item ){
        getPresenter().addItem( item );
    }

    public void removeItem( int index ){
        getPresenter().removeItem( index );
    }

    public void removeAllItems(){
        getPresenter().removeAllItems();
    }
}
