package fr.eseo.acm.andoird.projetandroid.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import fr.eseo.acm.andoird.projetandroid.room.Jury;
import fr.eseo.acm.andoird.projetandroid.room.Project;

public class UserUtils extends AppCompatActivity {
    public static List<Project> parseForProjects(JSONObject jsonResults) throws JSONException {
        List<Project> projectsList = new ArrayList<Project>();
        String supervisor = "";
        JSONArray jsonProjectsList = jsonResults.getJSONArray("projects");
        for (int i = 0; i < jsonProjectsList.length(); i++) {
            JSONObject project = jsonProjectsList.getJSONObject(i);
            String title = project.getString("title");
            JSONObject jsonSupervisor = project.getJSONObject("supervisor");
            supervisor = jsonSupervisor.getString("forename") + " " + jsonSupervisor.getString("surname");
            System.out.println(supervisor);
            projectsList.add(new Project(title, supervisor));
        }
        return projectsList;
    }

    public static List<Project> parseForProjectsFromJury(String array){
        List<Project> projectsList = new ArrayList<Project>();
        String[] list = array.split(", ");
        for(int i=0; i<list.length; i++){
            String[] projectElements = list[i].split("\"");
            String title = projectElements[5];
            String supervisor = projectElements[15]+" "+projectElements[19];
            projectsList.add(new Project(title,supervisor));
        }
        return projectsList;
    }

    public static List<Jury> parseForJury(JSONObject jsonJuryLists) throws JSONException {
        List<Jury> juryList = new ArrayList<Jury>();
        JSONArray jsonJuryList = jsonJuryLists.getJSONArray("juries");
        for (int i = 0; i < jsonJuryList.length(); i++) {
            JSONObject jury = jsonJuryList.getJSONObject(i);
            String date = jury.getString("date");
            int id = jury.getInt("idJury");
            juryList.add(new Jury(date, id));
        }
        return juryList;
    }

    public static List<Project> parseProjectsForJury(JSONObject jsonJuryLists, Context applicationContext) throws JSONException {
        List<Project> projectList = new ArrayList<Project>();
        List<String> allProjects = new ArrayList<>();
        JSONArray jsonJuryList = jsonJuryLists.getJSONArray("juries");
        for (int i = 0; i < jsonJuryList.length(); i++) {
            JSONObject jury = jsonJuryList.getJSONObject(i);
            JSONObject info = jury.getJSONObject("info");
            JSONArray projects = info.getJSONArray("projects");
            for(int j=0; j<projects.length(); j++){
                allProjects.add(projects.getString(j));
                String[] splitted_line = projects.getString(j).split("\"");
                String title = splitted_line[5];
                String supervisor = splitted_line[15]+" "+splitted_line[19];
                projectList.add(new Project(title, supervisor));
            }
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        SharedPreferences.Editor editor = sharedPref.edit();
        System.out.println(allProjects.toString());
        editor.putString("projectsFromJury", allProjects.toString());
        editor.commit();
        return projectList;
    }
}
