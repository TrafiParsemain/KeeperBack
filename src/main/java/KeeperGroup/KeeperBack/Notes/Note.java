package KeeperGroup.KeeperBack.Notes;

public class Note {

    private long id;
    private String username;
    private String title;
    private String content;
    private String statut;

    //Constructeur vide qui appelle les setter nécessaire pour un put ?
    protected  Note(){

    }

    public Note(long id, String username, String title, String content, String statut) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.statut = statut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
