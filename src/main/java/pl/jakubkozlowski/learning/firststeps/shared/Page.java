package pl.jakubkozlowski.learning.firststeps.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public <U> Page<U> map(Function<T, U> converter) {
        return new Page<>(content.stream().map(converter).collect(Collectors.toList()), page, totalCount);
    }
}
