package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nonthawit on 7/30/2016.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{

    public BaseViewHolder( ViewGroup parent, int layout ){
        super( LayoutInflater
                .from( parent.getContext() )
                .inflate( layout, parent, false ) );
        bindView( itemView );
    }

    public abstract void bindView( View view );

    protected Context getContext(){
        return itemView.getContext();
    }
}
