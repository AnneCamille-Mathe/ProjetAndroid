package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.HashMap;

import fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.R;

public class JuryNotesActivity extends API {

    private String position;

    private String[] project;

    private HashMap<Integer, String> students_list = new HashMap<>();

    private float note;

    private String student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        if(getIntent().hasExtra("idProject")) {
            position = getIntent().getStringExtra("position");
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String projects = sharedPref.getString("projectsFromJury", "Projets non trouvés !");
        String[] splitted_projects = projects.split("\\}]\\}, ");
        project = splitted_projects[Integer.parseInt(position)].split("userId");
        students_list.put(-1, "Tous");
        for(int i=1; i<project.length; i++) {
            String[] data = project[i].split("\"");
            int idStudent = Integer.parseInt(project[i].split(",\"")[0].substring(2));
            students_list.put(idStudent, data[8]+" "+data[4]);
        }

        Button button = findViewById(R.id.validerNote);
        final TextView givenNote = findViewById(R.id.givenNote);
        final Spinner students = findViewById(R.id.spinnerStudents);
        students.setVisibility(View.VISIBLE);

        String[] items = students_list.values().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_students_list, items);
        students.setAdapter(adapter);

        final String noteProjet = sharedPref.getString("noteProjet"+getIntent().getIntExtra("idProject", 0),
                                                "Pas de note attribuée");

        if(!noteProjet.equals("Pas de note attribuée")){
            givenNote.setText("Note attribuée à ce projet : "+noteProjet);
        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText noteEdit = findViewById(R.id.noteVisitor);
                note = Float.parseFloat(noteEdit.getText().toString());
                student = students.getSelectedItem().toString();

                if(note == Float.parseFloat(noteProjet) && student.equals("Tous")) {
                    Toast.makeText(getApplicationContext(), "Vous devez saisir une note différente de celle déjà attribuée !", Toast.LENGTH_LONG).show();
                }
                else {
                    JuryNotesActivity.this.enregistrerNote(v);
                }
            }
        });

        students.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (!selectedItem.equals("Tous")){
                    String noteMoy = getAverageMark(selectedItem);
                    givenNote.setText("Note moyenne de cet étudiant : "+noteMoy);
                }
                else{
                    givenNote.setText("Note attribuée à ce projet : "+noteProjet);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void enregistrerNote(View v) {
        boolean valid = true;

        for(int i=1; i<project.length; i++){
            int idStudent = Integer.parseInt(project[i].split(",\"")[0].substring(2));
            if(student.equals("Tous") || student.equals(students_list.get(idStudent))){
                String answer = this.noter(idStudent);
                if(answer.contains("KO")){
                    valid = false;
                }
            }
        }
        if(!valid){
            Toast.makeText(getApplicationContext(), "Un problème est survenu lors de l'envoi d'une note au moins !", Toast.LENGTH_LONG).show();
        }
        else if (student.equals("Tous")){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("noteProjet"+getIntent().getIntExtra("idProject", 0), String.valueOf(note));
            editor.commit();
            this.finish();
        }
        else{
            this.finish();
        }
    }

    public String noter(int idStudent){
        EditText noteEdit = findViewById(R.id.noteVisitor);
        note = Float.parseFloat(noteEdit.getText().toString());

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

    public String getAverageMark(String student){
        String forename = student.split(" ")[1];
        String surname = student.split(" ")[0];
        boolean finded = false;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        int id = getIntent().getIntExtra("idProject", 0);
        String[] params = new String[] {API.API_USER, username, API.API_PROJECT_ID, String.valueOf(id),
                                        API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_NOTES, params);
        String reply = this.getReplyFromHttpUrl(url);
        String[] splitted_reply = reply.split("\"");

        int i=0;
        String avgNote = "0";

        while (!finded){
            String prenom = splitted_reply[15+i*14];
            String nom = splitted_reply[19+i*14];
            if (forename.equals(prenom) && surname.equals(nom)){
                finded = true;
                avgNote = splitted_reply[24+i*14].split(": ")[1].split(" ")[0];
            }
            i++;
        }
        return avgNote;
    }
}
