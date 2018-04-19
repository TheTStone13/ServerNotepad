package xyz.hiddenfortress.servernotepad;

import android.content.Context;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileReadWrite {

    public FileReadWrite()
    {

    }

    private FileOutputStream outputStream;
    private FileInputStream inputStream;

    public void writeFile(Context context, ArrayList<String> data, String fileName)
    {
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            for (String s : data)
            {
                outputStream.write(s.getBytes());
            }
            outputStream.close();
        }catch(FileNotFoundException e) {
            Toast.makeText(context, "FileNotFound", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION:writefilearraylist");
        }catch(IOException e)
        {
            Toast.makeText(context, "FAILED TO WRITE FILE", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            System.out.println("IOEXCEPTION:writefilearraylist");
        }
    }

    public void writeFileInitial(Context context, ArrayList<String> data, String fileName)
    {

        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            for (String s : data)
            {
                outputStream.write(s.getBytes());
            }
            outputStream.close();
        }catch(FileNotFoundException e) {
            Toast.makeText(context, "FileNotFound", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION:writefilearraylist");
        }catch(IOException e)
        {
            Toast.makeText(context, "FAILED TO WRITE FILE", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            System.out.println("IOEXCEPTION:writefilearraylist");
        }
    }

    public void replaceFile(Context context, String data, String fileName)
    {
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);

            outputStream.write(data.getBytes());

            outputStream.close();
        }catch(FileNotFoundException e) {
            Toast.makeText(context, "FileNotFound", Toast.LENGTH_SHORT).show();
        }catch(IOException e)
        {
            Toast.makeText(context, "FAILED TO WRITE FILE", Toast.LENGTH_SHORT).show();
        }
    }

    public void appendFile(Context context, String data, String fileName)
    {
        //File f = new File(fileName);
        // if(f.exists)
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
            outputStream.write(data.getBytes());
            outputStream.close();
        }catch(FileNotFoundException e) {
            Toast.makeText(context, "FileNotFound", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION:writefilearraylist");
        }catch(IOException e)
        {
            Toast.makeText(context, "FAILED TO WRITE FILE", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            System.out.println("IOEXCEPTION:writefilearraylist");
        }
    }
    public String readFile(Context context, String filename)
    {
        String data = "";
        int c;
        try {
            inputStream = context.openFileInput(filename);
            while((c = inputStream.read()) != -1)
            {
                data += Character.toString((char)c);
            }
            inputStream.close();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION:realFile");
        }catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("IO EXCEPTION:readFile");
        }
        return data;
    }
}
