package xyz.hiddenfortress.servernotepad;


import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    static ArrayList<NoteModel> modelArr = new ArrayList<>();

    RecyclerAdapter(ArrayList<NoteModel> modelArr)
    {
        this.modelArr = modelArr;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleText.setText(modelArr.get(position).getTitle());
        holder.tagsText.setText(modelArr.get(position).getTags());
        holder.updateText.setText(modelArr.get(position).getUPdate());
        holder.createdateText.setText(modelArr.get(position).getCREATEdate());
        holder.contentText.setText(modelArr.get(position).getFilename());
    }

    @Override
    public int getItemCount(){
        return modelArr.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView titleText;
        TextView tagsText;
        TextView updateText;
        TextView createdateText;
        TextView contentText;

        public MyViewHolder(View item){
            super(item);
            titleText = (TextView)item.findViewById(R.id.title_item);
            tagsText = (TextView)item.findViewById(R.id.tags_item);
            updateText = (TextView)item.findViewById(R.id.update_item);
            createdateText = (TextView)item.findViewById(R.id.createdate_item);
            contentText= (TextView) item.findViewById(R.id.content_item);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v)
        {
            Context context = v.getContext();
            int i = getAdapterPosition();
            Intent edit = new Intent(context, EditNoteActivity.class);
            edit.putExtra("note_model_filename", contentText.getText());
            context.startActivity(edit);
            //Toast.makeText(NotepadMainActivity.this, notesModels.get(0).getTitle(), Toast.LENGTH_SHORT).show()
            /*
            edit.putExtra("note_model_content", contentText.getText());

            edit.putExtra("note_model_title", titleText.getText());

            edit.putExtra("note_model_tags", tagsText.getText());

            edit.putExtra("note_model_update", updateText.getText());

            edit.putExtra("note_model_createDate", createdateText.getText());
            //Toast.makeText(NotepadMainActivity.this, edit.getStringExtra("note_model_filename"), Toast.LENGTH_SHORT).show();
           */
        }

    }




}
