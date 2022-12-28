package proiect.ProiectBiblioteca.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.PublishingHouse;
import proiect.ProiectBiblioteca.exceptions.PublishingHouseNotFoundException;
import proiect.ProiectBiblioteca.services.PublishingHouseService;

import java.util.List;
import java.util.Optional;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.PUBLISHING_HOUSE_NOT_FOUND;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.PUBLISHING_HOUSE_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publishingHouse")
public class PublishingHouseController {

    @Autowired
    private PublishingHouseService publishingHouseService;

    @PostMapping("/add")
    public ResponseEntity<PublishingHouseDTO> addPublishHouse(@RequestBody PublishingHouseDTO publishingHouseDTO)
    {
        return ResponseEntity.ok(publishingHouseService.addPublishingHouse(publishingHouseDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PublishingHouse>> getAllPublishHouse()
    {
        return ResponseEntity.ok(publishingHouseService.getAllPublishingHouse());
    }

    @GetMapping("/getPublishHouseById/{id}")
    public ResponseEntity<Optional<PublishingHouse>> getPublishHouseById(@PathVariable Long id)
    {
        return ResponseEntity.ok(publishingHouseService.getPublishById(id));
    }

    @GetMapping("/getPublishHouseByName/{publishing_name}")
    public ResponseEntity<List<PublishingHouseDTO>> getPublishHouseByName(@PathVariable String publishing_name)
    {
        return ResponseEntity.ok(publishingHouseService.getPublishByName(publishing_name));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePublishHouse(@PathVariable Long id)
    {
        try {
            publishingHouseService.delete(id);
            return ResponseEntity.ok(String.format(PUBLISHING_HOUSE_WAS_DELETED,id));
        }
        catch (PublishingHouseNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format(PUBLISHING_HOUSE_NOT_FOUND,id));
        }
    }
}
