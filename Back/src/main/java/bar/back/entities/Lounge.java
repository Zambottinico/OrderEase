package bar.back.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Data
@Entity
@Table(name = "lounges")
public class Lounge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(length = 99999)
    private String tableroDeMesasJSON;

    @Transient
    private DiningTable[][] tableroDeMesas;
}
