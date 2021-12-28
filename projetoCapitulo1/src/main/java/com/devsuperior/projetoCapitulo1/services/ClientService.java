package com.devsuperior.projetoCapitulo1.services;

import com.devsuperior.projetoCapitulo1.dto.ClientDTO;
import com.devsuperior.projetoCapitulo1.entities.Client;
import com.devsuperior.projetoCapitulo1.repositories.ClientRepository;
import com.devsuperior.projetoCapitulo1.services.exceptions.ResouceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional
    public ClientDTO insert(ClientDTO clientDto){
        Client client = new Client(clientDto);
        client = repository.save(client);

        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResouceNotFoundException("Entity not found"));
        return new ClientDTO(client);
    }
}
