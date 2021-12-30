package com.devsuperior.projetoCapitulo1.services;

import com.devsuperior.projetoCapitulo1.dto.ClientDTO;
import com.devsuperior.projetoCapitulo1.entities.Client;
import com.devsuperior.projetoCapitulo1.repositories.ClientRepository;
import com.devsuperior.projetoCapitulo1.services.exceptions.DataBaseException;
import com.devsuperior.projetoCapitulo1.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Entity not found"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> clientPage = repository.findAll(pageRequest);
        return clientPage.map(ClientDTO::new);
    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);

        }catch (EmptyResultDataAccessException erro) {
            throw new ResourceNotFoundException("Id not found " + id);

        }catch(DataIntegrityViolationException erro) {
            throw new DataBaseException("Integrity violation");
        }
    }
}
