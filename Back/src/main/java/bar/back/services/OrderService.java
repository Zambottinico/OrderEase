package bar.back.services;

import bar.back.dtos.DetailDto;
import bar.back.dtos.OccupyTableDto;
import bar.back.dtos.OrderDetailDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    void CreateOrder(OccupyTableDto dto);


    @Transactional
    boolean AddDetail(DetailDto dto);

    List<OrderDetailDto> GetDetails(Long idLounge, Long idTable);
}
