package xyz.hiddenfortress.servernotepad;

import java.util.ArrayList;

public class NoteModel {
    String filename;
    String title;
    String UPdate;
    String CREATEdate;
    ArrayList<String> tagsList;
    String sTags;
    String content;


    public NoteModel(String[] note)
    {
        try {
            title = note[0];
            UPdate = note[1];
            CREATEdate = note[2];
            sTags = note[3];
            //setTags(note[3]);
            content = note[4];
            String dividers = "/~/";
            filename = "/?/" + title + dividers + UPdate.toString() + dividers + CREATEdate.toString() + dividers + sTags + dividers + content + "/!/";
        } catch(Exception E)
        {
            System.out.println("NoteModel String[].length too small: " + note.length + " should be 6");
        }
    }

    public NoteModel(String fileline)
    {
        filename = fileline;
        int index = 0; // index of noteData
        int pos = 3; //position of index for main loop
        NoteModel temp;
        int front = 0;//front of current subString
        int back = 0;//back of current subString
        String[] noteData = new String[5];//note Data
        while (pos < fileline.length() + 1) {

            String s = fileline.substring(pos - 3, pos);
            //Toast.makeText(this, "s=  " + s, Toast.LENGTH_SHORT).show();

            if (s.compareTo("/?/") == 0)//start note
            {
                //Toast.makeText(this, "s = /?/", Toast.LENGTH_SHORT).show();
                front = pos;
                pos++;
            } else if (s.compareTo("/!/") == 0)// end notes
            {
                //Toast.makeText(this, "s = /!/", Toast.LENGTH_SHORT).show();
                back = pos - 3;
                noteData[index] = fileline.substring(front, back);
                //temp = new NoteModel(noteData);
                //notes.add(temp);
                break;
                //noteData = new String[5];

            } else if (s.compareTo("/~/") == 0)//divs
            {
                //Toast.makeText(this, "s = /~/", Toast.LENGTH_SHORT).show();
                back = pos - 3;
                noteData[index] = fileline.substring(front, back);
                index++;
                front = pos;
                pos++;

            } else //normal interate
            {
                //Toast.makeText(this, "iterate", Toast.LENGTH_SHORT).show();
                pos++;
            }

        }

        title = noteData[0];
        UPdate = noteData[1];
        CREATEdate = noteData[2];
        sTags = noteData[3];
        //setTags(noteData[3]);
        content = noteData[4];

    }

    public String toString()
    {
        return title + " " + UPdate  + " " + CREATEdate + " " + sTags + " " + content;
    }

    public void setTags(String tags)
    {
        String tag = "";
        for(int p = 0; p < tags.length(); p++)
        {
            char c = tags.charAt(p);
            if(c == ';')
            {
                tagsList.add(tag);
                tag = "";
            }
            else
            {
                tag += c;
            }
        }
    }


    public boolean removeTag(String remove)
    {

        for(int y = 0; y < tagsList.size(); y++)
        {
            String s = tagsList.get(y);
            if(s.compareTo(remove) == 0)
            {
                tagsList.remove(y);
                return true;
            }
        }
        return false;
    }


    public String getFilename()
    {
        return filename;
    }
    public String getTitle()
    {
        return title;
    }
    public String getUPdate()
    {
        return UPdate;
    }
    public String getCREATEdate()
    {
        return CREATEdate;
    }
    public String getTags()
    {
        return sTags;
        /*
        String s = "";
        for(String x: tagsList)
        {
            s += x + ";";
        }
        if(tagsList.size() > 0)
        {
            s = s.substring(0, s.length());
        }
        return s;*/
    }
    public String getContent()
    {
        return content;
    }
}
