package com.github.transformeli.desafiospring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.transformeli.desafiospring.dto.ClientDTO;
import com.github.transformeli.desafiospring.exception.ClientExistsException;
import com.github.transformeli.desafiospring.exception.InternalServerException;
import com.github.transformeli.desafiospring.exception.NotFoundException;
import com.github.transformeli.desafiospring.model.Client;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {

    private final String linkFile = "src/main/resources/clients.json";

    /**
     * Get all clients, this method read the file Json.
     * @author Isaias Finger
     */
    public List<Client> getAllClients() {
        return this.readFile();
    }
    /**
     * Save clients, this method add the client in the list.
     * @author Isaias Finger
     * @param client  client object
     */
    public ClientDTO saveClient(Client client) {
        List<Client> clients = this.getAllClients();
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Client> newClients = new ArrayList<>(clients);
            newClients.add(client);
            mapper.writeValue(new File(linkFile), newClients);
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
        return new ClientDTO(client);
    }

    /**
     * Return client if state is equal parameter.
     * @author Isaias Finger
     * @param state stage of client
     */
    public List<Client> getByState(String state){
        try{
            List<Client> clientList = this.readFile();
            return clientList.stream()
                    .filter(c -> c.getState().equalsIgnoreCase(state))
                    .collect(Collectors.toList());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        throw new NotFoundException("Couldn`t read file");
    }

    /**
     * This method read the file json.
     * @author Isaias Finger
     */
    private List<Client> readFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<Client> list = new ArrayList<>();
        try {
            list = Arrays.asList(mapper.readValue(new File(linkFile), Client[].class));
        } catch (Exception ex) {
        }
        return list;
    }

}
