package massal.CharacterSelecter.service;

import massal.CharacterSelecter.DTO.PlayerRegistrationDTO;
import massal.CharacterSelecter.model.Champion;
import massal.CharacterSelecter.model.Player;
import massal.CharacterSelecter.repo.Playerrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private final Playerrepo prepo;


    public PlayerServiceImpl(Playerrepo prepo) {
        super();
        this.prepo = prepo;
    }

    @Override
    public Player save(PlayerRegistrationDTO playerRegistrationDTO) throws Exception {
        Player player = new Player( playerRegistrationDTO.getUsername(),
                                    playerRegistrationDTO.getPoste(),
                                    playerRegistrationDTO.getChampion_pool());
        try {
            prepo.save(player);
        }catch (Exception e){
           throw e;
        }
        return player;
    }

    @Override
    public List<Player> getplayer(){
        return prepo.findAll();
    }

    @Override
    public String addChampion(Long id, String name) {
        Champion champion = new Champion(name);
        if(this.prepo.findById(id).isPresent()){
            Player player = this.prepo.findById(id).get();
            player.getChampion_pool().add(champion);
            prepo.save(player);
            return "Champion added";
        }
        return "Player not exist with id: "+ id;
    }

    @Override
    public String updateplayer(Long id,PlayerRegistrationDTO playerRegistrationDTO){
        Player updatedplayer;
        if(prepo.findById(id).isPresent()){

            updatedplayer = prepo.findById(id).get();

            updatedplayer.setPoste(playerRegistrationDTO.getPoste());
            updatedplayer.setUsername(playerRegistrationDTO.getUsername());
            updatedplayer.setChampion_pool(playerRegistrationDTO.getChampion_pool());

            prepo.save(updatedplayer);

            return "Player Updated";
        }else  return "Player not exist with id: "+ id;


    }
    @Override
    public Optional<Player> findbyid(Long id){

        return this.prepo.findById(id);

    }



    @Override
    public String deleteplayer(Long id){
        if(prepo.findById(id).isPresent()){
            prepo.deleteById(id);
            return "successful deletion";
        }else {

            return "Player not exist with id: "+ id;
        }
    }

}
