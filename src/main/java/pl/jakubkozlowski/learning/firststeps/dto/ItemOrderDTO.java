package pl.jakubkozlowski.learning.firststeps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderDTO {
    private Long itemPageId;
    private Long itemId;
    private Integer itemOrder;
}