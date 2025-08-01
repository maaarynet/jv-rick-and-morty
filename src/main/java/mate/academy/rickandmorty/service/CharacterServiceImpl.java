package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.exception.EmptyDatabaseException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public Character getRandomCharacter() {
        long recordCount = characterRepository.count();
        if (recordCount == 0) {
            throw new EmptyDatabaseException("No characters in database");
        }
        int randomId = new Random().nextInt((int) recordCount) + 1;
        return characterRepository.findById((long) randomId)
                .orElseThrow(() -> new CharacterNotFoundException(
                        "Not found Character with id " + randomId));
    }

    @Override
    public List<Character> findByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }
}
