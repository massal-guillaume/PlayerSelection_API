package massal.CharacterSelecter.DTO;

import massal.CharacterSelecter.model.Champion;

import java.util.List;

public class PlayerRegistrationDTO {

    private String username;
    private String poste;
    private List<Champion> champion_pool;

    public PlayerRegistrationDTO(String username, String poste, List<Champion> champion_pool) {
        this.username = username;
        this.poste = poste;
        this.champion_pool = champion_pool;
    }

    public List<Champion> getChampion_pool() {
        return champion_pool;
    }

    public void setChampion_pool(List<Champion> champion_pool) {
        this.champion_pool = champion_pool;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

}
