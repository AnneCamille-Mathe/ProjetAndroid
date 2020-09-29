package fr.eseo.acm.andoird.projetandroid.Navigation;

import androidx.appcompat.app.AppCompatActivity;
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

public class Connexion extends AppCompatActivity {

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

        //DEBUT
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        // From https://www.washington.edu/itconnect/security/ca/load-der.crt
        Log.d("certificate", Integer.toString(R.raw.dis_inter_ca));

        InputStream caInput =
                //this.context.getResources().openRawResource(R.raw.dis_inter_ca);
                this.getResources().openRawResource(R.raw.dis_inter_ca);
        Log.d("certificateIn", caInput.toString());

        Certificate certif;
        try {
            certif = cf.generateCertificate(caInput);
            System.out.println("certif=" + ((X509Certificate)
                    certif).getSubjectDN());
        } finally {
            caInput.close();
        }

        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", certif);

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm =
                TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);
        //FIN
        URL url = API.buildApiUrl(username.getText().toString(), password.getText().toString());
        System.out.println("answer: "+ API.getReplyFromHttpUrl(url));
        //Ajouter ici le menu
    }
}