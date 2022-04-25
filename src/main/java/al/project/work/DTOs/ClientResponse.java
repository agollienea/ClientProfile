package al.project.work.DTOs;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    private Status status;
    private String statusMessage;
    private ClientDTO clientDetails;

}
