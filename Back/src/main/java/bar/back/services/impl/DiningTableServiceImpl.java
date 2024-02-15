package bar.back.services.impl;

import bar.back.dtos.LoungeDto;
import bar.back.dtos.OccupyTableDto;
import bar.back.entities.DiningTable;
import bar.back.entities.DiningTableState;
import bar.back.entities.Lounge;
import bar.back.repositories.LoungeJpaRepository;
import bar.back.services.DiningTableService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DiningTableServiceImpl implements DiningTableService {
    @Autowired
    private LoungeJpaRepository loungeJpaRepository;
    @Override
    public Boolean occupyTable(OccupyTableDto dto) {
        Lounge lounge= loungeJpaRepository.getById(dto.getIdLounge());
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            DiningTable[][] arrayBidimensional = objectMapper.readValue(lounge.getTableroDeMesasJSON(), DiningTable[][].class);
            for (int i = 0; i < arrayBidimensional.length; i++) {
                for (int j = 0; j < arrayBidimensional.length; j++) {
                    if (arrayBidimensional[i][j].getId()==dto.getId()){
                        arrayBidimensional[i][j].setState(DiningTableState.OCCUPIED);
                        arrayBidimensional[i][j].setPeople(dto.getPeople());
                        arrayBidimensional[i][j].setIdClient(dto.getIdClient());
                        lounge.setTableroDeMesas(arrayBidimensional);
                        String arrayBidimensionalJSON = objectMapper.writeValueAsString(arrayBidimensional);

                        lounge.setTableroDeMesasJSON(arrayBidimensionalJSON);
                        loungeJpaRepository.save(lounge);
                        return true;
                    }
                }
            }
            lounge.setTableroDeMesas(arrayBidimensional);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean clearTable(Long idTable, Long idlounge) {
        Lounge lounge= loungeJpaRepository.getById(idlounge);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            DiningTable[][] arrayBidimensional = objectMapper.readValue(lounge.getTableroDeMesasJSON(), DiningTable[][].class);
            for (int i = 0; i < arrayBidimensional.length; i++) {
                for (int j = 0; j < arrayBidimensional.length; j++) {
                    if (arrayBidimensional[i][j].getId()==idTable){
                        arrayBidimensional[i][j].setState(DiningTableState.OPEN);
                        arrayBidimensional[i][j].setPeople(null);
                        arrayBidimensional[i][j].setIdClient(null);
                        lounge.setTableroDeMesas(arrayBidimensional);
                        String arrayBidimensionalJSON = objectMapper.writeValueAsString(arrayBidimensional);

                        lounge.setTableroDeMesasJSON(arrayBidimensionalJSON);
                        loungeJpaRepository.save(lounge);
                        return true;
                    }
                }
            }
            lounge.setTableroDeMesas(arrayBidimensional);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
