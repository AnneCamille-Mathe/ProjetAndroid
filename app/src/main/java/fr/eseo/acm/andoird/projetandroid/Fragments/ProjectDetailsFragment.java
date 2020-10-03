package fr.eseo.acm.andoird.projetandroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.eseo.acm.andoird.projetandroid.Navigation.AllProjectsActivity;
import fr.eseo.acm.andoird.projetandroid.R;
import fr.eseo.acm.andoird.projetandroid.View.ProjectRoleViewAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ProjectDetailsFragment  extends Fragment{
    private RecyclerView roles;
    private Button reviewsButton;
    private TextView title;
    private TextView students;
    private TextView supervisor;
    private TextView confidentiality;
    private TextView description;
    private ImageView poster;

    private int movieId;

    private ProjectRoleViewAdapter roleAdapter;



    public static ProjectDetailsFragment newInstance(int movieId){
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(AllProjectsActivity.MOVIE_ID, movieId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater,parent,savedInstanceState);
        return inflater.inflate(R.layout.fragment_project_details, parent, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){
            this.movieId = getArguments().getInt(AllProjectsActivity.MOVIE_ID,0);

            this.title = view.findViewById(R.id.title);
            this.students = view.findViewById(R.id.students);
            this.supervisor = view.findViewById(R.id.supervisor);
            this.confidentiality = view.findViewById(R.id.confidentiality);
            this.description = view.findViewById(R.id.description);
            this.poster = view.findViewById(R.id.poster);

        }
    }


}
