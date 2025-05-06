package spring_project.mapper;

import org.mapstruct.Mapper;
import spring_project.dto.RickShawRequets;
import spring_project.entity.Rickshaw;

@Mapper(componentModel = "spring")
public interface RickShawMapper {
    RickShawRequets toDTO(Rickshaw rickshaw);
    Rickshaw toEntity(RickShawRequets rickshawRequets);
}
