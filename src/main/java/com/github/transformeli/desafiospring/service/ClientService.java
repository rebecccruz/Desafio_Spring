package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ClientDTO;
import com.github.transformeli.desafiospring.exception.ClientExistsException;
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

    /**
     * This method call getAllClients() in ClientRepository, change Client to ClientDTO and return list.
     * @author Isaias Finger
     *
     */
    @Override
    public List<ClientDTO> getAllClients() {
        return repo.getAllClients().stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }
    /**
     * This method call getBySate(String state) in ClientRepository, change Client to ClientDTO and return list.
     * @author: Larissa Navarro
     * @param state of client
     */
    @Override
    public List<ClientDTO> getClientsByState(String state) {
        List<Client> clientByStateList = repo.getByState(state);
        List<ClientDTO> treatedClient = clientByStateList.stream().map(ClientDTO::new).collect(Collectors.toList());
        return treatedClient;

    }

    /**
     * This method call saveClient(Client client) in ClientRepository
     * @author Isaias Finger
     * @param client client object
     */

    @Override
    public ClientDTO saveClient(Client client) {
        client.setId(getNewClientID());
        this.validateAddNewClient(client);
        return repo.saveClient(client);
    }
    /**
     * This method check if attributes is empty and call exceptions
     * @author Alexandre Borges
     * @param client client object
     */
    private void validateAddNewClient ( Client client) {
        client.setCpf(CPFDocument.getNumberOnlyCPF(client.getCpf()));
        String[] arrClientName = client.getName().split("\\s");
        if (client.getName().isEmpty()) {
            throw new InvalidClientParamException("Client name is required");
        } else if (arrClientName.length < 2) {
            throw new InvalidClientParamException("Client name need full name");
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
        else if (!CPFDocument.isValideCPF(client.getCpf())) {
            throw new InvalidClientParamException("Client CPF is not valide. CPF:" + client.getCpf());
        }
        else if (this.getClientByCPF(client)) {
            throw new ClientExistsException("Client create request (" + client.getCpf() + ") already exists.");
        }
    }

    /**
     * This method return an id to new Client
     * @author Alexandre Borges
     *
     */
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

    /**
     * Check client exist by CPF
     * @Author Alexandre Borges Souza and Isaias Finger
     * @param client client object
     * @return boolean
     */
    public Boolean getClientByCPF(Client client) {
        return (
                repo
                        .getAllClients()
                        .stream()
                        .anyMatch(
                                c -> CPFDocument.getNumberOnlyCPF(c.getCpf()).equals(CPFDocument.getNumberOnlyCPF(client.getCpf())))
                        );
    }
}
