package com.devsuperior.Desafio3.services;

import com.devsuperior.Desafio3.dtos.requests.ClientRequestDTO;
import com.devsuperior.Desafio3.dtos.responses.ClientResponseDTO;
import com.devsuperior.Desafio3.entities.Client;
import com.devsuperior.Desafio3.repositories.ClientRepository;
import com.devsuperior.Desafio3.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAllPaged(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(client -> new ClientResponseDTO(client));
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO findById(Long id) {
        Client result = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        return new ClientResponseDTO(result);
    }

    @Transactional
    public ClientResponseDTO insert(ClientRequestDTO dto) {
        Client entity = new Client();
        copyDtoToEntity(entity, dto);
        entity = repository.save(entity);
        return new ClientResponseDTO(entity);
    }

    @Transactional
    public ClientResponseDTO update(Long id, ClientRequestDTO dto) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(entity, dto);
            entity = repository.save(entity);
            return new ClientResponseDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client not found");
        }
    }

    @Transactional
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found");
        }
        repository.deleteById(id);
    }

    private void copyDtoToEntity(Client entity, ClientRequestDTO dto) {
        entity.setName(dto.name());
        entity.setCpf(dto.cpf());
        entity.setIncome(dto.income());
        entity.setBirthDate(dto.birthDate());
        entity.setChildren(dto.children());
    }

}
