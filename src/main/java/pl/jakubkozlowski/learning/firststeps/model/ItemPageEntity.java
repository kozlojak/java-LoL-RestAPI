package pl.jakubkozlowski.learning.firststeps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPageEntity {
    private Long id;
    private String pagename;
    private String description;
    private Long championId;
    private Integer roleId;
    private Long userId;
}
