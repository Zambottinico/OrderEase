package bar.back.dtos;

import bar.back.entities.DiningTable;
import bar.back.entities.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class PutLoungeDto {
    private Long id;
    private String name;
    private Restaurant restaurant;
    private DiningTable[][] tableroDeMesas;
}
