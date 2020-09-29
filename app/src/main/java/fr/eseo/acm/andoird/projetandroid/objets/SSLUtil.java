package fr.eseo.acm.andoird.projetandroid.objets;

import android.content.Context;
import android.util.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import fr.eseo.acm.andoird.projetandroid.R;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SSLUtil {

    public static Pair<SSLContext, TrustManager> getSSLAndTrust(Context context) {
        try {
            return getSSLContext(context);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException("Impossible d'importer les certificats");
    }

    public static SSLContext getAppSSLContext(Context context) {
        return getSSLAndTrust(context).first;
    }

    private static Pair<SSLContext, TrustManager> getSSLContext(Context context) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, NoSuchAlgorithmException {
        // loading CAs from an InputStream

        Certificate ca = getCertificate(context, R.raw.dis_inter_ca);

        // creating a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // creating a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        return new Pair<>(sslContext, tmf.getTrustManagers()[0]);
    }


    private static Certificate getCertificate(Context context, int certId) throws CertificateException, CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream cert = context.getResources().openRawResource(certId);
        Certificate ca;
        try {
            ca = cf.generateCertificate(cert);
        } finally {
            try {
                cert.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ca;
    }
}