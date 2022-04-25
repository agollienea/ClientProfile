package al.project.work.Database.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class ClientDetails {
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long id;
    @Id
    private String clientNumber;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String personalNr;
    private String email;
    private String address;

}
