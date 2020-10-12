package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class NotesComActivity extends AppCompatActivity {
    List<Project> mProjectList = null;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notes_com);
        String position = "";

        if(getIntent().hasExtra("position")) {
            position = (getIntent().getStringExtra("position"));
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String notes = sharedPref.getString("notes", "");

        if(notes.charAt(0)==('-')){
            notes = notes.substring(1);
        }

        //Récuperer l'ID du projet a partir de la position

        String projetsRandom = sharedPref.getString("randomProjects", "");
        try {
            mProjectList = UserUtils.parseForProjectsForPorte(new JSONObject(projetsRandom));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int idProject = mProjectList.get(Integer.parseInt(position)).getIdProject();
        Log.i(TAG, "ID de la classe = " + idProject);

        //decouper les notes
        List<String> notesAAfficher = new ArrayList<String>();
        String[] listeNotes = notes.split("-");

        for(int i = 0; i< listeNotes.length; i++){
            String[] notesEtId = listeNotes[i].split("/");
            if (Integer.parseInt(notesEtId[1]) == idProject) {
                notesAAfficher.add(notesEtId[0]);
            }
        }

        //afficher les notes
        String afficheNote = "";
        for(int i = 0; i< notesAAfficher.size(); i++){
            afficheNote = afficheNote + " / " + notesAAfficher.get(i);
        }
        TextView textNotes = findViewById(R.id.textNotes);
        textNotes.setText(afficheNote.substring(1).substring(1));

        /*
        TextView textCommentaire = findViewById(R.id.textCommentaire);
        textCommentaire.setText(titre);
        */

    }
}
