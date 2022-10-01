package massal.CharacterSelecter.service;

import massal.CharacterSelecter.DTO.PlayerRegistrationDTO;
import massal.CharacterSelecter.model.Champion;
import massal.CharacterSelecter.model.Player;
import massal.CharacterSelecter.repo.Playerrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private Playerrepo prepo;


    public PlayerServiceImpl(Playerrepo prepo) {
        super();
        this.prepo = prepo;
    }

    @Override
    public void save(PlayerRegistrationDTO playerRegistrationDTO) {
        Player player = new Player( playerRegistrationDTO.getUsername(),
                                    playerRegistrationDTO.getPoste(),
                                    playerRegistrationDTO.getChampion_pool());
        prepo.save(player);
    }

    @Override
    public List<Player> getplayer(){
        return prepo.findAll();
    }

    @Override
    public void addChampion(Long id, String name) {
        Champion champion = new Champion(name);
        Player player = this.prepo.findById(id).get();
        player.getChampion_pool().add(champion);
        prepo.save(player);
    }

    @Override
    public void deleteplayer(Long id){
        prepo.deleteById(id);
    }

}
