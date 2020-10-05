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
        String title = "";
        JSONArray jsonJuryList = jsonJuryLists.getJSONArray("juries");
        for (int i = 0; i < jsonJuryList.length(); i++) {
            JSONObject jury = jsonJuryList.getJSONObject(i);
            String date = jury.getString("date");
            JSONObject juryInfo = jury.getJSONObject("info");
            JSONArray jsonProject = juryInfo.getJSONArray("projects");
            title = jsonProject.getString(0).split("\"")[5];
            System.out.println("TITLE " + title);
            juryList.add(new Jury(date, title));
        }
        return juryList;
    }
}
