package pl.jakubkozlowski.leagueoflegends.restAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.leagueoflegends.restAPI.controller.descriptor.ChampionControllerDescriptor;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ChampionDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.exception.ChampionException;
import pl.jakubkozlowski.leagueoflegends.restAPI.service.ChampionService;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Page;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Pageable;

@RestController
@RequestMapping(path = ChampionControllerDescriptor.CHAMPION_BASE_PATH)
public class ChampionController {

    private ChampionService championService;

    @Autowired
    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<ChampionDTO>> findPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Pageable pageable = new Pageable(page, size);
        return championService.findPage(pageable).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(value = ChampionControllerDescriptor.BY_ID)
    public ResponseEntity<ChampionDTO> findById(@PathVariable("id") Long id) throws ChampionException {
        if (championService.findById(id) == null) throw ChampionException.wrongData();
        return championService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<ChampionDTO> save(@RequestBody ChampionDTO champion) {
        championService.save(champion);
        return ResponseEntity.status(HttpStatus.CREATED).body(champion);
    }

    @PutMapping(value = ChampionControllerDescriptor.BY_ID)
    public ResponseEntity<ChampionDTO> update(@PathVariable("id") Long id, @RequestBody ChampionDTO champion) throws ChampionException {
        if (championService.findById(id) == null) throw ChampionException.isNoLongerAvailable();
        championService.update(id, champion);
        return ResponseEntity.status(HttpStatus.OK).body(champion);
    }

    @DeleteMapping(value = ChampionControllerDescriptor.BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) throws ChampionException {
        if (championService.findById(id) == null) throw ChampionException.doesNotExist();
        championService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}