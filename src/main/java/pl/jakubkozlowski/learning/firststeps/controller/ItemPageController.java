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
    public List<ItemPageDTO> findAllItemPages() {
        return itemPageService.findAllItemPages();
    }

    @PostMapping
    public ResponseEntity<ItemPageDTO> saveItemPage(@RequestBody ItemPageDTO itemPage) {
        itemPageService.saveItemPage(itemPage);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPage);
    }

    @GetMapping(value = BY_ID)
    public ResponseEntity<ItemPageDTO> findItemPageById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemPageService.findItemPageById(id));
    }

    @GetMapping(value = PAGENAME)
    public ResponseEntity<ItemPageDTO> findItemPageByPagename(@PathVariable("pagename") String pagename) {
        return ResponseEntity.ok(itemPageService.findItemPageByPagename(pagename));
    }

    @PutMapping(value = BY_ID)
    public ResponseEntity<ItemPageDTO> updateItemPage(@PathVariable("id") Long id, @RequestBody ItemPageDTO itemPage) {
        itemPageService.updateItemPage(id, itemPage);
        return ResponseEntity.status(HttpStatus.OK).body(itemPage);
    }


    @DeleteMapping(value = BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        itemPageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
