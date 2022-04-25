package al.project.work.Services;

import al.project.work.DTOs.ClientResponse;
import al.project.work.DTOs.FileDTO;
import al.project.work.DTOs.FileResponse;
import al.project.work.DTOs.Status;
import al.project.work.Database.Entity.FileDetails;
import al.project.work.Database.Repository.ClientRepository;
import al.project.work.Database.Repository.FileRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

@Service
public class FileService {

    private static final Logger LOG = LoggerFactory.getLogger(FileService.class);


    @Value("${file.dir}")
    String fileDir;

    @Autowired
    private FileRepository fileRepository;


    public FileResponse upload(String clientNumber,FileDTO fileDTO){
        FileResponse fileResponse;

        try{
            File newFile = new File(fileDir+fileDTO.getFileName()+"."+fileDTO.getFileContentType().toString().toLowerCase());

            byte [] byteContent = Base64.decodeBase64(fileDTO.getFileContent());

            OutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(byteContent);
            outputStream.flush();
            outputStream.close();

            String fileSizeKB = Math.round(newFile.length()/1024) + " KB";

            FileDetails fileDetails = FileDetails.builder()
                    .clientNumber(clientNumber)
                    .fileFormat(fileDTO.getFileContentType().toString().toLowerCase())
                    .fileName(fileDTO.getFileName())
                    .fileSize(fileSizeKB)
                    .uploadedAt(new Date()).build();

            FileDetails savedFile = fileRepository.save(fileDetails);

            fileResponse = FileResponse.builder()
                    .status(Status.SUCCESS)
                    .statusMessage("File Uploaded Successfully with id " + savedFile.getId()).build();

        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            fileResponse = FileResponse.builder().status(Status.FAILED).statusMessage("An exception happened please check logs").build();

        }
        return fileResponse;
    }
}
