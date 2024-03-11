package bar.back.services.impl;

import bar.back.dtos.LoungeDto;
import bar.back.dtos.PutLoungeDto;
import bar.back.entities.DiningTable;
import bar.back.entities.DiningTableState;
import bar.back.entities.Lounge;
import bar.back.repositories.LoungeJpaRepository;
import bar.back.services.LoungeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoungeServiceImpl implements LoungeService {
    @Autowired
    private LoungeJpaRepository loungeJpaRepository;
    @Override
    public Lounge SaveLounge(DiningTable[][] tables) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayBidimensionalJSON = objectMapper.writeValueAsString(tables);
        Lounge lounge = new Lounge();
        lounge.setTableroDeMesasJSON(arrayBidimensionalJSON);
        lounge.setName("xd");
        return lounge;
    }

    @Override
    public Lounge CreateLounge() throws JsonProcessingException {
        Lounge lounge= new Lounge();
        lounge.setName("Nuevo salon");
        DiningTable[][] arrayBidimensional = new DiningTable[10][10];;
        for (int i = 0; i < 100; i++) {
            int fila = i / 10; // Calcular la fila en base al índice del bucle
            int columna = i % 10; // Calcular la columna en base al índice del bucle
            Long id = (long) (i + 1);



            // Crear la mesa y agregarla al array bidimensional
            DiningTable mesa = new DiningTable();
            mesa.setState(DiningTableState.NOT_EXIST);
            mesa.setName(id.toString());
            mesa.setId(id);
            arrayBidimensional[fila][columna] = mesa;
        }
        lounge.setTableroDeMesas(arrayBidimensional);
        ObjectMapper objectMapper = new ObjectMapper();
        String arrayBidimensionalJSON = objectMapper.writeValueAsString(arrayBidimensional);

        lounge.setTableroDeMesasJSON(arrayBidimensionalJSON);
        loungeJpaRepository.save(lounge);
        return lounge;
    }

    @Override
    public LoungeDto GetLoungeById(Long id) {
        Lounge lounge= loungeJpaRepository.getById(id);
        LoungeDto dto= new LoungeDto();
        dto.setId(lounge.getId());
        dto.setName(lounge.getName());


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            DiningTable[][] arrayBidimensional = objectMapper.readValue(lounge.getTableroDeMesasJSON(), DiningTable[][].class);
            dto.setTableroDeMesas(arrayBidimensional);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dto;
    }

    @Override
    public List<LoungeDto> getLounges() {
        List<Lounge> loungeList=loungeJpaRepository.findAll();
        List<LoungeDto> loungeDtos=new ArrayList<>();
        for (Lounge lounge: loungeList) {
            LoungeDto dto= new LoungeDto();
            dto.setId(lounge.getId());
            dto.setName(lounge.getName());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                DiningTable[][] arrayBidimensional = objectMapper.readValue(lounge.getTableroDeMesasJSON(), DiningTable[][].class);
                dto.setTableroDeMesas(arrayBidimensional);
            } catch (IOException e) {
                e.printStackTrace();
            }
            loungeDtos.add(dto);
        }
        return loungeDtos;
    }

    @Override
    public LoungeDto EditLounge(Long id, PutLoungeDto putLoungeDto) throws JsonProcessingException {
        Lounge lounge= loungeJpaRepository.getById(id);
        if (lounge != null) {
            lounge.setTableroDeMesas(putLoungeDto.getTableroDeMesas());

            ObjectMapper objectMapper = new ObjectMapper();
            String arrayBidimensionalJSON = objectMapper.writeValueAsString(putLoungeDto.getTableroDeMesas());

            lounge.setTableroDeMesasJSON(arrayBidimensionalJSON);
            lounge.setName(putLoungeDto.getName());
            loungeJpaRepository.save(lounge);
            LoungeDto dto= new LoungeDto();
            dto.setId(lounge.getId());
            dto.setName(lounge.getName());
            try {
                DiningTable[][] arrayBidimensional = objectMapper.readValue(lounge.getTableroDeMesasJSON(), DiningTable[][].class);
                dto.setTableroDeMesas(arrayBidimensional);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dto;
        }
            return null;

    }
}
