package bar.back.services.impl;

import bar.back.dtos.DetailDto;
import bar.back.dtos.OccupyTableDto;
import bar.back.dtos.OrderDetailDto;
import bar.back.entities.Client;
import bar.back.entities.DetailOrder;
import bar.back.entities.OrderEntity;
import bar.back.repositories.ClientJpaRepository;
import bar.back.repositories.OrderJpaRepository;
import bar.back.repositories.ProductJpaRepository;
import bar.back.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderJpaRepository orderJpaRepository;
    @Autowired
    private ClientJpaRepository clientJpaRepository;
    @Autowired
    private ProductJpaRepository productJpaRepository;
    @Override
    public void CreateOrder(OccupyTableDto dto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setQuantityPeople(dto.getPeople());
        orderEntity.setAvailable(true);
        orderEntity.setIdLounge(dto.getIdLounge());
        orderEntity.setIdTable(dto.getId());
        List<DetailOrder> details = new ArrayList<>();
        Client client = clientJpaRepository.getReferenceById(dto.getIdClient());
        orderEntity.setClient(client);
        orderEntity.setDetails(details);

        orderJpaRepository.save(orderEntity);
    }

    @Override
    @Transactional
    public boolean AddDetail(DetailDto dto) {
        Optional<OrderEntity> optionalOrder = orderJpaRepository.findByAvailableTrueAndIdLoungeAndIdTable(dto.getIdLounge(), dto.getIdTable());
        if (optionalOrder.isPresent()) {
            OrderEntity orderEntity = optionalOrder.get();

            DetailOrder detailOrder = new DetailOrder();
            detailOrder.setComment(dto.getComment());
            detailOrder.setProduct(productJpaRepository.getById(dto.getIdProduct()));
            detailOrder.setQuantity(dto.getQuantity());
            detailOrder.setOrderEntity(orderEntity);
            orderEntity.getDetails().add(detailOrder);
            orderJpaRepository.save(orderEntity);

            return true;
        }
        return false;
    }

    @Override
    public List<OrderDetailDto> GetDetails(Long idLounge, Long idTable) {
        Optional<OrderEntity> optionalOrder = orderJpaRepository.findByAvailableTrueAndIdLoungeAndIdTable(idLounge, idTable);
        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
        if (optionalOrder.isPresent()) {
            OrderEntity orderEntity = optionalOrder.get();
            for (DetailOrder detail: orderEntity.getDetails()) {
                OrderDetailDto dto = new OrderDetailDto();
                dto.setComment(detail.getComment());
                dto.setQuantity(detail.getQuantity());
                dto.setProduct(detail.getProduct().getName());
                dto.setId(detail.getId());
                orderDetailDtoList.add(dto);
            }
            return orderDetailDtoList;
        }
        return null;
    }


}
