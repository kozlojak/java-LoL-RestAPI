package pl.jakubkozlowski.learning.firststeps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.learning.firststeps.model.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.service.ChampionService;
import pl.jakubkozlowski.learning.firststeps.service.ChampionServiceImpl;

import java.util.List;

import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.ChampionControllerDescriptor.BASE_PATH;
import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.ChampionControllerDescriptor.BY_ID;

@RestController
@RequestMapping(path = BASE_PATH)
public class ChampionController {

    private ChampionService championService;
    public ChampionController(ChampionService championService){
        this.championService= championService;
    }

    @GetMapping
    public List<ChampionDTO> findAll() {
        return championService.findAll();
    }

    @GetMapping(value = BY_ID)
    public ResponseEntity<ChampionDTO> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(championService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ChampionDTO> persist(@RequestBody ChampionDTO champion) {
         championService.persist(champion);
         return ResponseEntity.status(HttpStatus.CREATED).body(champion);
    }

    @PutMapping(value = BY_ID)
    public ResponseEntity<ChampionDTO> update(@PathVariable("id") Long id, @RequestBody ChampionDTO champion) {
        championService.update(id, champion);
        return ResponseEntity.status(HttpStatus.OK).body(champion);
    }

    @DeleteMapping(value = BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        championService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}