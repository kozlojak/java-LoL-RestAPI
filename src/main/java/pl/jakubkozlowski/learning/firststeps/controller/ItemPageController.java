package pl.jakubkozlowski.learning.firststeps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.learning.firststeps.dto.ItemPageDTO;
import pl.jakubkozlowski.learning.firststeps.service.ItemPageService;

import java.util.List;

import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.ItemPageDescriptor.*;

@RestController
@RequestMapping(path = ITEM_PAGE_BASE_PATH)
public class ItemPageController {

    private ItemPageService itemPageService;

    @Autowired
    public ItemPageController(ItemPageService itemPageService) {
        this.itemPageService = itemPageService;
    }

    @GetMapping
    public ResponseEntity<List<ItemPageDTO>> findAll() {
        return itemPageService.findAll().map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<ItemPageDTO> save(@RequestBody ItemPageDTO itemPage) {
        itemPageService.save(itemPage);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPage);
    }

    @GetMapping(value = BY_ID)
    public ResponseEntity<ItemPageDTO> findById(@PathVariable("id") Long id) {
        return itemPageService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(value = PAGENAME)
    public ResponseEntity<ItemPageDTO> findByPagename(@PathVariable("pagename") String pagename) {
        return itemPageService.findByPagename(pagename).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping(value = BY_ID)
    public ResponseEntity<ItemPageDTO> update(@PathVariable("id") Long id, @RequestBody ItemPageDTO itemPage) {
        itemPageService.update(id, itemPage);
        return ResponseEntity.status(HttpStatus.OK).body(itemPage);
    }


    @DeleteMapping(value = BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        itemPageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
