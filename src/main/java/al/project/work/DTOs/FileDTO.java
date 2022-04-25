package al.project.work.DTOs;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private String fileName;
    private String fileContent;
    private FileContentType fileContentType;
}
