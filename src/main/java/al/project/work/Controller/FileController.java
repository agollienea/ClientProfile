package al.project.work.Controller;

import al.project.work.DTOs.ClientDTO;
import al.project.work.DTOs.ClientResponse;
import al.project.work.DTOs.FileDTO;
import al.project.work.DTOs.FileResponse;
import al.project.work.Services.ClientService;
import al.project.work.Services.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/file")
public class FileController {
    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;


    @PostMapping("uploadProfilePhoto/{clientNumber}")
    public ResponseEntity<FileResponse> registerClient(@PathVariable(value = "clientNumber") String clientNumber,
                                                       @RequestBody FileDTO file){

        LOG.info("Upload Profile Photo for Client : " + clientNumber);
        return ResponseEntity.ok().body(fileService.upload(clientNumber,file));
    }
}
