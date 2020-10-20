package fr.eseo.acm.andoird.projetandroid.room;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Project {

    private int idProject;
    private String title;
    private String description;
    private String superviseur;
    private String posters;
    private Bitmap poster;
    private int confidentiality;
    private ArrayList<String> members;

    public Project(int idProject, String titre, String description, String superviseur, Bitmap poster, int confidentiality) {
        this.idProject = idProject;
        this.title = titre;
        this.description = description;
        this.superviseur = superviseur;
        this.poster = poster;
        this.confidentiality = confidentiality;
    }

    public Project(int idProject, String titre, String description, String superviseur, int confidentiality) {
        this.idProject = idProject;
        this.title = titre;
        this.description = description;
        this.superviseur = superviseur;
        this.confidentiality = confidentiality;
    }

    public Project(String titre, String superviseur) {
        this.title = titre;
        this.superviseur = superviseur;
    }

    public Project(String titre, String superviseur, String description) {
        this.title = titre;
        this.superviseur = superviseur;
        this.description = description;
    }

    public Project(int idProject, String title, String description, String poster){
        this.idProject = idProject;
        this.title = title;
        this.description = description;
        this.posters = poster;
    }

    public Project(int idProject, String title){
        this.idProject = idProject;
        this.title = title;
        this.description = "d";
    }

    public Project(int idProject) {
        this.idProject = idProject;
        this.description = "d";
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titre) {
        this.title = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(String superviseur) {
        this.superviseur = superviseur;
    }

    public Bitmap getPoster() {
        return poster;
    }

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    public int getConfidentiality() {
        return confidentiality;
    }

    public void setConfidentiality(int confidentiality) {
        this.confidentiality = confidentiality;
    }

    public ArrayList<String> getMembers() { return this.members; }

    public void setMembers(ArrayList<String> members){ this.members = members; }

    @Override
    public String toString()  {
        return this.title +" (Description: "+ this.description+")";
    }
}
