package com.devsuperior.Desafio3.controllers;

import com.devsuperior.Desafio3.dtos.requests.ClientRequestDTO;
import com.devsuperior.Desafio3.dtos.responses.ClientResponseDTO;
import com.devsuperior.Desafio3.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> findAllPaged(Pageable pageable) {
        Page<ClientResponseDTO> clients = service.findAllPaged(pageable);
        return ResponseEntity.ok(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        ClientResponseDTO client = service.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> insert(@Valid @RequestBody ClientRequestDTO requestDTO) {
        ClientResponseDTO responseDTO = service.insert(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientResponseDTO> updateById(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO requestDTO) {
        ClientResponseDTO responseDTO = service.update(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
