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
@Table(name = "file")
public class FileDetails {

    @Id
    @SequenceGenerator(
            name = "file_sequence",
            sequenceName = "file_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "file_sequence"
    )
    private Long id;
    private String clientNumber;
    private String fileName;
    private String fileFormat;
    private String fileSize;
    private Date uploadedAt;


}
