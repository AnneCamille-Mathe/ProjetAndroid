package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.API.UserUtils;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.adapter.ListJuryAdapter;
import fr.eseo.acm.andoird.projetandroid.room.Jury;

public class ListJuryFragment extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListJuryAdapter mAdapter;
    private List<Jury> mJuryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_mine);

        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_jury_list);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Populate the list of projects
        mJuryList = new ArrayList<Jury>();
        Intent intent = getIntent();
        if(getIntent().hasExtra("json")) {
            try {
                JSONObject jsonJuryLists = new JSONObject(getIntent().getStringExtra("json"));
                mJuryList = UserUtils.parseForJury(jsonJuryLists);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //set adapter to recyclerview
        mAdapter = new ListJuryAdapter(mJuryList,this);
        mRecyclerView.setAdapter(mAdapter);
    }
}
