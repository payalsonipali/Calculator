package com.enpik.calculator.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.enpik.calculator.R;
import com.enpik.calculator.model.OperationHistoryModel;
import java.util.List;


public class ListViewAdapter extends  ArrayAdapter<String>{
    Context currentContext;
    List<OperationHistoryModel> operationHistoryModelList;
    public ListViewAdapter(@NonNull Context context, List<OperationHistoryModel> operationHistoryModelList) {
        super(context, R.layout.history_list_item);
        this.operationHistoryModelList = operationHistoryModelList;
        this.currentContext = context;
    }

    @Override
    public int getCount() {
        return operationHistoryModelList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater mInflater = (LayoutInflater) currentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.history_list_item, parent, false);
        viewHolder.operation = convertView.findViewById(R.id.operation);
        viewHolder.result = convertView.findViewById(R.id.result);
        convertView.setTag(viewHolder);
        viewHolder.operation.setText(operationHistoryModelList.get(position).getOperation());
        viewHolder.result.setText("="+ operationHistoryModelList.get(position).getResult());
        return convertView;
    }

    static class ViewHolder{
        TextView operation;
        TextView result;
    }
}