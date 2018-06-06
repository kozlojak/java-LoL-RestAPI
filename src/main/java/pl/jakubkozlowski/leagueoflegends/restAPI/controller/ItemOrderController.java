package pl.jakubkozlowski.leagueoflegends.restAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.leagueoflegends.restAPI.controller.descriptor.ItemOrderDescriptor;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.ItemOrderDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.service.ItemOrderService;

import java.util.List;

@RestController
@RequestMapping(path = ItemOrderDescriptor.ITEM_ORDER_BASE_PATH)
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

    @GetMapping(value = ItemOrderDescriptor.BY_ID)
    public ResponseEntity<ItemOrderDTO> findById(@PathVariable("id") Long id) {
        return itemOrderService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping(value = ItemOrderDescriptor.BY_ID)
    public ResponseEntity<ItemOrderDTO> update(@PathVariable("id") Long id, @RequestBody ItemOrderDTO itemOrder) {
        itemOrderService.update(id, itemOrder);
        return ResponseEntity.status(HttpStatus.OK).body(itemOrder);
    }


    @DeleteMapping(value = ItemOrderDescriptor.BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        itemOrderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
