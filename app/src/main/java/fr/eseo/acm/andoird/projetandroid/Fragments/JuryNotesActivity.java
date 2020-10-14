package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.net.URL;

import fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.R;

public class JuryNotesActivity extends API {

    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Button button = (Button) findViewById(R.id.validerNote);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                JuryNotesActivity.this.enregistrerNote(v);
            }
        });

        if(getIntent().hasExtra("idProject")) {
            position = getIntent().getStringExtra("position");
        }
    }

    public void enregistrerNote(View v) {
        boolean valid = true;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String projects = sharedPref.getString("projectsFromJury", "Projets non trouvés !");
        String[] splitted_projects = projects.split("\\}]\\}, ");
        String[] splitted_project = splitted_projects[Integer.parseInt(position)].split("userId");
        for(int i=1; i<splitted_project.length; i++){
            int idStudent = Integer.parseInt(splitted_project[i].split(",\"")[0].substring(2));
            String answer = this.noter(idStudent);
            if(answer.contains("KO")){
                valid = false;
            }
        }
        if(valid){
            this.finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "Un problème est survenu lors de l'envoi des notes !", Toast.LENGTH_LONG).show();
        }
    }

    public String noter(int idStudent){
        EditText noteEdit = (EditText)findViewById(R.id.noteVisitor);
        float note = Float.parseFloat(noteEdit.getText().toString());

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        int id = getIntent().getIntExtra("idProject", 0);

        String[] params = new String[] {API.API_USER, username, API.API_PROJECT_ID, String.valueOf(id),
                                        API.API_STUDENT, String.valueOf(idStudent), API.API_NOTE, String.valueOf(note),
                                        API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_NEWNT, params);
        return this.getReplyFromHttpUrl(url);
    }
}
