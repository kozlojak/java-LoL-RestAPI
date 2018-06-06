package pl.jakubkozlowski.leagueoflegends.restAPI.service;

import java.util.Optional;

public interface CRUDService<DTO> {

    Optional<DTO> findById(Long id);

    DTO save(DTO championDTO);

    void update(Long id, DTO championDTO);

    void deleteById(Long id);
}
