package pl.jakubkozlowski.learning.firststeps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer favoriteRoleId;
    private Long favoriteChampionId;

    public UserEntity(String username, String email, Integer favoriteRoleId, Long favoriteChampionId) {
        this.username = username;
        this.email = email;
        this.favoriteRoleId = favoriteRoleId;
        this.favoriteChampionId = favoriteChampionId;
    }
}
