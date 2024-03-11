package bar.back.dtos;

import lombok.Data;

@Data
public class DetailDto {
    private Long idLounge;
    private Long idTable;
    private Long idProduct;
    private Integer quantity;
    private String comment;



}
