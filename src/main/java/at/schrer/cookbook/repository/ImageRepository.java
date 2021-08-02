package at.schrer.cookbook.repository;

import at.schrer.cookbook.data.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, String> {
    Optional<ImageEntity> findById(UUID uuid);
}
