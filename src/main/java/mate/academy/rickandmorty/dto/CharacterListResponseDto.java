package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class CharacterListResponseDto {
    private List<CharacterResponseDto> results;
}
