package com.example.dell.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.List;

/**
 * Created by DELL on 2019/4/22.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    //CarAdapter适配器

    private List<Book> mCarBookList;
    //自定义接口
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View carView;
        TextView carBookName;
        TextView carBookUnitPrice;
        Button carDelete;


        public ViewHolder(View view){
            super(view);
            carView = view;
            carBookName = view.findViewById(R.id.textview_car_bname_sql);
            carBookUnitPrice = view.findViewById(R.id.textview_car_bprice_sql);
            carDelete = view.findViewById(R.id.button_car_delete);
        }
    }

    public CarAdapter(List<Book> carBookList){mCarBookList = carBookList;}

    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CarAdapter.ViewHolder holder, final int position) {
        final Book book = mCarBookList.get(position);
        holder.carBookName.setText(book.getBname());
        //setText()方法只能传入String，所以price要类型转换
        String carBookPrice= String.valueOf(book.getBprice());
        holder.carBookUnitPrice.setText(carBookPrice);
        holder.carDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, position);
            }
        });

    }

    @Override
    public int getItemCount() {return mCarBookList.size();
    }
}
