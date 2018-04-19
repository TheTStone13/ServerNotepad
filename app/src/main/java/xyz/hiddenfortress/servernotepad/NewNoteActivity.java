package xyz.hiddenfortress.servernotepad;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.widget.Toolbar;

import java.util.Date;

public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener{

    private FileReadWrite reWr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.newNoteToolBar);
        setSupportActionBar(toolbar);
        Button save = (Button)findViewById(R.id.saveButton);
        save.setOnClickListener(NewNoteActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_note_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.new_delete_note:
                Toast.makeText(this, "Return", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NewNoteActivity.this, NotepadMainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.saveButton:
                saveNote();
        }
    }



    protected void saveNote()
    {
        TextView titleView = (TextView)findViewById(R.id.titleBox);
        TextView tagsView = (TextView)findViewById(R.id.tagsBox);
        TextView contentView = (TextView)findViewById(R.id.contentBox);
        String title = titleView.getText().toString();
        String tags = tagsView.getText().toString();
        String content = contentView.getText().toString();
        String dividers = "/~/";
        Date now = Calendar.getInstance().getTime();

        String sData = "/?/" + title + dividers + now.toString() + dividers + now.toString() + dividers + tags + dividers + content + "/!/";
        byte[] data = sData.getBytes();

        try {
            reWr = new FileReadWrite();
            reWr.appendFile(this, sData, "saveNotes.txt");
            Toast.makeText(this, "File saved!!!", Toast.LENGTH_SHORT).show();
        }catch(Exception e) {
            Toast.makeText(this, "File not added!!!", Toast.LENGTH_SHORT).show();
        }


        //Toast.makeText(this, "File saved!!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(NewNoteActivity.this, NotepadMainActivity.class));

    }
}

