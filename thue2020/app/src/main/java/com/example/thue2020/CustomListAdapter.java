package com.example.thue2020;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thue2020.modal.declareTax;

import java.sql.Date;
import java.util.List;

public class CustomListAdapter  extends BaseAdapter {

    private List<declareTax> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext,  List<declareTax> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.tvTaxPeriodItem = (TextView) convertView.findViewById(R.id.tvTaxPeriodItem);
            holder.tvStatusDeclareTaxItem = (TextView) convertView.findViewById(R.id.tvStatusDeclareTaxItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        declareTax declareTax = this.listData.get(position);
        holder.tvTaxPeriodItem.setText(declareTax.getTaxPeriod().toString());
        String status = declareTax.getPaymentDate().toString();
        if (declareTax.getPaymentDate().equals(new Date(0))) status = "chưa trả";
        holder.tvStatusDeclareTaxItem.setText(status);

        return convertView;
    }

    static class ViewHolder {
        TextView tvTaxPeriodItem;
        TextView tvStatusDeclareTaxItem;
    }

}
