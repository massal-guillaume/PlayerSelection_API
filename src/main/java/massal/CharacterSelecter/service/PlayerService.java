package massal.CharacterSelecter.service;

import massal.CharacterSelecter.DTO.PlayerRegistrationDTO;
import massal.CharacterSelecter.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    Player save(PlayerRegistrationDTO playerRegistrationDTO) throws Exception;
    List<Player> getplayer();

    String deleteplayer(Long id);

    String addChampion(Long id, String name);

    String updateplayer(Long id,PlayerRegistrationDTO playerRegistrationDTO);


    Optional<Player> findbyid(Long id);

}
