package al.project.work.Services;

import al.project.work.DTOs.ClientDTO;
import al.project.work.DTOs.ClientResponse;
import al.project.work.DTOs.Status;
import al.project.work.Database.Entity.ClientDetails;
import al.project.work.Database.Repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    ObjectMapper objectMapper;

    public ClientResponse getClientDetails(String clientNumber) throws JsonProcessingException {
        ClientResponse clientResponse ;
        try {
            ClientDetails clientDetails = clientRepository.findClientDetailsByClientNumber(clientNumber);
            if (clientDetails != null) {
                ClientDTO clientDTO = ClientDTO.builder()
                        .clientNumber(clientDetails.getClientNumber())
                        .firstName(clientDetails.getFirstName())
                        .lastName(clientDetails.getLastName())
                        .birthdate(clientDetails.getBirthdate())
                        .personalNr(clientDetails.getPersonalNr())
                        .email(clientDetails.getEmail())
                        .address(clientDetails.getAddress()).build();

                clientResponse = ClientResponse.builder()
                        .status(Status.SUCCESS)
                        .statusMessage("")
                        .clientDetails(clientDTO).build();
            } else {
                clientResponse = ClientResponse.builder()
                        .status(Status.FAILED)
                        .statusMessage("Client not found")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            clientResponse = ClientResponse.builder().status(Status.FAILED).statusMessage("An exception happened please check logs").build();
        }

        LOG.info("Get Client Details Response : " + objectMapper.writeValueAsString(clientResponse));
        return clientResponse;
    }

    public ClientResponse register(ClientDTO client) throws JsonProcessingException {
        ClientResponse clientResponse ;

        try {
            if (!clientRepository.existsClientDetailsByClientNumber(client.getClientNumber())) {
                ClientDetails clientDetails = ClientDetails.builder()
                        .clientNumber(client.getClientNumber())
                        .firstName(client.getFirstName())
                        .lastName(client.getLastName())
                        .birthdate(client.getBirthdate())
                        .personalNr(client.getPersonalNr())
                        .email(client.getEmail())
                        .address(client.getAddress()).build();

                clientRepository.save(clientDetails);

                clientResponse = ClientResponse.builder()
                        .status(Status.SUCCESS)
                        .statusMessage("Client is registered successfully")
                        .clientDetails(client).build();
            }else{
                clientResponse = ClientResponse.builder()
                        .status(Status.FAILED)
                        .statusMessage("This Client Number is already registered")
                        .clientDetails(client).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            clientResponse = ClientResponse.builder().status(Status.FAILED).statusMessage("An exception happened please check logs").build();
        }
        LOG.info("Register Client Response : " + objectMapper.writeValueAsString(clientResponse));
        return clientResponse;
    }

    public ClientResponse update(ClientDTO client) throws JsonProcessingException {
        ClientResponse clientResponse ;
        try {
            ClientDetails clientDetails = ClientDetails.builder()
                    .clientNumber(client.getClientNumber())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .birthdate(client.getBirthdate())
                    .personalNr(client.getPersonalNr())
                    .email(client.getEmail())
                    .address(client.getAddress()).build();

            clientRepository.save(clientDetails);

            clientResponse = ClientResponse.builder()
                    .status(Status.SUCCESS)
                    .statusMessage("Client is updated successfully")
                    .clientDetails(client).build();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            clientResponse = ClientResponse.builder().status(Status.FAILED).statusMessage("An exception happened please check logs").build();

        }
        LOG.info("Update Client Response : " + objectMapper.writeValueAsString(clientResponse));
        return clientResponse;
    }

}
