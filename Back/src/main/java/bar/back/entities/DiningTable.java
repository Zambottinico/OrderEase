package bar.back.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dining_tables")
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private DiningTableState state;
    private Integer people;
    private Long idClient;

}
