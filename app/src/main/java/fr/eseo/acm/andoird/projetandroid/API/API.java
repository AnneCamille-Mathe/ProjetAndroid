package fr.eseo.acm.andoird.projetandroid.API;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import fr.eseo.acm.andoird.projetandroid.R;

public class API   extends AppCompatActivity {
    static final String API_BASE_URL = "https://192.168.4.248/pfe/webservice.php?";
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



    public static URL buildApiUrl(String movieName, String year) {
        try {
            return new URL(Uri.parse(API_BASE_URL).buildUpon().appendQueryParameter(API_KEY_QUERY, API_LOGON).appendQueryParameter(API_USER, movieName).appendQueryParameter(API_PASS, year).build().toString());
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
            return null;
        }
    }

    public static String getReplyFromHttpUrl(URL url) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
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
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String parseForPoster(String results) {
        Log.d("parseForPoster", results);
        try {
            String posterPath = new JSONObject(results).getJSONArray("results").getJSONObject(0).getString("poster_path");
            Log.d("Poster path", posterPath);
            return posterPath;
        } catch (JSONException e) {
            return null;
        }
    }

}
