package massal.CharacterSelecter.service;

import massal.CharacterSelecter.DTO.PlayerRegistrationDTO;
import massal.CharacterSelecter.model.Champion;
import massal.CharacterSelecter.model.Player;
import massal.CharacterSelecter.repo.Playerrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Player> getchampbyplayer(String champName){

        List<Player> lplayer = this.getplayer();
        List<Player> lanswer = new ArrayList<>();
        for(Player player : lplayer){
            for(Champion c : player.getChampion_pool()) {
                if (c.getName().equals(champName)) {
                    lanswer.add(player);
                    break;
                }
            }
        }
        return lanswer;
    }

    @Override
    public List<Champion> getflexpick(String username1,String username2) throws Exception{

        List<Player> list = prepo.findAll();
        Optional<Player> p1 = Optional.of(list.stream().filter(s -> s.getUsername().equals(username1)).findFirst().get());
        Optional<Player> p2 = Optional.of(list.stream().filter(s -> s.getUsername().equals(username2)).findFirst().get());
        if(p1.isPresent() && p2.isPresent()){
           List<Champion> flexpick = p1.get().getChampion_pool();
           flexpick.retainAll(p2.get().getChampion_pool());
            if(flexpick.isEmpty()) throw new Exception("Il n'y a aucun champions en commun entre les deux joueurs");
            else return flexpick;
        }else throw new Exception("Un des deux joueurs n'est pas present dans la base de donées");

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
