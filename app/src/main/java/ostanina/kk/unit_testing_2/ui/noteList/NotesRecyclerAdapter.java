package ostanina.kk.unit_testing_2.ui.noteList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.NonNull;
import ostanina.kk.unit_testing_2.R;
import ostanina.kk.unit_testing_2.Util.DateUtil;
import ostanina.kk.unit_testing_2.models.Note;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "NotesRecyclerAdapter";

    private List<Note> notes = new ArrayList<>();
    private OnNoteListener onNoteListener;

    public NotesRecyclerAdapter(OnNoteListener onNoteListener) {
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);
        return new ViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try{
            String month = notes.get(position).getTimestamp().substring(0, 2);
            month = DateUtil.getMonthFromNumber(month);
            String year = notes.get(position).getTimestamp().substring(3);
            String timestamp = month + " " + year;
            ((ViewHolder)holder).timestamp.setText(timestamp);
            ((ViewHolder)holder).title.setText(notes.get(position).getTitle());
        }catch (NullPointerException e){
            Log.e(TAG, "onBindViewHolder: Null Pointer: " + e.getMessage() );
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getNote(int position){
        if(notes.size() > 0){
            return notes.get(position);
        }
        return null;
    }

    public void removeNote(Note note){
        notes.remove(note);
        notifyDataSetChanged();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView timestamp, title;
        OnNoteListener mOnNoteListener;

        public ViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.note_timestamp);
            title = itemView.findViewById(R.id.note_title);
            mOnNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnNoteListener.onNoteClick(getNote(getAdapterPosition()));
        }
    }

    public interface OnNoteListener{
        void onNoteClick(Note note);
    }
}
