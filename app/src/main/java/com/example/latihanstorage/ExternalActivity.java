package com.example.latihanstorage;

import android.annotation.SuppressLint;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ExternalActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namafile.txt";
    Button buatFile, ubahFile, bacaFile, hapusFile;
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        buatFile = findViewById(R.id.buttonBuatFile);
        ubahFile = findViewById(R.id.buttonUbahFile);
        bacaFile = findViewById(R.id.buttonBacaFile);
        hapusFile = findViewById(R.id.buttonHapusFile);
        textBaca = findViewById(R.id.textBaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        hapusFile.setOnClickListener(this);
    }


    public void buatFile(){
        String cashback = "Tulis Isi File";
        File state = new File(Environment.getExternalStorageState());

        if(!Environment.MEDIA_MOUNTED.equals(state)){
            return;
        }

        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "namafile.txt");
        }
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(cashback.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    public void bacaFile() {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, FILENAME);

        if(file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while(line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }
            textBaca.setText(text.toString());
        } else{
            textBaca.setText("File Tidak Ditemukan!");
        }

    }

    private void ubahFile() {
        String sdcard = "(Sudah Diubah/Ditambahkan!)";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            if(file.exists()){
                outputStream = new FileOutputStream(file, true);
                outputStream.write(sdcard.getBytes());
                outputStream.flush();
                outputStream.close();}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void hapusFile(){
        File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
        if(file.exists()){
            file.delete();
            Toast.makeText(this, "File Dihapus", Toast.LENGTH_LONG). show();
        }
    }

    @Override
    public void onClick(View v) {jalankanPerintah(v.getId());}

    public void jalankanPerintah(int id){
        switch (id){
            case R.id.buttonBuatFile:
                buatFile();
                break;

            case R.id.buttonBacaFile:
                bacaFile();
                break;

            case R.id.buttonUbahFile:
                ubahFile();
                break;

            case R.id.buttonHapusFile:
                hapusFile();
                break;

        }
    }



}