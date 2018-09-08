package com.facerec.tasol.androiarchitecturecomponent.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facerec.tasol.androiarchitecturecomponent.R;
import com.facerec.tasol.androiarchitecturecomponent.model_services.model.StudentModel;

import java.util.List;

/**
 * Created by tasol on 5/9/18.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private List<StudentModel> mStudentList;
    private Context mContext;

    public StudentAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {
        CheckBox mChbPresent;
        TextView mTvStudentName, mTvStudentAge;

        public StudentViewHolder(View itemView) {
            super(itemView);
            mChbPresent = (CheckBox) itemView.findViewById(R.id.chb_present);
            mTvStudentName = (TextView) itemView.findViewById(R.id.tv_student_name);
            mTvStudentAge = (TextView) itemView.findViewById(R.id.tv_student_age);
        }
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.view_item_student_adapter, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        final StudentModel studentModel = mStudentList.get(position);
        holder.mTvStudentName.setText(studentModel.getStudentName().toString());
        holder.mTvStudentAge.setText(String.valueOf(studentModel.getAge()));
        holder.mChbPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(mContext," "+studentModel.getStudentName()+" is Present ",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext," "+studentModel.getStudentName()+" is Absent ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // getItemCount() is called many times, and when it is first called,
    // mStudentList has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mStudentList != null)
            return mStudentList.size();
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setData(List<StudentModel> data) {
        mStudentList = data;
        notifyDataSetChanged();
    }
}
