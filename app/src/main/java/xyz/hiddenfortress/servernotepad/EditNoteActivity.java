package xyz.hiddenfortress.servernotepad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class EditNoteActivity  extends AppCompatActivity implements View.OnClickListener {

    private TextView titleView;
    private TextView tagsView;
    private TextView contentView;
    private NoteModel edit_model;
    private FileReadWrite reWr;
    private String mfilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.edit_note_tool_bar);
        setSupportActionBar(toolbar);
        reWr = new FileReadWrite();
        Button save = (Button) findViewById(R.id.edit_save_button);
        save.setOnClickListener(EditNoteActivity.this);
        titleView = (TextView) findViewById(R.id.edit_title_box);
        tagsView = (TextView) findViewById(R.id.edit_tags_box);
        contentView = (TextView) findViewById(R.id.edit_content_box);
        initialize();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_note_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.edit_return_main:
                Toast.makeText(this, "Return", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditNoteActivity.this, NotepadMainActivity.class));
                break;

            case R.id.edit_delete_note://TODO delete existing notes
                deleteNote();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_save_button:
                saveNote(false);
        }
    }

    protected void initialize() {
        try {
            mfilename = getIntent().getStringExtra("note_model_filename");
            edit_model = new NoteModel(mfilename);
            if (edit_model.getTitle().length() > 0) {
                titleView.setText(edit_model.getTitle().toCharArray(), 0, edit_model.getTitle().toCharArray().length);
            }
            if (edit_model.getTags().length() > 0) {
                tagsView.setText(edit_model.getTags().toCharArray(), 0, edit_model.getTags().toCharArray().length);
            }
            if (edit_model.getContent().length() > 0) {
                contentView.setText(edit_model.getContent().toCharArray(), 0, edit_model.getContent().toCharArray().length);
            }
            ////read file and remove filename
            /*
            int p = 0;
            while (p < allData.length()) {
                if (allData.substring(p, filename.length()).compareTo(filename) == 0) {
                    String front = allData.substring(0, p);
                    String back = allData.substring(p + filename.length());
                    allData = front + back;
                    //reWr.replaceFile(this, allData, "saveNotes.txt");
                    //Toast.makeText(EditNoteActivity.this, "allData front back = " + allData, Toast.LENGTH_LONG).show();
                    break;
                }
            }*/

        } catch (Exception e) {
            Toast.makeText(this, "failed to initialize", Toast.LENGTH_SHORT).show();
        }
    }

    protected void saveNote(boolean delete)//TODO remove old data, insert new
    {
        String title = titleView.getText().toString();
        String tags = tagsView.getText().toString();
        String content = contentView.getText().toString();
        String dividers = "/~/";
        Date now = Calendar.getInstance().getTime();

        String sData = "/?/" + title + dividers + now.toString() + dividers + edit_model.getCREATEdate() + dividers + tags + dividers + content + "/!/";
        //byte[] data = sData.getBytes();
        try {
             String data = reWr.readFile(this, "saveNotes.txt");
             data = data.replaceAll(mfilename, " ");
            Toast.makeText(this, "data = " + data, Toast.LENGTH_LONG).show();
             reWr.replaceFile(this, data + sData, "saveNotes.txt");
             Toast.makeText(this, "File saved!!!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "File not saved!!!", Toast.LENGTH_SHORT).show();
        }


        //Toast.makeText(this, "File saved!!!", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(EditNoteActivity.this, NotepadMainActivity.class));

    }

    public void deleteNote()
    {
        try{
            String data = reWr.readFile(this, "saveNotes.txt");
            data = data.replaceAll(mfilename, " ");
            reWr.replaceFile(this, data, "saveNotes.txt");
            startActivity(new Intent(EditNoteActivity.this, NotepadMainActivity.class));

        }catch (Exception e)
        {

        }
    }

}
