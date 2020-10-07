package fr.eseo.acm.andoird.projetandroid.API;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import fr.eseo.acm.andoird.projetandroid.objets.SSLUtil;

public class API   extends AppCompatActivity {
    public static final String API_BASE_URL = "https://172.24.5.16/pfe/webservice.php?";
    public static final String API_KEY_QUERY = "q";
    public static final String API_LOGON = "LOGON";
    public static final String API_PROJECTS = "LIPRJ";
    public static final String API_NOTES = "NOTES";
    public static final String API_MYINF = "MYINF";
    public static final String API_USER = "user";
    public static final String API_PASS = "pass";
    public static final String API_TOKEN = "token";
    public static final String API_JURY = "jury";
    public static final String API_JURYINFO = "JYINF";
    public static final String API_MYJURY = "MYJUR";


    //FROM MOVIESEO
    public URL buildRequest(String query, String[] parameters) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        this.handleSSLHandshake();
        try {
            Uri.Builder url = Uri.parse(API_BASE_URL).buildUpon().appendQueryParameter(API_KEY_QUERY, query);
            for(int i=0; i<parameters.length; i = i+2){
                url.appendQueryParameter(parameters[i], parameters[i+1]);
            }
            return new URL(url.build().toString());
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
            return null;
        }
    }

    //FROM MOVIESEO
    public String getReplyFromHttpUrl(URL url) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        this.handleSSLHandshake();


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

}
