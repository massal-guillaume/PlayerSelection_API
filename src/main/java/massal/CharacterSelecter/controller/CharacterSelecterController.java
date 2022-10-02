package massal.CharacterSelecter.controller;

import massal.CharacterSelecter.DTO.PlayerRegistrationDTO;
import massal.CharacterSelecter.model.Player;
import massal.CharacterSelecter.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CharacterSelecterController {

    @Autowired
    private final PlayerService playerService;

    public CharacterSelecterController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/CharacterSelector/getplayer")
    public List<Player> getplayer(){
        return playerService.getplayer();
    }

    @PutMapping("/CharacterSelector/updateplayer/addchampion/{id}/{name}")
    public String addchampion(@PathVariable Long id, @PathVariable String name){
       return  this.playerService.addChampion(id,name);
    }

    @PutMapping("/CharacterSelector/updateplayer/{id}")
    public String updateplayer(@PathVariable Long id,@RequestBody PlayerRegistrationDTO playerRegistrationDTO){
       return  this.playerService.updateplayer(id,playerRegistrationDTO);
    }


    @DeleteMapping("/CharacterSelector/deleteplayer/{id}")
    public String deleteplayerbyid(@PathVariable("id") Long id){
        return playerService.deleteplayer(id);
    }


    @PostMapping("/CharacterSelector/saveplayer")
    public String registerPlayer(@RequestBody PlayerRegistrationDTO playerRegistrationDTO) throws Exception{
        playerService.save(playerRegistrationDTO);
        return "save succeeded";
   }








}
