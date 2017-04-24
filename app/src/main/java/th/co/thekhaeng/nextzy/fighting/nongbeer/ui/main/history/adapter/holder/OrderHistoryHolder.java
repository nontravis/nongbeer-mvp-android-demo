package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.holder;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.history.adapter.item.HistoryItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.utils.StringUtils;

/**
 * Created by thekhaeng on 4/6/2017 AD.
 */

public class OrderHistoryHolder extends BaseViewHolder{

    private TextView tvDate;
    private TextView tvAmount;
    private TextView tvTotalPrice;

    public OrderHistoryHolder( ViewGroup viewGroup ){
        super( viewGroup, R.layout.holder_order_history );
    }

    @Override
    public void bindView( View view ){
        tvDate = (TextView) view.findViewById( R.id.tv_date );
        tvAmount = (TextView) view.findViewById( R.id.tv_beer_amount );
        tvTotalPrice = (TextView) view.findViewById( R.id.tv_total_price );
    }

    @SuppressLint( "SetTextI18n" )
    public void onBind( HistoryItem item ){
        tvDate.setText( item.getDate() + StringUtils.getAtString( getContext() ) + item.getTime() );
        tvAmount.setText( item.getTotalAmount() + StringUtils.getBottleOfBeerString( getContext() ) );
        tvTotalPrice.setText( StringUtils.getCommaPriceWithBaht( getContext(), item.getTotalPrice() ) );
    }

    public void setOnClickItem( View.OnClickListener listener ){
        itemView.setOnClickListener( listener );
    }

}
