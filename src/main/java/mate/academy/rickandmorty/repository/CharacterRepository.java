package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CharacterRepository extends
        JpaRepository<Character, Long>,
        JpaSpecificationExecutor<Character> {

    List<Character> findByNameContainingIgnoreCase(String name);
}
