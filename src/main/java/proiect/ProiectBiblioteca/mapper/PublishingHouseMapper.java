package proiect.ProiectBiblioteca.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.PublishingHouse;

@Component
public class PublishingHouseMapper {

    public PublishingHouse mapToPublishingHouse(PublishingHouseDTO publishingHouseDTO)
    {
        return PublishingHouse.builder()
                .publishing_name(publishingHouseDTO.getPublishing_name())
                .build();
    }

    public PublishingHouseDTO mapToPublishingHouseDTO(PublishingHouse publishingHouse)
    {
        return PublishingHouseDTO.builder()
                .id(publishingHouse.getId())
                .publishing_name(publishingHouse.getPublishing_name())
                .build();
    }
}
