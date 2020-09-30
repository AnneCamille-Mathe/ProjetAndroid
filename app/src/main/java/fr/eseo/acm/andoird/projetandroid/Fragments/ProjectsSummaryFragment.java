package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.vues.ProjectsSummaryListViewAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectsSummaryFragment extends Fragment {
    private ProjectsSummaryListViewAdapter viewAdapter;

    public static ProjectsSummaryFragment newInstance(){
        ProjectsSummaryFragment fragment = new ProjectsSummaryFragment();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        Log.d("MovieSummaryFragment","onCreateView() called");
        return inflater.inflate(R.layout.fragment_project_summary_list, parent, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        Log.d("MovieSummaryFragment","onViewCreated() called");
        RecyclerView recycler = view.findViewById(R.id.recycler_projects_summary_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(llm);
        recycler.setHasFixedSize(true);
        viewAdapter = new ProjectsSummaryListViewAdapter(this);
        recycler.setAdapter(viewAdapter);
    }

}
