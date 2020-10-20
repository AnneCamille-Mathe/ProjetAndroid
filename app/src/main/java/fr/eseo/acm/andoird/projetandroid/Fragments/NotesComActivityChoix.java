package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class NotesComActivityChoix extends AppCompatActivity {
    List<Project> mProjectList = null;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notes_com);
        String position = "";

        if (getIntent().hasExtra("position")) {
            position = (getIntent().getStringExtra("position"));
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String notes = sharedPref.getString("notes", "");
        String commentaires = sharedPref.getString("commentaires", "");

        if (notes != "") {
            if (notes.charAt(0) == ('-')) {
                notes = notes.substring(1);
            }
        }

        if (commentaires != "") {
            if (commentaires.charAt(0) == ('-')) {
                commentaires = commentaires.substring(1);
            }
        }


        //Récuperer l'ID du projet a partir de la position
        String projetsRandom = sharedPref.getString("randomProjects", "");

        String projetSansEtoile = projetsRandom.substring(1);
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

        int idProject = idProjets.get(Integer.parseInt(position));

        //decouper les notes
        List<String> notesAAfficher = new ArrayList<String>();
        String[] listeNotes = notes.split("-");

        if (notes != "") {
            for (int i = 0; i < listeNotes.length; i++) {
                String[] notesEtId = listeNotes[i].split("/");
                if (Integer.parseInt(notesEtId[1]) == idProject) {
                    notesAAfficher.add(notesEtId[0]);
                }
            }
        }
        List<String> commentairesAAfficher = new ArrayList<String>();
        String[] listeCommentaires = commentaires.split("-");

        if (commentaires != "") {
            for (int i = 0; i < listeCommentaires.length; i++) {
                String[] commentaireEtId = listeCommentaires[i].split("/");
                if (Integer.parseInt(commentaireEtId[1]) == idProject) {
                    commentairesAAfficher.add(commentaireEtId[0]);
                }
            }
        }

        //afficher les notes
        String afficheNote = "";
        for (int i = 0; i < notesAAfficher.size(); i++) {
            afficheNote = afficheNote + " / " + notesAAfficher.get(i);
        }
        TextView textNotes = findViewById(R.id.textNotes);

        //TODO - If not null supprimer les 2 premiers caractere (substring(1).substring(1))
        textNotes.setText(afficheNote);


        String afficheCom = "";
        for (int i = 0; i < commentairesAAfficher.size(); i++) {
            afficheCom = afficheCom + " / " + commentairesAAfficher.get(i);
        }

        TextView textCommentaire = findViewById(R.id.textCommentaire);

        //TODO - If not null supprimer les 2 premiers caractere (substring(1).substring(1))

        textCommentaire.setText(afficheCom);

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
