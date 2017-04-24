package th.co.thekhaeng.nextzy.fighting.nongbeer.ui.orderdetail.adapter.holder;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import th.co.thekhaeng.nextzy.fighting.nongbeer.R;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.base.adapter.BaseViewHolder;
import th.co.thekhaeng.nextzy.fighting.nongbeer.ui.main.beer.adapter.item.BeerProductItem;
import th.co.thekhaeng.nextzy.fighting.nongbeer.utils.StringUtils;

/**
 * Created by thekhaeng on 4/6/2017 AD.
 */

public class BeerOrderDetailHolder extends BaseViewHolder{

    private TextView tvName;
    private TextView tvAmount;
    private TextView tvPrice;

    public BeerOrderDetailHolder( ViewGroup viewGroup ){
        super( viewGroup, R.layout.holder_order_detail );
    }

    @Override
    public void bindView( View view ){
        tvName = (TextView) view.findViewById( R.id.tv_name );
        tvAmount = (TextView) view.findViewById( R.id.tv_amount );
        tvPrice = (TextView) view.findViewById( R.id.tv_price );
    }

    @SuppressLint( "SetTextI18n" )
    public void onBind( BeerProductItem item ){
        tvName.setText( item.getName() );
        tvAmount.setText( "x" + item.getAmount() );
        tvPrice.setText( StringUtils.getCommaPriceWithBaht( getContext(), item.getPrice() ) );
    }
}
