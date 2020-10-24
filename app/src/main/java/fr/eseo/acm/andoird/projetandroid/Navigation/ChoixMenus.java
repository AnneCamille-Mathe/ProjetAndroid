package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.Fragments.ListJuryFragment;
import fr.eseo.acm.andoird.projetandroid.Fragments.ListProjectsFragment;
import fr.eseo.acm.andoird.projetandroid.Fragments.TelechargementActivity;
import fr.eseo.acm.andoird.projetandroid.R;

import java.net.URL;

public class ChoixMenus extends API {

    private int role;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        role = intent.getIntExtra("role", 0);
        if(role == 1){
            setContentView(R.layout.activity_jury);
        } else if (role == 4) {
            setContentView(R.layout.activity_com);
        } else{
            Intent intentVisitor = new Intent(this, VisitorActivity.class);
            startActivity(intentVisitor);
        }
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
                    openActivityJuryCom();
                    break;
                case R.id.marks:
                    openActivityNotesCom();
                    break;
                case R.id.downloads:
                    openActivityTelechargement();
                    break;
            }
        } else if (role == 1) {
            switch (item.getItemId()) {
                case R.id.projects:
                    openActivityProject();
                    break;
                case R.id.my_jury:
                    openActivityJuryMine();
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void openActivityProject() {
        Intent intent = new Intent(this, ListProjectsFragment.class);
        intent.putExtra("json", this.getProjects());
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("projets", this.getProjects());
        editor.commit();
        startActivity(intent);
    }

    public void openActivityJuryCom() {
        Intent intent = new Intent(this, ComJuryActivity.class);
        startActivity(intent);
    }

    public void openActivityNotesCom() {
        Intent intent = new Intent(this, ComNotesActivity.class);
        startActivity(intent);
    }

    public void openActivityTelechargement(){
        Intent intent = new Intent(this, TelechargementActivity.class);
        startActivity(intent);
    }

    public void openActivityJuryMine() {
        Intent intent = new Intent(this, ListJuryFragment.class);
        intent.putExtra("json", this.getMyJury());
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("jury", this.getMyJury());
        editor.commit();
        startActivity(intent);
    }

    public String getProjects() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        String[] params = new String[] {API.API_USER, username, API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_PROJECTS, params);
        return this.getReplyFromHttpUrl(url);
    }


    public String getMyJury() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        String[] params = new String[] {API.API_USER, username, API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_MYJURY, params);
        return this.getReplyFromHttpUrl(url);
    }
}