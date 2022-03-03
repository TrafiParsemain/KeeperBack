package KeeperGroup.KeeperBack.Notes;


import javax.persistence.*;


@Entity
public class Note {

    //Key of the entity
    //Auto generated value
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",updatable = false)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
