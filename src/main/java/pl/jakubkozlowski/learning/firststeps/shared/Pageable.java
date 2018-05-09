package pl.jakubkozlowski.learning.firststeps.shared;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Pageable {

    private int limit;
    private int offset;

    public Pageable(int page, int size) {
        this.limit = size;
        this.offset = page * size;
    }
}
