package bar.back.dtos;

import lombok.Data;

@Data
public class OccupyTableDto {
    private Long id;
    private Long idLounge;
    private Integer people;
    private Long idClient;
}
