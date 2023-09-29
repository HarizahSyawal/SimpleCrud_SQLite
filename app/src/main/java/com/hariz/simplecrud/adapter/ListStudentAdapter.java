package com.hariz.simplecrud.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hariz.simplecrud.R;
import com.hariz.simplecrud.model.Student;

import java.util.List;

public class ListStudentAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Student> lists;

    public ListStudentAdapter(Activity activity, List<Student> list) {
        this.activity = activity;
        this.lists = list;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null && inflater != null){
            convertView = inflater.inflate(R.layout.list_students, null);
        }
        if (convertView != null) {
            TextView name = convertView.findViewById(R.id.tvName);
            TextView email = convertView.findViewById(R.id.tvEmail);
            TextView matric_num = convertView.findViewById(R.id.tvMatricNum);
            Student student = lists.get(position);
            name.setText(student.getName());
            email.setText(student.getEmail());
            matric_num.setText(student.getMatricnum());
        }
        return convertView;
    }
}
