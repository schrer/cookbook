package at.schrer.cookbook.repository;

import at.schrer.cookbook.data.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, String> {}
