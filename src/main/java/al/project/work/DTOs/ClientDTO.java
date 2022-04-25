package al.project.work.DTOs;

import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private String clientNumber;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String personalNr;
    private String email;
    private String address;
}
