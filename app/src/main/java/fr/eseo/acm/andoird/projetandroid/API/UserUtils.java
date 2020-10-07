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
            projectsList.add(new Project(title, supervisor));
        }
        return projectsList;
    }

    public static List<Project> parseForProjectsWithDescrip(JSONObject jsonResults) throws JSONException {
        List<Project> projectsList = new ArrayList<Project>();
        String supervisor = "";
        JSONArray jsonProjectsList = jsonResults.getJSONArray("projects");
        for (int i = 0; i < jsonProjectsList.length(); i++) {
            JSONObject project = jsonProjectsList.getJSONObject(i);
            String title = project.getString("title");
            String descript = project.getString("descrip");
            JSONObject jsonSupervisor = project.getJSONObject("supervisor");
            supervisor = jsonSupervisor.getString("forename") + " " + jsonSupervisor.getString("surname");
            projectsList.add(new Project(title, supervisor, descript));
        }
        return projectsList;
    }

    public static List<Project> parseForProjectsFromJury(String array){
        List<Project> projectsList = new ArrayList<Project>();
        String[] list = array.split("\\}]\\}, ");
        for(int i=0; i<list.length; i++){
            System.out.println(list[i]);
            String[] projectElements = list[i].split("\"");
            int id = Integer.parseInt(projectElements[2].split(",")[0].substring(1));
            String title = projectElements[5];
            String supervisor = projectElements[19]+" "+projectElements[23];
            String description = projectElements[9];
            int confidentiality = Integer.parseInt(projectElements[12].substring(1,2));
            boolean poster = Boolean.parseBoolean(projectElements[14].split(",")[0].substring(1));
            projectsList.add(new Project(id, title, description, supervisor, poster, confidentiality));
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
        JSONArray projects = jsonJuryLists.getJSONArray("projects");
        for(int j=0; j<projects.length(); j++){
            allProjects.add(projects.getString(j));
            String[] splitted_line = projects.getString(j).split("\"");
            String title = splitted_line[5];
            String supervisor = splitted_line[19]+" "+splitted_line[23];
            projectList.add(new Project(title, supervisor));
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("projectsFromJury", allProjects.toString());
        editor.commit();
        return projectList;
    }
}
