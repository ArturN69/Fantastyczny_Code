package pl.sda.springwebmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.springwebmvc.entity.LaptopEntity;

@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Long> {
}
