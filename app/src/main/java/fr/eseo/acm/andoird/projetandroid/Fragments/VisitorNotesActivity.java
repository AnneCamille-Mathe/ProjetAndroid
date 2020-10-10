package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import fr.eseo.acm.andoird.projetandroid.R;

public class VisitorNotesActivity  extends AppCompatActivity {
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_visitor);

        Button button = (Button) findViewById(R.id.validerNote);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                VisitorNotesActivity.this.enregistrerNote(v);
            }
        });

        if(getIntent().hasExtra("position")) {
            position = (getIntent().getStringExtra("position"));
        }
    }

    public void enregistrerNote(View v){
        EditText noteEdit = (EditText)findViewById(R.id.noteVisitor);
        int note = Integer.parseInt(noteEdit.toString());
        System.out.println("NOTE " + note);
        System.out.println("POSITION " + position);
    }
}
