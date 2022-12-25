package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.entity.City;
import proiect.ProiectBiblioteca.exceptions.CityNotFloundException;
import proiect.ProiectBiblioteca.mapper.CityMapper;
import proiect.ProiectBiblioteca.repositories.CityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.CITY_ID_NOT_FOUND;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.CITY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CityService implements CityServiceImpl{

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityMapper cityMapper;

    @Override
    public CityDTO addCity(CityDTO cityDTO)
    {
        return cityMapper.mapToCityDTO(cityRepository.save(cityMapper.mapToCity(cityDTO)));
    }

    @Override
    public Optional<City> getCity(Long id)
    {
        return cityRepository.findById(id);
    }

    @Override
    public List<CityDTO> getCityByName(String city_name)
    {
        List<CityDTO> cityDTOS = cityRepository.findCityByCity_name(city_name).stream()
                .map(name -> cityMapper.mapToCityDTO(name)).collect(Collectors.toList());
        if (cityDTOS.isEmpty())
        {
            throw new CityNotFloundException(String.format(CITY_NOT_FOUND,city_name));
        }
        return cityDTOS;
    }

    @Override
    public void deleteCity(Long id)
    {
        Optional<City> cityFound = cityRepository.findById(id);
        if(cityFound.isPresent())
        {
            cityRepository.delete(cityFound.get());
        }
        else
        {
            throw new CityNotFloundException(String.format(CITY_ID_NOT_FOUND,id));
        }
    }
}
