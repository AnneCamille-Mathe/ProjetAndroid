package fr.eseo.acm.andoird.projetandroid.room;

import java.util.Date;

public class Jury {

    private int idJury;
    private String date;
    private String[] members;
    private Project[] projects;

    public Jury(int id, String date, String[] members, Project[] projets){
        this.idJury = id;
        this.date = date;
        this.members = members;
        this.projects = projets;
    }

    public Jury(String date, String title){
        this.date = date;
        this.projects = new Project[] {new Project(title,null)};
    }

    public int getIdJury() {
        return idJury;
    }

    public void setIdJury(int idJury) {
        this.idJury = idJury;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public Project[] getProjects() {
        return projects;
    }

    public void setProjects(Project[] projects) {
        this.projects = projects;
    }

}
