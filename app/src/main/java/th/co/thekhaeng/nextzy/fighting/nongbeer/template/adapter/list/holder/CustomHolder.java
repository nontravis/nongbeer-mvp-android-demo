package th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.list.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.template.adapter.list.item.CustomItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;


/**
 * Created by thekhaeng on 2/6/2017 AD.
 */

public class CustomHolder extends BaseViewHolder{

    private OnClickCustomHolderListener listener;

    public interface OnClickCustomHolderListener{
        void onClick1( CustomHolder view, int position );

        void onClick2( CustomHolder view, int position );

        void onClick3( CustomHolder view, int position );
    }

    public CustomHolder( ViewGroup itemView ){
        super( itemView, R.layout.activity_main );
    }

    @Override
    public void bindView( View view ){

    }

    public void onBind( CustomItem item ){

    }


    public void setOnClickCustomHolderListener( OnClickCustomHolderListener listener ){
        this.listener = listener;
    }

    @NonNull
    private View.OnClickListener onClick1(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClick1( CustomHolder.this, getAdapterPosition() );
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener onClick2(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClick2( CustomHolder.this, getAdapterPosition() );
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener onClick3(){
        return new View.OnClickListener(){
            @Override
            public void onClick( View v ){
                if( listener != null ){
                    listener.onClick3( CustomHolder.this, getAdapterPosition() );
                }
            }
        };
    }
}
