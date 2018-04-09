package pl.jakubkozlowski.learning.firststeps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.learning.firststeps.mapper.ChampionMapper;
import pl.jakubkozlowski.learning.firststeps.model.Champion;

import java.util.List;

import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.ChampionControllerDescriptor.BASE_PATH;

@RestController
@RequestMapping(path = BASE_PATH)
public class ChampionController {

    private ChampionMapper championMapper;

    @Autowired
    public ChampionController(ChampionMapper championMapper) {
        this.championMapper = championMapper;
    }

    @GetMapping
    public List<Champion> findAll() {
        return championMapper.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Champion> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(championMapper.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Champion> create(@RequestBody Champion champion) {
         championMapper.create(champion);
         return ResponseEntity.status(HttpStatus.CREATED).body(champion);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Champion> update(@PathVariable("id") Long id, @RequestBody Champion champion) {
        championMapper.update(id, champion);
        return ResponseEntity.status(HttpStatus.OK).body(champion);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        championMapper.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}