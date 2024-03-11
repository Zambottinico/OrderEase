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
    OrderDetailDto AddDetail(DetailDto dto);
    boolean deleteDetail(Long id);

    List<OrderDetailDto> GetDetails(Long idLounge, Long idTable);
}
