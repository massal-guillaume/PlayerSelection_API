package massal.CharacterSelecter;

import massal.CharacterSelecter.model.Champion;
import massal.CharacterSelecter.model.Player;
import massal.CharacterSelecter.repo.Playerrepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    public void testfindbyid(){
        Optional<Player> player = playerrepo.findById(Long.valueOf(1));
        assertEquals(player.get().getId(),Long.valueOf(1));
    }

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





}
