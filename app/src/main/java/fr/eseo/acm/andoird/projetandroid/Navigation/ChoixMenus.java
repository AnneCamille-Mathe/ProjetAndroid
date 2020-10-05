package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import fr.eseo.acm.andoird.projetandroid.R;
import androidx.appcompat.app.AppCompatActivity;

public class ChoixMenus extends AppCompatActivity {

    private int role;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        role = intent.getIntExtra("role", 0);
        System.out.println("ROLE "+role);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        if(role == 1){
            //R.menu.menu est l'id de notre menu
            inflater.inflate(R.menu.menu_jury, menu);
        } else if (role == 4) {
            //R.menu.menu est l'id de notre menu
            inflater.inflate(R.menu.menu_com, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(role == 4){
            switch (item.getItemId()) {
                case R.id.projects:
                    openActivityProject();
                    break;
                case R.id.jury:
                    openActivityJury();
                    break;
                case R.id.marks:
                    openActivityNotesCom();
                    break;

            }
        } else if (role == 1) {
            switch (item.getItemId()) {
                case R.id.projects:
                    openActivityProject();
                    break;
                case R.id.posters:
                    openActivityJuryPosters();
                    break;
                case R.id.my_jury:
                    openActivityJuryMine();
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void openActivityProject() {
        Intent intent = new Intent(this, AllProjectsActivity.class);
        startActivity(intent);
    }

    public void openActivityJury() {
        Intent intent = new Intent(this, ComJuryActivity.class);
        startActivity(intent);
    }

    public void openActivityNotesCom() {
        Intent intent = new Intent(this, ComNotesActivity.class);
        startActivity(intent);
    }

    public void openActivityJuryMine() {
        Intent intent = new Intent(this, JuryMineActivity.class);
        startActivity(intent);
    }

    public void openActivityJuryPosters() {
        Intent intent = new Intent(this, JuryPostersActivity.class);
        startActivity(intent);
    }

}