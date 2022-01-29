package pl.sda.springwebmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sda.springwebmvc.entity.TagEntity;

@ResponseBody
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
