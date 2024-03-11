package bar.back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;
}
