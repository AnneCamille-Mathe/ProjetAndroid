package fr.eseo.acm.andoird.projetandroid.activities.visitor;

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

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.api.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.entites.Project;

public class VisitorNotesActivity  extends AppCompatActivity {
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

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
        if(projet.contains("*")){
            String projetSansEtoile = projet.substring(1);
            String projetSansAccoladeG = projetSansEtoile.replace("[", "");
            String projetSansAccoladeD = projetSansAccoladeG.replace("]", "");
            String projetSansVirgule = projetSansAccoladeD.replace(",", "");
            String[] idProjetsList = projetSansVirgule.split(" ");

            ArrayList<Integer> idProjets = new ArrayList<>();
            for (int i = 0; i < idProjetsList.length; i++) {
                if (isNumeric(idProjetsList[i])) {
                    idProjets.add(Integer.parseInt(idProjetsList[i]));
                }
            }

            String notesEnregristres = sharedPref.getString("notes", "");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("notes", notesEnregristres + "-" + note + "/" + idProjets.get(Integer.parseInt(position)));
            editor.commit();
            Intent intent = new Intent(this, VisitorActivity.class);
            startActivity(intent);
        }
        else {
            List<Project> mProjectList = UserUtils.parseForProjectsForPorte(new JSONObject(projet));
            String notesEnregristres = sharedPref.getString("notes", "");
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("notes", notesEnregristres + "-" + note + "/" + mProjectList.get(Integer.parseInt(position)).getIdProject());
            editor.commit();
            Intent intent = new Intent(this, VisitorActivity.class);
            startActivity(intent);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
