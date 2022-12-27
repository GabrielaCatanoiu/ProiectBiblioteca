package proiect.ProiectBiblioteca.services;

import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.PublishingHouse;

import java.util.List;
import java.util.Optional;

public interface PublishingHouseServiceImpl {

    public PublishingHouseDTO addPublishingHouse(PublishingHouseDTO publishingHouseDTO);
    public List<PublishingHouse> getAllPublishingHouse();
    public Optional<PublishingHouse> getPublishById(Long id);
    public List<PublishingHouseDTO> getPublishByName(String publishing_name);
    public void deletePublising(Long id);

}
