package bar.back.services;

import bar.back.dtos.LoungeDto;
import bar.back.dtos.PutLoungeDto;
import bar.back.entities.DiningTable;
import bar.back.entities.Lounge;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface LoungeService {
    Lounge SaveLounge(DiningTable[][] tables) throws JsonProcessingException;
    Lounge CreateLounge() throws JsonProcessingException;
    LoungeDto GetLoungeById(Long id);
    List<LoungeDto> getLounges();

    LoungeDto EditLounge(Long id, PutLoungeDto tables) throws JsonProcessingException;
}
