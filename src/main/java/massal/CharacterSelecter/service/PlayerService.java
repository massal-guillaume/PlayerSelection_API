package massal.CharacterSelecter.service;

import massal.CharacterSelecter.DTO.PlayerRegistrationDTO;
import massal.CharacterSelecter.model.Player;

import java.util.List;

public interface PlayerService {

    void save(PlayerRegistrationDTO playerRegistrationDTO);
    public List<Player> getplayer();

    void deleteplayer(Long id);

    public void addChampion(Long id, String name);


}
