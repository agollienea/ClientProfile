package al.project.work.Database.Repository;

import al.project.work.Database.Entity.ClientDetails;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ClientRepository extends JpaRepository<ClientDetails,String> {

    ClientDetails findClientDetailsByClientNumber(String clientNumber);

    boolean existsClientDetailsByClientNumber(String clientNumber);


}
