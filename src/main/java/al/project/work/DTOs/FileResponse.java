package al.project.work.DTOs;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {

    private Status status;
    private String statusMessage;
}
