
package fr.eseo.acm.andoird.projetandroid.Navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import fr.eseo.acm.andoird.projetandroid.R;
import  fr.eseo.acm.andoird.projetandroid.API.API;
import fr.eseo.acm.andoird.projetandroid.servicesAPI.LOGONService;

public class Connexion extends API {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        Button button = (Button) findViewById(R.id.connexion);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Connexion.this.login(v);
                } catch (IOException | KeyStoreException | KeyManagementException | NoSuchAlgorithmException | CertificateException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void login(View v) throws IOException, KeyStoreException, KeyManagementException, NoSuchAlgorithmException, CertificateException {
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        URL url = this.buildApiUrl(API.API_LOGON, username.getText().toString(), password.getText().toString());
        System.out.println(url.toString());
        //System.out.println("answer: "+ this.getReplyFromHttpUrl(url) + "fin");

        //Récupérer le token
        LOGONService log = new LOGONService();
        String token = log.getToken(this.getReplyFromHttpUrl(url));

        //On sauvegarde la valeur du token
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_token), token);
        editor.commit();

        //Ajouter ici le menu
        if(token != null){
            Intent intent = new Intent(this, ChoixMenus.class);
            startActivity(intent);
        }
    }
}
