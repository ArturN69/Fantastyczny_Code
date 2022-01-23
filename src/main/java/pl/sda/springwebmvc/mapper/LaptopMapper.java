package pl.sda.springwebmvc.mapper;

import pl.sda.springwebmvc.entity.LaptopEntity;
import pl.sda.springwebmvc.model.Laptop;

public class LaptopMapper {

    public static LaptopEntity mapToEntity(Laptop laptop){
        return LaptopEntity.builder()
                .cpu(laptop.getCpu())
                .name(laptop.getName())
                .price(laptop.getPrice())
                .ram(laptop.getRam())
                .build();
    }
}
