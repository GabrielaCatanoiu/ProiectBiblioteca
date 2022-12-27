package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.PublishingHouse;
import proiect.ProiectBiblioteca.exceptions.PublishingHouseNotFoundException;
import proiect.ProiectBiblioteca.mapper.PublishingHouseMapper;
import proiect.ProiectBiblioteca.repositories.PublishingHouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.PUBLISHING_HOUSE_ID_NOT_FOUND;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.PUBLISHING_HOUSE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PublishingHouseService implements PublishingHouseServiceImpl{

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;
    @Autowired
    private PublishingHouseMapper publishingHouseMapper;

    @Override
    public PublishingHouseDTO addPublishingHouse(PublishingHouseDTO publishingHouseDTO)
    {
        return publishingHouseMapper.mapToPublishingHouseDTO(publishingHouseRepository.save(publishingHouseMapper.mapToPublishingHouse(publishingHouseDTO)));
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
