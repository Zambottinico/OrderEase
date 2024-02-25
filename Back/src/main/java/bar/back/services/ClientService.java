package bar.back.services;

import bar.back.dtos.ClientDto;
import bar.back.dtos.PostClientDto;
import bar.back.dtos.PostProductoDto;
import bar.back.dtos.ProductDto;

import java.util.List;

public interface ClientService {

    ClientDto createClient(PostClientDto client);
    ClientDto editClient(ClientDto client);
    ClientDto deleteClient(Long id) throws Exception;

    ClientDto getClientById(Long id) throws Exception;
    List<ClientDto> getClients() throws Exception;
}
