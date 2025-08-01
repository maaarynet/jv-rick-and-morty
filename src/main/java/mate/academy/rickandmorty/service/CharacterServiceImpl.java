package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public Character getRandomCharacter() {
        return characterRepository.getRandomCharacter()
                .orElseThrow(() -> new CharacterNotFoundException("No characters found"));
    }

    @Override
    public List<Character> findByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }
}
