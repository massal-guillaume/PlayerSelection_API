package massal.CharacterSelecter;

import massal.CharacterSelecter.model.Champion;
import massal.CharacterSelecter.model.Player;
import massal.CharacterSelecter.repo.Playerrepo;
import massal.CharacterSelecter.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerTest {

    @Autowired
    private Playerrepo playerrepo;



    @Test
    public void testsaveplayernotnull(){
        List<Champion> champion_pool = new ArrayList<Champion>();
       Player player = new Player("test_username","test_poste",champion_pool);
       playerrepo.save(player);
        assertNotNull(player);

    }

    @Test
    public void testsaveplayer(){
        List<Champion> champion_pool = new ArrayList<Champion>();
        Player player = new Player("test_username","test_poste",champion_pool);
        Player playersaved = playerrepo.save(player);

        assertEquals(player,playersaved);
    }

    @Test
    public void testfindbyid(){
        List<Champion> champion_pool = new ArrayList<Champion>();
        Player player = new Player("test_username","test_poste",champion_pool);
        Long id = playerrepo.save(player).getId();
        Optional<Player> playeradded = playerrepo.findById(id);
        assertEquals(playeradded.get().getId(),id);
    }

   @Test
    public void testupdateplayer(){
       List<Champion> champion_pool = new ArrayList<Champion>();
       Player player = new Player("test_username","test_poste",champion_pool);

       Long id = this.playerrepo.save(player).getId();

           Optional<Player> savedplayer = this.playerrepo.findById(id);

       savedplayer.get().setUsername("test_update");
       this.playerrepo.save(savedplayer.get());

       assertEquals(this.playerrepo.findById(id).get().getUsername(),"test_update");

   }


   @Test
    public void testdeleteplayer(){
       List<Champion> champion_pool = new ArrayList<>();
       Player player = new Player("test_username","test_poste",champion_pool);
       Long id = this.playerrepo.save(player).getId();
       this.playerrepo.deleteById(id);
       assertTrue(this.playerrepo.findById(id).isEmpty());
   }

   @Test
    public void testaddchampion(){
       List<Champion> champion_pool = new ArrayList<>();
       Player player = new Player("test_username","test_poste",champion_pool);
       Long id = this.playerrepo.save(player).getId();

       Champion champion = new Champion("Xerath");

       Optional<Player> savedplayer = this.playerrepo.findById(id);

       savedplayer.get().getChampion_pool().add(champion);
       this.playerrepo.save(savedplayer.get());

       assertEquals(this.playerrepo.findById(id).get().getChampion_pool().get(0).getName(),champion.getName());


   }

   @Test
    public void testgetflexpick(){
       List<Champion> champion_pool1 = new ArrayList<>();
       List<Champion> champion_pool2 = new ArrayList<>();
       Champion c1 = new Champion("aatrox");
       Champion c2 = new Champion("thresh");
       champion_pool1.add(c1);
       champion_pool1.add(c2);
       champion_pool2.add(c1);
       Player player1 = new Player("test_flexpick1","test_poste1",champion_pool1);
       Player player2 = new Player("test_flexpick2","test_poste2",champion_pool2);
       Long id1 = this.playerrepo.save(player1).getId();
       Long id2 = this.playerrepo.save(player2).getId();

       List<Player> list = playerrepo.findAll();
       Optional<Player> p1 = Optional.of(list.stream().filter(s -> s.getUsername().equals("test_flexpick1")).findFirst().get());
       Optional<Player> p2 = Optional.of(list.stream().filter(s -> s.getUsername().equals("test_flexpick2")).findFirst().get());
       List<Champion> flexpick = p1.get().getChampion_pool();
       flexpick.retainAll(p2.get().getChampion_pool());
       assertEquals(flexpick.get(0).getName(),"aatrox");
    }




}
