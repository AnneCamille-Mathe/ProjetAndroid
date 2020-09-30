package fr.eseo.acm.andoird.projetandroid.Navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import fr.eseo.acm.andoird.projetandroid.R;
import  fr.eseo.acm.andoird.projetandroid.API.API;

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
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_visitor);
            }
        });
    }


    public void login(View v) throws IOException, KeyStoreException, KeyManagementException, NoSuchAlgorithmException, CertificateException {
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        URL url = this.buildApiUrl(API.API_LOGON, username.getText().toString(), password.getText().toString());
        System.out.println(url.toString());
        System.out.println("answer: "+ this.getReplyFromHttpUrl(url));
        //Ajouter ici le menu_com
    }
}