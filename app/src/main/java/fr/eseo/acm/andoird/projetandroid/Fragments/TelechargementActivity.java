package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class TelechargementActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telechargement);

        Button buttonDownloadNotes = (Button) findViewById(R.id.buttonDownloadNotes);
        buttonDownloadNotes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TelechargementActivity.this.downloadNotes();
            }
        });

        Button buttonDownloadCom = (Button) findViewById(R.id.buttonDownloadCom);
        buttonDownloadCom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TelechargementActivity.this.downloadCom();
            }
        });
    }

    public void downloadNotes() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String notes = sharedPref.getString("notes", "");

        if (notes != "") {
            if (notes.charAt(0) == ('-')) {
                notes = notes.substring(1);
            }
        }

        File dir = new File("//sdcard//Download//");
        File file = new File(dir, notes);
        DownloadManager downloadManager = (DownloadManager) this.getApplicationContext().getSystemService(DOWNLOAD_SERVICE);
        downloadManager.addCompletedDownload(file.getName(), file.getName(), true, "text/plain",file.getAbsolutePath(),file.length(),true);
        Log.i(TAG, notes);
    }

    public void downloadCom() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String commentaires = sharedPref.getString("commentaires", "");

        if (commentaires != "") {
            if (commentaires.charAt(0) == ('-')) {
                commentaires = commentaires.substring(1);
            }
        }

        File dir = new File("//sdcard//Download//");
        File file = new File(dir, commentaires);
        DownloadManager downloadManager = (DownloadManager) this.getApplicationContext().getSystemService(DOWNLOAD_SERVICE);
        downloadManager.addCompletedDownload(file.getName(), file.getName(), true, "text/plain",file.getAbsolutePath(),file.length(),true);
        Log.i(TAG, commentaires);

        Log.i(TAG, commentaires);

    }
}
