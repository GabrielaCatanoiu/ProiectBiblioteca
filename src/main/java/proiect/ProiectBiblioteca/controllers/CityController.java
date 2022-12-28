package proiect.ProiectBiblioteca.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.entity.City;
import proiect.ProiectBiblioteca.exceptions.CityNotFloundException;
import proiect.ProiectBiblioteca.services.CityService;

import java.util.List;
import java.util.Optional;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    public ResponseEntity<CityDTO> addCity(@RequestBody CityDTO cityDTO)
    {
        return ResponseEntity.ok(cityService.addCity(cityDTO));
    }

    @GetMapping("/getCity/{id}")
    public ResponseEntity<Optional<City>> getOneCity(@PathVariable Long id)
    {
        return ResponseEntity.ok(cityService.getCity(id));
    }

    @GetMapping("/getByName/{name:[a-zA-Z ]*}")
    public ResponseEntity<List<CityDTO>> getByName(@PathVariable String name)
    {
        return ResponseEntity.ok(cityService.getCityByName(name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id)
    {
        try {
            cityService.delete(id);
            return ResponseEntity.ok(String.format(CITY_WAS_DELETED,id));
        }
        catch (CityNotFloundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format(CITY_ID_NOT_FOUND, id));
        }
    }
}
