package com.yummy.maps.Activities;


import android.app.Fragment;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yummy.maps.Adapters.OrdersAdapter;
import com.yummy.maps.Entities.Order;
import com.yummy.maps.PointFinder;
import com.yummy.maps.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    ArrayList<Order> orders = new ArrayList<Order>();
    OrdersAdapter ordersAdapter;
    View view;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View view = getActivity().findViewById(R.id.frag_orders);
        if(view != null){
            orders.clear();
            PointFinder pf = new PointFinder();
            pf.execute();
            orders = pf.getOrders();
            ordersAdapter = new OrdersAdapter(getActivity(), orders);
            ListView lvOrders = (ListView) view.findViewById(R.id.list_Orders);
            lvOrders.setAdapter(ordersAdapter);
        }
    }
}
