
package fr.eseo.acm.andoird.projetandroid.navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import fr.eseo.acm.andoird.projetandroid.R;
import  fr.eseo.acm.andoird.projetandroid.api.API;
import fr.eseo.acm.andoird.projetandroid.api.LogonService;

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
        Button visitor = (Button) findViewById(R.id.visitor);
        visitor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Connexion.this.loginAsVisitor();
            }
        });
    }
    public void login(View v) throws IOException, KeyStoreException, KeyManagementException, NoSuchAlgorithmException, CertificateException {
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        String[] params = new String[] {API.API_USER, username.getText().toString(), API.API_PASS, password.getText().toString()};
        URL url = this.buildRequest(API.API_LOGON, params);

        //Récupérer le token
        LogonService log = new LogonService();
        String token = log.getToken(this.getReplyFromHttpUrl(url));

        //On sauvegarde la valeur du token
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("saved_token", token);
        editor.putString("saved_username", username.getText().toString());
        editor.commit();

        if(token != null){
            int role = this.getRole(username.getText().toString());
            Intent intent = new Intent(this, ChoixMenus.class);
            intent.putExtra("role", role);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Vos identifiants sont invalides, veuillez réessayer !", Toast.LENGTH_LONG).show();
        }
    }

    public void loginAsVisitor(){
        Intent intent = new Intent(this, ChoixMenus.class);
        intent.putExtra("role", 0);
        startActivity(intent);
    }

    public int getRole(String username) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        String[] params = new String[] {API.API_USER, username};
        URL url = this.buildRequest(API.API_MYINF, params);
        String reply = this.getReplyFromHttpUrl(url);

        String role = "";
        String[] details = reply.split("\"idRole\": ");
        String[] detail = details[1].split(",");
        role = detail[0];
        return Integer.parseInt(role);
    }
}
