package pl.sda.springwebmvc.service;

import org.springframework.stereotype.Service;
import pl.sda.springwebmvc.entity.LaptopEntity;
import pl.sda.springwebmvc.mapper.LaptopMapper;
import pl.sda.springwebmvc.model.Laptop;
import pl.sda.springwebmvc.repository.LaptopRepository;

import java.util.List;

@Service
public class LaptopService {
    private final LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public LaptopEntity save(Laptop laptop){
        return laptopRepository.save(LaptopMapper.mapToEntity(laptop));
    }

    public List<LaptopEntity> findAll(){
        return laptopRepository.findAll();
    }
}
