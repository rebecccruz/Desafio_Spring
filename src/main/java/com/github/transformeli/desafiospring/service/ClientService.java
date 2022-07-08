package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ClientDTO;
import com.github.transformeli.desafiospring.exception.InvalidClientParamException;
import com.github.transformeli.desafiospring.helper.CPFDocument;
import com.github.transformeli.desafiospring.model.Client;
import com.github.transformeli.desafiospring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository repo;

    @Override
    public List<ClientDTO> getAllClients() {
        return repo.getAllClients().stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> getClientsByState(String state) {
        return this.getAllClients(); // TODO
    }

    @Override
    public ClientDTO saveClient(Client client) {
        client.setId(getNewClientID());
        this.validateAddNewClient(client);
        return repo.saveClient(client);
    }

    private void validateAddNewClient ( Client client) {
        if (client.getName().isEmpty()) {
            throw new InvalidClientParamException("Client name is required");
        }

        if (client.getCountry().isEmpty()) {
            throw new InvalidClientParamException("Client country is required");
        }

        if (client.getState().isEmpty()) {
            throw new InvalidClientParamException("Client state is required");
        }

        if (client.getCpf().isEmpty()) {
            throw new InvalidClientParamException("Client CPF is required");
        }
        if (!CPFDocument.isValideCPF(client.getCpf())) {
            throw new InvalidClientParamException("Client CPF is not valide. CPF:" + client.getCpf());
        }
    }

    private Integer getNewClientID () {
        Integer id = 0;

        if (repo.getAllClients().size()>0) {
            Client client = repo
                    .getAllClients()
                    .stream()
                    .max(Comparator.comparing(Client::getId)).orElseThrow(NoSuchElementException::new);
            id = (client.getId());
        }
        return ++id;
    }
}
