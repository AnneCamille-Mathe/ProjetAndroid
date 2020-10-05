package fr.eseo.acm.andoird.projetandroid.API;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static List<Project> parseProjectsForJury(JSONObject jsonJuryLists) throws JSONException {
        List<Project> projectList = new ArrayList<Project>();
        JSONArray jsonJuryList = jsonJuryLists.getJSONArray("juries");
        for (int i = 0; i < jsonJuryList.length(); i++) {
            JSONObject jury = jsonJuryList.getJSONObject(i);
            JSONObject info = jury.getJSONObject("info");
            JSONArray projects = info.getJSONArray("projects");
            for(int j=0; j<projects.length(); j++){
                String[] splitted_line = projects.getString(j).split("\"");
                String title = splitted_line[5];
                String supervisor = splitted_line[15]+" "+splitted_line[19];
                projectList.add(new Project(title, supervisor));
            }
        }
        return projectList;
    }
}
