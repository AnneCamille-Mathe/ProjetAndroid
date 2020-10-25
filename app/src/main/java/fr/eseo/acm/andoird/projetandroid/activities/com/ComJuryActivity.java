package fr.eseo.acm.andoird.projetandroid.activities.com;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import fr.eseo.acm.andoird.projetandroid.api.API;
import fr.eseo.acm.andoird.projetandroid.navigation.ChoixNbProjects;
import fr.eseo.acm.andoird.projetandroid.activities.RandomProjectsActivity;
import fr.eseo.acm.andoird.projetandroid.R;

public class ComJuryActivity extends API {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_jury_activity);
        Button buttonAleatoire = (Button) findViewById(R.id.buttonAleatoire);
        buttonAleatoire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    ComJuryActivity.this.genereRandom();
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
            }
        });
        Button buttonManuel = (Button) findViewById(R.id.buttonManuel);
        buttonManuel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ComJuryActivity.this.genereChoix();
            }
        });
    }

    public void genereRandom() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        Intent intent = new Intent(this, RandomProjectsActivity.class);
        String randomProject = this.getRandomProject();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("randomProjects", randomProject);
        editor.putString("notes", "");
        editor.putString("commentaires", "");
        editor.commit();
        startActivity(intent);
    }

    public String getRandomProject() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        String[] params = new String[]{API.API_USER, username, API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_PORTE, params);
        return this.getReplyFromHttpUrl(url);
    }

    public void genereChoix() {
        Intent intent = new Intent(this, ChoixNbProjects.class);
        startActivity(intent);

    }



}
