package com.example.dell.litepaltest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL on 2019/4/22.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    //OrderAdapter适配器
    private List<ShoppingCar> mOrdeerList;

    //自定义接口
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        View orderView;
        TextView orderTime;


        public ViewHolder(View view){
            super(view);
            orderView = view;
            orderTime = view.findViewById(R.id.textview_order_time_display);
        }
    }

    public OrderAdapter(List<ShoppingCar> shoppingCars){mOrdeerList = shoppingCars;}

    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, final int position) {
        final ShoppingCar shoppingCar = mOrdeerList.get(position);
        //setText()方法只能传入String，所以price要类型转换
        String str_orderTime= String.valueOf(shoppingCar.getTiem());
        holder.orderTime.setText(str_orderTime);
        //点击最外层布局事件，使用了接口回调
        holder.orderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mOrdeerList.size();
    }
}
