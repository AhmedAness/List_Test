package com.example.dt.List_Test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dt.List_Test.Model.Course;
import com.example.dt.List_Test.R;

public class CourseAdapter extends ListAdapter<Course, CourseAdapter.CourseHolder> {

    public CourseAdapter() {
        super(diffCallback);
    }

    private static final DiffUtil.ItemCallback<Course> diffCallback=new DiffUtil.ItemCallback<Course>() {
        @Override
        public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
            return oldItem.getName().equals(newItem.getName())&&oldItem.getDesc().equals(newItem.getDesc())&&oldItem.getLink().equals( newItem.getLink());
        }
    };

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new CourseHolder(itemview);
    }

    public Course GetCourseAt(int position) {
        return getItem(position);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        Course course = getItem(position);
        holder.Name.setText(course.getName());
        holder.Description.setText(course.getDesc());
        holder.Link.setText(String.valueOf(course.getLink()));
    }

    protected class CourseHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Description;
        TextView Link;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.FullName);
            Description = itemView.findViewById(R.id.Desc_item);
            Link = itemView.findViewById(R.id.Link_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Course Name :"+ Name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
