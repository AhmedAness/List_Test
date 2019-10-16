package com.example.dt.List_Test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dt.List_Test.Model.Course;
import com.example.dt.List_Test.R;

public class NoteAdapter extends ListAdapter<Course,NoteAdapter.NoteHolder> {

    OnRecyclerItemClick listner;

    public NoteAdapter() {
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
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new NoteHolder(itemview);
    }

    public Course GetNoteAt(int position) {
        return getItem(position);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Course course = getItem(position);
        holder.Title.setText(course.getName());
        holder.Description.setText(course.getDesc());
        holder.Preority.setText(String.valueOf(course.getLink()));
    }

    protected class NoteHolder extends RecyclerView.ViewHolder {
        TextView Title;
        TextView Description;
        TextView Preority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.FullName);
            Description = itemView.findViewById(R.id.Desc_item);
            Preority = itemView.findViewById(R.id.Link_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listner.OnItemClicked(getItem(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnRecyclerItemClick{
        void OnItemClicked(Course course);
    }
    public void SetOnItemClickListner(OnRecyclerItemClick listner){
        this.listner=listner;
    }


}
