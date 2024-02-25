package bar.back.dtos;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PostProductoDto {
    private String name;
    private BigDecimal price;
}
