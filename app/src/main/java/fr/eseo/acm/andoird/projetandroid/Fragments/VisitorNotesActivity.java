package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.Navigation.VisitorActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class VisitorNotesActivity  extends AppCompatActivity {
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_visitor);

        Button button = (Button) findViewById(R.id.validerNote);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    VisitorNotesActivity.this.enregistrerNote(v);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        if(getIntent().hasExtra("position")) {
            position = (getIntent().getStringExtra("position"));
        }
    }

    public void enregistrerNote(View v) throws JSONException {
        EditText noteEdit = (EditText)findViewById(R.id.noteVisitor);
        int note = Integer.parseInt(noteEdit.getText().toString());
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String projet = sharedPref.getString("randomProjects", "projets non trouvés !");
        List<Project> mProjectList = UserUtils.parseForProjectsForPorte(new JSONObject(projet));
        String notesEnregristres = sharedPref.getString("notes", "");
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("notes", notesEnregristres + "-" + note + "/" + mProjectList.get(Integer.parseInt(position)).getIdProject());
        editor.commit();
        Intent intent = new Intent(this, VisitorActivity.class);
        startActivity(intent);
    }
}
