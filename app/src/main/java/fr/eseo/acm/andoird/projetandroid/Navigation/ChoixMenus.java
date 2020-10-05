package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.Fragments.ListProjectsFragment;
import fr.eseo.acm.andoird.projetandroid.R;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class ChoixMenus extends API {

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
                    try {
                        openActivityProject();
                    } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
                        e.printStackTrace();
                    }
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
                    try {
                        openActivityProject();
                    } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
                        e.printStackTrace();
                    }
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

    public void openActivityProject() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        //TEST
        //Intent intent = new Intent(this, AllProjectsActivity.class);
        Intent intent = new Intent(this, ListProjectsFragment.class);
        intent.putExtra("json", this.getProjects());
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

    public String getProjects() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        String[] params = new String[] {API.API_USER, username, API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_PROJECTS, params);
        System.out.println(url.toString());
       return this.getReplyFromHttpUrl(url);
    }

}