package bar.back.services.impl;

import bar.back.dtos.PostProductoDto;
import bar.back.dtos.ProductDto;
import bar.back.entities.Product;
import bar.back.repositories.ProductJpaRepository;
import bar.back.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductJpaRepository productJpaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ProductDto createProduct(PostProductoDto dto) {
        Product product = modelMapper.map(dto,Product.class);
        productJpaRepository.save(product);
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public ProductDto editProduct(ProductDto dto) {
        Product product = productJpaRepository.getReferenceById(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        productJpaRepository.save(product);
        dto.setId(product.getId());
        return dto;
    }

    @Override
    public ProductDto deleteProduct(Long id) throws Exception {
        Product product = productJpaRepository.getReferenceById(id);
        if (product!=null){
            productJpaRepository.deleteById(product.getId());
            return modelMapper.map(product,ProductDto.class);

        }
        throw new Exception("No existe producto con id "+ id);
    }

    @Override
    public ProductDto getProductById(Long id) throws Exception {
        Product product = productJpaRepository.getReferenceById(id);
        if (product!=null){
            ProductDto dto= modelMapper.map(product,ProductDto.class);
            return dto;
        }
        throw new Exception("No existe producto con id "+ id);
    }

    @Override
    public List<ProductDto> getProducts() throws Exception {
        List<Product> productList = productJpaRepository.findAll();
        List<ProductDto> dtoList = new ArrayList<>();
        if (productList.get(0)!=null){
            for (Product p:productList) {
                if (p!=null){
                    ProductDto dto =modelMapper.map(p,ProductDto.class);
                    dtoList.add(dto);
                }
            }
            return dtoList;
        }
        throw new Exception("No hay productos");
    }
}
