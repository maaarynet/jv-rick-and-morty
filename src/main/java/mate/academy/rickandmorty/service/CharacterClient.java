package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterListResponseDto;
import mate.academy.rickandmorty.exception.HttpSendRequestException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    public List<Character> downloadCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString());
            CharacterListResponseDto responseDto = objectMapper.readValue(
                        response.body(), CharacterListResponseDto.class);
            List<Character> list = responseDto.getResults().stream()
                        .map(characterMapper::toCharacter)
                        .toList();
            return characterRepository.saveAll(list);
        } catch (IOException | InterruptedException e) {
            throw new HttpSendRequestException("Cant send request to RickAndMorty API");
        }
    }

    @PostConstruct
    public void init() {
        if (characterRepository.count() == 0) {
            downloadCharacters();
        }
    }
}
