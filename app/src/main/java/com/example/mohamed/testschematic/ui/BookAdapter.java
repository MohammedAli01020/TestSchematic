package com.example.mohamed.testschematic.ui;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohamed.testschematic.R;
import com.example.mohamed.testschematic.data.LibraryContract;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private Cursor mCursor;

  
	
    void setmCursor(Cursor mCursor) {
        this.mCursor = mCursor;
        notifyDataSetChanged();
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) return;

        String message = mCursor.getString(mCursor.getColumnIndexOrThrow(LibraryContract.BookEntry.COLUMN_MESSAGE));
        int id = mCursor.getInt(mCursor.getColumnIndexOrThrow(LibraryContract.BookEntry._ID));

        holder.message.setText(message);
        holder.id.setText(String.valueOf(id));
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        } else {
            return mCursor.getCount();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView message;
        TextView id;

        ViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.tv_message);
            id = itemView.findViewById(R.id.tv_id);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickedListener.onClick(getAdapterPosition());
        }
    }

    private OnItemClickedListener onItemClickedListener;
	
    BookAdapter (OnItemClickedListener listener) {
        onItemClickedListener = listener;
    }

    public interface OnItemClickedListener {
        void onClick(int position);
    }
}
