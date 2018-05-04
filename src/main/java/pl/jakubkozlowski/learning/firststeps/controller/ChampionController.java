package pl.jakubkozlowski.learning.firststeps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.learning.firststeps.dto.ChampionDTO;
import pl.jakubkozlowski.learning.firststeps.exception.ChampionException;
import pl.jakubkozlowski.learning.firststeps.service.ChampionService;

import java.util.List;

import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.ChampionControllerDescriptor.BY_ID;
import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.ChampionControllerDescriptor.CHAMPION_BASE_PATH;

@RestController
@RequestMapping(path = CHAMPION_BASE_PATH)
public class ChampionController {

    private ChampionService championService;

    @Autowired
    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping
    public ResponseEntity<List<ChampionDTO>> findAll() {
        return championService.findAll().map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(value = BY_ID)
    public ResponseEntity<ChampionDTO> findById(@PathVariable("id") Long id) throws ChampionException {
        if (championService.findById(id) == null) throw ChampionException.wrongData();
        return championService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<ChampionDTO> save(@RequestBody ChampionDTO champion) {
        championService.save(champion);
        return ResponseEntity.status(HttpStatus.CREATED).body(champion);
    }

    @PutMapping(value = BY_ID)
    public ResponseEntity<ChampionDTO> update(@PathVariable("id") Long id, @RequestBody ChampionDTO champion) throws ChampionException {
        if (championService.findById(id) == null) throw ChampionException.isNoLongerAvailable();
        championService.update(id, champion);
        return ResponseEntity.status(HttpStatus.OK).body(champion);
    }

    @DeleteMapping(value = BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) throws ChampionException {
        if (championService.findById(id) == null) throw ChampionException.doesNotExist();
        championService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}