package fr.eseo.acm.andoird.projetandroid.entites;

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

    public Jury(String date, int id){
        this.date = date;
        this.idJury = id;
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
