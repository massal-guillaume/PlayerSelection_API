package massal.CharacterSelecter.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String username;

    private String poste;


    @ElementCollection
    @CollectionTable(name = "champion_pool", joinColumns = @JoinColumn(name = "player_id"), foreignKey = @ForeignKey(name = "champion_id"))
    private List<Champion> champion_pool;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<Champion> getChampion_pool() {
        return champion_pool;
    }

    public void setChampion_pool(List<Champion> champion_pool) {
        this.champion_pool = champion_pool;
    }

    public Player() {
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        poste = poste;
    }

    public Player(String username, String poste,List<Champion>champion_pool) {
        super();
        this.username=username;
        this.poste=poste;
        this.champion_pool=champion_pool;
    }

    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

}
