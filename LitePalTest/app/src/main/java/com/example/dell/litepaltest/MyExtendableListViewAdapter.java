package com.example.dell.litepaltest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * Created by DELL on 2019/4/20.
 */

public class MyExtendableListViewAdapter extends BaseExpandableListAdapter {

//    private Context mcontext;
    private String[] groupString = {"经济", "政治／军事", "文化", "计算机／网络"};
    private String[][] childString = {
            {"经济数学", "区域经济", "财政税收", "贸易政策"},
            {"政治理论", "中国政治", "世界政治 ", "军事"},
            {"文化理论", "世界文化", "中国文化", "文化评述 "},
            {"计算机理论", "操作系统", "数据库", "程序设计"}
    };

    @Override
    // 获取分组的个数
    public int getGroupCount() {
        return groupString.length;
    }

    @Override
    //获取指定分组中的子选项的个数
    public int getChildrenCount(int groupPosition) {
        return childString[groupPosition].length;
    }

    @Override
    //获取指定的分组数据
    public Object getGroup(int groupPosition) {
        return groupString[groupPosition];
    }

    @Override
    //获取指定分组中的指定子选项数据
    public Object getChild(int groupPosition, int childPosition) {
        return childString[groupPosition][childPosition];
    }

    @Override
    //获取指定分组的ID, 这个ID必须是唯一的
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    //获取子选项的ID, 这个ID必须是唯一的
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    public boolean hasStableIds() {
        return true;
    }

    @Override
    /**
     *
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded 该组是展开状态还是伸缩状态
     * @param convertView 重用已有的视图对象
     * @param parent 返回的视图对象始终依附于的视图组
     */
    // 获取显示指定分组的视图
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fclass_item,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = convertView.findViewById(R.id.label_group_normal);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.tvTitle.setText(groupString[groupPosition]);
        return convertView;
    }

    @Override
    /**
     *
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     *
     * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild 子元素是否处于组中的最后一个
     * @param convertView 重用已有的视图(View)对象
     * @param parent 返回的视图(View)对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, android.view.View,
     *      android.view.ViewGroup)
     */

    //取得显示给定分组给定子位置的数据用的视图
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sclass_item,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.expand_child);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvTitle.setText(childString[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    //指定位置上的子元素是否可选中
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView tvTitle;
    }

    static class ChildViewHolder {
        TextView tvTitle;

    }

}
