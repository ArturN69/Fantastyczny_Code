package pl.sda.springwebmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.springwebmvc.entity.TagEntity;

import java.util.Optional;

@ResponseBody
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    //Zdefiniuj metodę do wyszukiwania tagu po polu label
    TagEntity findTagEntityByLabel(String label);
}
