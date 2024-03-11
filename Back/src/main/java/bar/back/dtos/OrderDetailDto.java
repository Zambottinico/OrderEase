package bar.back.dtos;

import bar.back.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderDetailDto {
    private Long id;
    private String product;
    private Integer quantity;
    private String comment;
}
