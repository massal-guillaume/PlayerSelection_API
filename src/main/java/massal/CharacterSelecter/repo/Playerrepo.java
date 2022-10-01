package massal.CharacterSelecter.repo;

import massal.CharacterSelecter.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Playerrepo extends JpaRepository<Player,Long> {
}
