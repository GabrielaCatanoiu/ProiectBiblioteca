package proiect.ProiectBiblioteca.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.PublishingHouse;

@Component
public class PublishingHouseMapper {

    @Autowired
    private CityMapper cityMapper;

    public PublishingHouse mapToPublishingHouse(PublishingHouseDTO publishingHouseDTO)
    {
        if(publishingHouseDTO.getCityDTO()!= null)
        {
            return PublishingHouse.builder()
                    .id(publishingHouseDTO.getId())
                    .publishing_name(publishingHouseDTO.getPublishing_name())
                    .city(cityMapper.mapToCity(publishingHouseDTO.getCityDTO()))
                    .build();
        }
        else
        {
            return PublishingHouse.builder()
                    .id(publishingHouseDTO.getId())
                    .publishing_name(publishingHouseDTO.getPublishing_name())
                    .build();
        }

    }

    public PublishingHouseDTO mapToPublishingHouseDTO(PublishingHouse publishingHouse)
    {
        if (publishingHouse.getCity()!= null)
        {
            return PublishingHouseDTO.builder()
                    .id(publishingHouse.getId())
                    .publishing_name(publishingHouse.getPublishing_name())
                    .cityDTO(cityMapper.mapToCityDTO(publishingHouse.getCity()))
                    .build();
        }
        else
        {
            return PublishingHouseDTO.builder()
                    .id(publishingHouse.getId())
                    .publishing_name(publishingHouse.getPublishing_name())
                    .build();
        }
    }
}
