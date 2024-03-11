package bar.back.services.impl;

import bar.back.dtos.ClientDto;
import bar.back.dtos.PostClientDto;
import bar.back.dtos.ProductDto;
import bar.back.entities.Client;
import bar.back.entities.Product;
import bar.back.repositories.ClientJpaRepository;
import bar.back.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientJpaRepository clientJpaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ClientDto createClient(PostClientDto dto) {
        Client client = modelMapper.map(dto,Client.class);
        clientJpaRepository.save(client);
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    public ClientDto editClient(ClientDto dto) {
        Client client = clientJpaRepository.getReferenceById(dto.getId());
        client.setName(dto.getName());
        client.setLastName(dto.getLastName());
        clientJpaRepository.save(client);
        dto.setId(client.getId());
        return dto;
    }

    @Override
    public ClientDto deleteClient(Long id) throws Exception {
        Client client = clientJpaRepository.getReferenceById(id);
        if (client!=null){
            clientJpaRepository.deleteById(client.getId());
            return modelMapper.map(client,ClientDto.class);

        }
        throw new Exception("No existe client con id "+ id);
    }

    @Override
    public ClientDto getClientById(Long id) throws Exception {
        Client client = clientJpaRepository.getReferenceById(id);
        if (client!=null){
            return modelMapper.map(client,ClientDto.class);
        }
        return null;
    }

    @Override
    public List<ClientDto> getClients() throws Exception {
        List<Client> clientList = clientJpaRepository.findAll();
        List<ClientDto> dtoList = new ArrayList<>();
        if (!clientList.isEmpty()){
            for (Client p:clientList) {
                if (p!=null){
                    ClientDto dto =modelMapper.map(p,ClientDto.class);
                    dtoList.add(dto);
                }
            }
            return dtoList;
        }
         return null;
    }
}
