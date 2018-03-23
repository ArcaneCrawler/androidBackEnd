package com.yummy.maps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yummy.maps.Entities.Order;

import java.util.ArrayList;

/**
 * Created by Man on 23.03.2018.
 */

public class OrdersAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Order> objects;

    public OrdersAdapter(Context ctx, ArrayList<Order> objects) {
        this.ctx = ctx;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_item_orders, parent, false);
        }

        Order order = getOrder(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.deliverItem)).setText(order.getDeliverItem());
        ((TextView) view.findViewById(R.id.comment)).setText(order.getComment1());
        ((TextView) view.findViewById(R.id.addressFrom)).setText(order.getAddressFrom());
        ((TextView) view.findViewById(R.id.addressTo)).setText(order.getAddressTo());
        ((TextView) view.findViewById(R.id.costOrder)).setText(order.getCostOrder() + " RUB");
        ((ImageView) view.findViewById(R.id.imageOrder)).setImageResource(R.drawable.ic_dashboard_black_24dp);

        return view;
    }

    private Order getOrder(int position) {
        return ((Order) getItem(position));
    }

}
