package pl.jakubkozlowski.learning.firststeps.service;

public interface CRUDService<DTO> {

    DTO findById(Long id);

    DTO save(DTO championDTO);

    void update(Long id, DTO championDTO);

    void deleteById(Long id);
}
