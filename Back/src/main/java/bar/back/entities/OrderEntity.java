package bar.back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idLounge;
    private Long idTable;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private List<DetailOrder> details;
    private Integer quantityPeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    private Boolean available;
}
