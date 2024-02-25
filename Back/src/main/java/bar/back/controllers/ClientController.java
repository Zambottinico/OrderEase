package bar.back.controllers;

import bar.back.dtos.ClientDto;
import bar.back.dtos.PostClientDto;
import bar.back.dtos.PostProductoDto;
import bar.back.dtos.ProductDto;
import bar.back.services.ClientService;
import bar.back.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("client/Create")
    public ResponseEntity<ClientDto> CreateProduct(@RequestBody PostClientDto dto){
        return  ResponseEntity.ok(clientService.createClient(dto));
    }

    @PutMapping("client/Edit")
    public ResponseEntity<ClientDto> editProduct(@RequestBody ClientDto dto){
        return  ResponseEntity.ok(clientService.editClient(dto));
    }

    @DeleteMapping("client/delete/{id}")
    public ResponseEntity<ClientDto> deleteProduct(@PathVariable Long id) throws Exception {
        return  ResponseEntity.ok(clientService.deleteClient(id));
    }
    @GetMapping("client/{id}")
    public ResponseEntity<ClientDto> getProduct(@PathVariable Long id) throws Exception {
        return  ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("client/")
    public ResponseEntity<List<ClientDto>> getProducts() throws Exception {
        return  ResponseEntity.ok(clientService.getClients());
    }
}
