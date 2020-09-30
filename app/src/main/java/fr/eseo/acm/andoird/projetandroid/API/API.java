package fr.eseo.acm.andoird.projetandroid.API;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import fr.eseo.acm.andoird.projetandroid.MainActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.objets.SSLUtil;

public class API   extends AppCompatActivity {
    static final String API_BASE_URL = "https://172.24.5.16/pfe/webservice.php?";
    static final String API_KEY_QUERY = "q";
    static final String API_LIPRJ = "LIPRJ";
    static final String API_MYPRJ = "MYPRJ";
    static final String API_LIJUR = "LIJUR";
    static final String API_MYJUR = "MYJUR";
    static final String API_JYINF = "JYINF";
    static final String API_POSTR = "POSTR";
    static final String API_NOTES = "NOTES";
    static final String API_NEWNT = "NEWNT";
    static final String API_PORTE = "PORTES";
    static final String API_LOGON = "LOGON";
    static final String API_USER = "user";
    static final String API_PASS = "pass";


    //FROM MOVIESEO
    public URL buildApiUrl(String username, String password) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        this.handleSSLHandshake();
        try {
            return new URL(Uri.parse(API_BASE_URL).buildUpon().appendQueryParameter(API_KEY_QUERY, API_LOGON).appendQueryParameter(API_USER, username).appendQueryParameter(API_PASS, password).build().toString());
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
            return null;
        }
    }

    //FROM MOVIESEO
    public String getReplyFromHttpUrl(URL url) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        this.handleSSLHandshake();

        /*
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            Scanner scanner = new Scanner(urlConnection.getInputStream());
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            String scannerText = scanner.next();
            Log.d("ASYNC", scannerText);
            if (hasInput) {
                return scannerText;
            }
            urlConnection.disconnect();
            return null;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return null;*/

        try {
            return new NetworkTask(url).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Créer la connexion sécurisée
     */
    private void handleSSLHandshake() {
        SSLContext sslContext = SSLUtil.getAppSSLContext(this.getApplication().getApplicationContext());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }



    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    private static class NetworkTask extends AsyncTask<Void, Void, String> {

        private final URL url;
        NetworkTask(URL url) {
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... params) {
            System.out.println("START");
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Scanner scanner = new Scanner(urlConnection.getInputStream());
                scanner.useDelimiter("\\A");
                boolean hasInput = scanner.hasNext();
                String scannerText = scanner.next();
                Log.d("ASYNC", scannerText);
                if (hasInput) {
                    return scannerText;
                }
                urlConnection.disconnect();
                return null;
            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("STOP");
        }
    }

    /*
    public void certificat() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, KeyManagementException {
        //DEBUT
        CertificateFactory cf = CertificateFactory.getInstance("X.509");

        // From https://www.washington.edu/itconnect/security/ca/load-der.crt
        Log.d("certificate", Integer.toString(R.raw.dis_inter_ca));

        InputStream caInput =
                //this.context.getResources().openRawResource(R.raw.dis_inter_ca);
                getResources().openRawResource(R.raw.dis_inter_ca);
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
    }
    */
}
