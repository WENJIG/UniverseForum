package trip.wenjig.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trip.wenjig.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
