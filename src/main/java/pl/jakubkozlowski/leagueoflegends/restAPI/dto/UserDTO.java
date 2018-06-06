package pl.jakubkozlowski.leagueoflegends.restAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer favoriteRoleId;
    private Long favoriteChampionId;

    public UserDTO(String username, String email, Integer favoriteRoleId, Long favoriteChampionId) {
        this.username = username;
        this.email = email;
        this.favoriteRoleId = favoriteRoleId;
        this.favoriteChampionId = favoriteChampionId;
    }
}
