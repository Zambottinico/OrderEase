package bar.back.services;

import bar.back.dtos.OccupyTableDto;
import org.springframework.stereotype.Service;

@Service
public interface DiningTableService {
    Boolean occupyTable(OccupyTableDto dto);
    boolean clearTable(Long idTable,Long idlounge);
}
