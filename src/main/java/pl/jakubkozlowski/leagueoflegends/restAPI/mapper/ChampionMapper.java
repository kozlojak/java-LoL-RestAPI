package pl.jakubkozlowski.leagueoflegends.restAPI.mapper;

import org.apache.ibatis.annotations.*;
import pl.jakubkozlowski.leagueoflegends.restAPI.model.ChampionEntity;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Page;
import pl.jakubkozlowski.leagueoflegends.restAPI.shared.Pageable;

import java.util.List;

@Mapper
public interface ChampionMapper {


    @Insert("INSERT INTO champion (name) VALUES (#{championEntity.name})")
    @Options(useGeneratedKeys = true, keyProperty = "championEntity.id")
    void save(@Param("championEntity") ChampionEntity championEntity);

    @Select("SELECT * FROM champion WHERE id= #{id}")
    ChampionEntity findById(@Param("id") Long id);

    @Select("SELECT * FROM champion WHERE name= #{name}")
    ChampionEntity findByName(@Param("name") String name);

    @Select("SELECT * FROM champion LIMIT #{pageable.limit} OFFSET #{pageable.offset}")
    List<ChampionEntity> findWithPageable(@Param("pageable") Pageable pageable);

    @Select("SELECT COUNT(*) FROM champion")
    Long getCount();

    default Page<ChampionEntity> findPage(Pageable pageable) {
        Long totalCount = getCount();
        if (totalCount == 0) {
            return Page.empty();
        }
        List<ChampionEntity> content = findWithPageable(pageable);
        return new Page<>(content, pageable.getOffset() / pageable.getLimit(), totalCount);
    }

    @Update("UPDATE champion SET id= #{championEntity.id}, name= #{championEntity.name} WHERE id= #{id}")
    void update(@Param("id") Long id, @Param("championEntity") ChampionEntity championEntity);

    @Delete("DELETE FROM champion WHERE id=#{id}")
    void deleteById(@Param("id") Long id);
}

