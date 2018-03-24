package com.yummy.maps;


import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.yummy.maps.Entities.Order;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends ListFragment {

    ArrayList<Order> orders = new ArrayList<>();

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ArrayAdapter<Order> listAdapter = new ArrayAdapter<Order>(inflater.getContext(), R.layout.list_item_orders, orders);
        setListAdapter(listAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
        //return inflater.inflate(R.layout.fragment_order, container, false);
    }

}
