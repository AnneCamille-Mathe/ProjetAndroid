package fr.eseo.acm.andoird.projetandroid.room;

public class Project {

    private int idProject;
    private String title;
    private String description;
    private String superviseur;
    private boolean poster;
    private int confidentiality;

    public Project(int idProject, String titre, String description, String superviseur, boolean poster, int confidentiality) {
        this.idProject = idProject;
        this.title = titre;
        this.description = description;
        this.superviseur = superviseur;
        this.poster = poster;
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

    public boolean getPoster() {
        return poster;
    }

    public void setPoster(boolean poster) {
        this.poster = poster;
    }

    public int getConfidentiality() {
        return confidentiality;
    }

    public void setConfidentiality(int confidentiality) {
        this.confidentiality = confidentiality;
    }

    @Override
    public String toString()  {
        return this.title +" (Description: "+ this.description+")";
    }
}
