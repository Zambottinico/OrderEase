package bar.back.services;

import bar.back.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto product);
    ProductDto editProduct(ProductDto product);
    ProductDto deleteProduct(Long id) throws Exception;

    ProductDto getProductById(Long id) throws Exception;
    List<ProductDto> getProducts() throws Exception;

}
