package pl.jakubkozlowski.learning.firststeps.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    private List<T> content;
    private Integer page;
    private Long totalCount;


    public static <T> Page<T> empty() {
        return new Page<>(new ArrayList<T>(), 0, 0L);
    }
}
