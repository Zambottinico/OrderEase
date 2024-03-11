package bar.back.dtos;

import bar.back.entities.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDto {
    private Long id;
    private String product;
    private Integer quantity;
    private String comment;
    private BigDecimal subtotal;
}
