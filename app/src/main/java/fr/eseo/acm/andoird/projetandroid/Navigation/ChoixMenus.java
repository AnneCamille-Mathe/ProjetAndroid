package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import fr.eseo.acm.andoird.projetandroid.R;
import androidx.appcompat.app.AppCompatActivity;

public class ChoixMenus extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        //R.menu.menu est l'id de notre menu
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                openActivityProject();
                break;
            case R.id.item2:
                openActivityJury();
                break;
            case R.id.item3:
                openActivityNotesCom();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void openActivityProject() {
        Intent intent = new Intent(this, ProjetActivity.class);
        startActivity(intent);
    }

    public void openActivityJury() {
        Intent intent = new Intent(this, JuryActivity.class);
        startActivity(intent);
    }

    public void openActivityNotesCom() {
        Intent intent = new Intent(this, ComNotesActivity.class);
        startActivity(intent);
    }

}