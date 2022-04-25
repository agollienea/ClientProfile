package al.project.work.Controller;

import al.project.work.DTOs.ClientDTO;
import al.project.work.DTOs.ClientResponse;
import al.project.work.Services.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/client")
public class ClientController {

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("getDetails/{clientNumber}")
    public ResponseEntity<ClientResponse> getDetails(@PathVariable(value = "clientNumber") String clientNumber) throws JsonProcessingException {

        LOG.info("Get Details for Client Number : " + clientNumber);
        return ResponseEntity.ok().body(clientService.getClientDetails(clientNumber));
    }

    @PostMapping("registerClient")
    public ResponseEntity<ClientResponse> registerClient(@RequestBody ClientDTO client) throws JsonProcessingException {

        LOG.info("Register Client Request : " + objectMapper.writeValueAsString(client));
        return ResponseEntity.ok().body(clientService.register(client));
    }

    @PutMapping("updateClient")
    public ResponseEntity<ClientResponse> updateClient(@RequestBody ClientDTO client) throws JsonProcessingException {
        LOG.info("Update Client Request : " + objectMapper.writeValueAsString(client));
        return ResponseEntity.ok().body(clientService.update(client));
    }
}
