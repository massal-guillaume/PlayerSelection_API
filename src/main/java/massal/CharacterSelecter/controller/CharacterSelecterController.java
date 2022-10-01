package massal.CharacterSelecter.controller;

import massal.CharacterSelecter.DTO.PlayerRegistrationDTO;
import massal.CharacterSelecter.model.Player;
import massal.CharacterSelecter.service.PlayerService;
import massal.CharacterSelecter.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CharacterSelecterController {

    @Autowired
    private PlayerService playerService;

    public CharacterSelecterController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("/CharacterSelector/getplayer")
    public List<Player> getplayer(){
        return playerService.getplayer();
    }

    @PutMapping("/CharacterSelector/updateplayer/addchampion/{id}/{name}")
    public String updateplayer(@PathVariable Long id, @PathVariable String name){
        this.playerService.addChampion(id,name);
        System.out.println(name);
        return "Champion added";

    }


    @DeleteMapping("/CharacterSelector/deleteplayer/{id}")
    public String deleteplayerbyid(@PathVariable("id") Long id){
        playerService.deleteplayer(id);
        return "successful deletion";
    }


    @PostMapping("/CharacterSelector/saveplayer")
    public String registerPlayer(@RequestBody PlayerRegistrationDTO playerRegistrationDTO){
        playerService.save(playerRegistrationDTO);
        return "save succeeded";
   }








}
