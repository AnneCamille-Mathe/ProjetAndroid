package fr.eseo.acm.andoird.projetandroid.activities.jury;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.api.API;
import fr.eseo.acm.andoird.projetandroid.api.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.adapters.ListProjectsFromJuryAdapter;
import fr.eseo.acm.andoird.projetandroid.entites.Project;

public class JuryDetailsActivity extends API {

    private RecyclerView mRecyclerView;
    private ListProjectsFromJuryAdapter mAdapter;
    private List<Project> mProjectList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_details);
        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_jury_project_list);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String juryId = String.valueOf(getIntent().getIntExtra("id", 0));
        JSONObject jury = null;
        try {
            jury = new JSONObject(this.getJuryInfo(juryId));
        } catch (JSONException | CertificateException | NoSuchAlgorithmException | KeyStoreException | IOException | KeyManagementException e) {
            e.printStackTrace();
        }
        if(jury != null){
            try {
                mProjectList = UserUtils.parseProjectsForJury(jury, getApplicationContext());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mAdapter = new ListProjectsFromJuryAdapter(mProjectList,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public String getJuryInfo(String id) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = sharedPref.getString("saved_username", "le login n'est pas trouvé");
        String token = sharedPref.getString("saved_token", "le token n'est pas trouvé");
        String[] params = new String[] {API.API_USER, username, API.API_JURY, id, API.API_TOKEN, token};
        URL url = this.buildRequest(API.API_JURYINFO, params);
        return this.getReplyFromHttpUrl(url);
    }
}
