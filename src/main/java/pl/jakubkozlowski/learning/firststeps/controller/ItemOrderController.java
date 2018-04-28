package pl.jakubkozlowski.learning.firststeps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.learning.firststeps.dto.ItemOrderDTO;
import pl.jakubkozlowski.learning.firststeps.service.ItemOrderService;

import java.util.List;

import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.ItemOrderDescriptor.BY_ID;
import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.ItemOrderDescriptor.ITEM_ORDER_BASE_PATH;

@RestController
@RequestMapping(path = ITEM_ORDER_BASE_PATH)
public class ItemOrderController {

    private ItemOrderService itemOrderService;

    @Autowired
    public ItemOrderController(ItemOrderService itemOrderService) {
        this.itemOrderService = itemOrderService;
    }

    @GetMapping
    public ResponseEntity<List<ItemOrderDTO>> findAll() {
        return itemOrderService.findAll().map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping
    public ResponseEntity<ItemOrderDTO> save(@RequestBody ItemOrderDTO itemOrder) {
        itemOrderService.save(itemOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemOrder);
    }

    @GetMapping(value = BY_ID)
    public ResponseEntity<ItemOrderDTO> findById(@PathVariable("id") Long id) {
        return itemOrderService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping(value = BY_ID)
    public ResponseEntity<ItemOrderDTO> update(@PathVariable("id") Long id, @RequestBody ItemOrderDTO itemOrder) {
        itemOrderService.update(id, itemOrder);
        return ResponseEntity.status(HttpStatus.OK).body(itemOrder);
    }


    @DeleteMapping(value = BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        itemOrderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
