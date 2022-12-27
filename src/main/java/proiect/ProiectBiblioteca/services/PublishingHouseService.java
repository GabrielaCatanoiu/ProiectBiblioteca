package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.City;
import proiect.ProiectBiblioteca.entity.PublishingHouse;
import proiect.ProiectBiblioteca.exceptions.CityNotFloundException;
import proiect.ProiectBiblioteca.exceptions.PublishingHouseNotFoundException;
import proiect.ProiectBiblioteca.mapper.PublishingHouseMapper;
import proiect.ProiectBiblioteca.repositories.CityRepository;
import proiect.ProiectBiblioteca.repositories.PublishingHouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@Service
@RequiredArgsConstructor
public class PublishingHouseService implements PublishingHouseServiceImpl{

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;
    @Autowired
    private PublishingHouseMapper publishingHouseMapper;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public PublishingHouseDTO addPublishingHouse(PublishingHouseDTO publishingHouseDTO)
    {
        PublishingHouse publishingHouse = publishingHouseMapper.mapToPublishingHouse(publishingHouseDTO);
        if(publishingHouseDTO.getCityDTO()!= null)
        {
            Optional<City> city = cityRepository.findById(publishingHouseDTO.getCityDTO().getId());
            if (city.isEmpty())
            {
                throw new CityNotFloundException(String.format(CITY_NOT_FOUND,publishingHouse.getCity()));
            }
            publishingHouse.setCity(city.get());
        }
        return publishingHouseMapper.mapToPublishingHouseDTO(publishingHouseRepository.save(publishingHouse));
    }

    @Override
    public List<PublishingHouse> getAllPublishingHouse()
    {
        List<PublishingHouse> publishingHouses = new ArrayList<>();
        for (int i=0; i< publishingHouseRepository.findAll().size(); i++)
        {
            publishingHouses.add(publishingHouseRepository.findAll().get(i));
        }
        return publishingHouses;
    }

    @Override
    public Optional<PublishingHouse> getPublishById(Long id)
    {
        return publishingHouseRepository.findById(id);
    }

    @Override
    public List<PublishingHouseDTO> getPublishByName(String publishing_name)
    {
        List<PublishingHouseDTO> publishingHouseDTOS = publishingHouseRepository.findByPublishing_name(publishing_name).stream()
                .map(publish -> publishingHouseMapper.mapToPublishingHouseDTO(publish)).collect(Collectors.toList());
        if (publishingHouseDTOS.isEmpty())
        {
            throw new PublishingHouseNotFoundException(String.format(PUBLISHING_HOUSE_NOT_FOUND,publishing_name));
        }
        return publishingHouseDTOS;
    }

    @Override
    public void deletePublising(Long id)
    {
        Optional<PublishingHouse> publishingHouseFound = publishingHouseRepository.findById(id);
        if (publishingHouseFound.isPresent())
        {
            publishingHouseRepository.delete(publishingHouseFound.get());
        }
        else
        {
            throw new PublishingHouseNotFoundException(String.format(PUBLISHING_HOUSE_ID_NOT_FOUND,id));
        }
    }

}
