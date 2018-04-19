package xyz.hiddenfortress.servernotepad;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class NotepadMainActivity extends AppCompatActivity {


    Toolbar toolbarMain;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<NoteModel> noteModelsARR = new ArrayList<>();
    FileReadWrite reWr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_main);
        toolbarMain = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbarMain);
        reWr = new FileReadWrite();

        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        noteModelsARR = populateList();

        adapter = new RecyclerAdapter(noteModelsARR);
        recyclerView.setAdapter(adapter);


    }

    protected ArrayList<NoteModel> populateList() {
        //Toast.makeText(this, "populate list", Toast.LENGTH_SHORT).show();
        //ArrayList<String> linesArr = new ArrayList<String>();
        String data = "";
        try {
            reWr = new FileReadWrite();
            data = reWr.readFile(this, "saveNotes.txt");
        } catch (Exception e) {
            Toast.makeText(this, "404", Toast.LENGTH_SHORT).show();
        }

        ArrayList<NoteModel> notes = new ArrayList<NoteModel>();
        //Toast.makeText(this, data, Toast.LENGTH_LONG).show();

        //TextView t = (TextView)findViewById(R.id.tempTextView); //used to debug listview
        //char[] c = data.toCharArray();
        //t.setText(c, 0, data.length());

        int index = 0; // index of noteData
        int pos = 3; //position of index for main loop
        NoteModel temp;
        int front = 0;//front of current subString
        int back = 0;//back of current subString
        String[] noteData = new String[5];//note Data
        //Toast.makeText(this, "StartLoop", Toast.LENGTH_SHORT).show();

        while (pos < data.length() + 1) {
            String s = data.substring(pos - 3, pos);
            if (s.compareTo("/?/") == 0) {
                front = pos - 3;
            }
            if (s.compareTo("/!/") == 0) {
                if (data.length() - pos > 10) {
                    String si = data.substring(front, pos);
                    //Toast.makeText(this, si, Toast.LENGTH_SHORT).show();
                    //NoteModel test = new NoteModel(si);
                    notes.add(new NoteModel(si));
                } else {
                    String si = data.substring(front);
                    //Toast.makeText(NotepadMainActivity.this, si, Toast.LENGTH_SHORT).show();
                    notes.add(new NoteModel(si));
                }

            }
            pos++;
        }

        ArrayList<NoteModel> returnNotes = new ArrayList<NoteModel>();
        for(int i = notes.size()-1; i >= 0; i--)
        {
            returnNotes.add(notes.get(i));
        }
        return returnNotes;
    }



    public void uploadNotes() {
        final String s = reWr.readFile(this, "saveNotes.txt");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "174.45.92.69/writeFile.php?f=testDir/NewFiles/notes.txt&d=" + s;
        Toast.makeText(this, url, Toast.LENGTH_LONG).show();
        StringRequest strRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(NotepadMainActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NotepadMainActivity.this, "ERROR UPLOADING NOTES", Toast.LENGTH_SHORT).show();
            }
        });
        //this.deleteFile("saveNotes.txt");
        //queue.add(strRequest);
        //reWr.replaceFile(this, "", "saveNotes.txt");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m)
    {
        getMenuInflater().inflate(R.menu.main_menu_items, m);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu)
    {
        switch (menu.getItemId())
        {
            case R.id.create_new_note:
                startActivity(new Intent(NotepadMainActivity.this, NewNoteActivity.class));
                return true;
            case R.id.upload_notes:
                uploadNotes();
                return true;
            default:
                return true;
        }

    }


}
