package al.project.work.Database.Repository;

import al.project.work.Database.Entity.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileDetails,Long> {
}
