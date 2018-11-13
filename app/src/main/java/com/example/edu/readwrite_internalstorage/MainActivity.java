package com.example.edu.readwrite_internalstorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.view.View;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextInput;
    Button buttonRead, buttonWrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        buttonRead = findViewById(R.id.buttonRead);
        buttonRead.setOnClickListener(this);
        buttonWrite = findViewById(R.id.buttonWrite);
        buttonWrite.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText editText = findViewById(R.id.editTextInput);
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        switch (view.getId()){
            case R.id.buttonRead:
                try {
                    fileInputStream = openFileInput("save.txt");
                    byte[] buffer = new byte[fileInputStream.available()];

                    fileInputStream.read(buffer);
                    editText.setText(new String(buffer));

                } catch (FileNotFoundException e) {
                    Log.e("openFileInput", "FileNotFoundException");
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("buffer fail", "FileNotFoundException");
                    e.printStackTrace();
                } finally {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.buttonWrite:
                try {
                    fileOutputStream = openFileOutput("save.txt", Context.MODE_PRIVATE);
                    fileOutputStream.write(editTextInput.getText().toString().getBytes());

                    editTextInput.setText("");

                } catch (FileNotFoundException e) {
                    Log.e("openFileOutput", "FileNotFoundException");
                    e.printStackTrace();

                } catch (IOException e) {
                    Log.e("openFileOutput.write", "failed");
                    e.printStackTrace();
                }finally {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
