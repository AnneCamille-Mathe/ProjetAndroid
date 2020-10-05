package fr.eseo.acm.andoird.projetandroid.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        //R.menu.menu est l'id de notre menu
        inflater.inflate(R.menu.menu_com, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                try {
                    openActivityProject();
                } catch (CertificateException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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