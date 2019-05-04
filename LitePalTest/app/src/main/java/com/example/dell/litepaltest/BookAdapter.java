package com.example.dell.litepaltest;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by DELL on 2019/4/14.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    //BookAdapter适配器

    private List<Book> mBookList;
    //自定义接口
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View bookView;
        TextView bookName;
        TextView bookPrice;


        public ViewHolder(View view){
            super(view);
            bookView = view;
            bookName = view.findViewById(R.id.textview_book_name);
            bookPrice = view.findViewById(R.id.textview_book_price);
        }
    }

    public BookAdapter(List<Book> bookList){
        mBookList = bookList;
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder,final int position) {
        final Book book = mBookList.get(position);
        holder.bookName.setText(book.getBname());
        //setText()方法只能传入String，所以price要类型转换
        String bookPrice= String.valueOf(book.getBprice());
        holder.bookPrice.setText(bookPrice);
        //点击最外层布局事件，使用了接口回调
        holder.bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }



}
