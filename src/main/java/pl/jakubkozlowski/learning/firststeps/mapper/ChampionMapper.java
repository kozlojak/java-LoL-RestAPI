package pl.jakubkozlowski.learning.firststeps.mapper;

import org.apache.ibatis.annotations.*;
import pl.jakubkozlowski.learning.firststeps.model.ChampionEntity;

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

    @Select("SELECT * FROM champion")
    List<ChampionEntity> findAll();

    @Update("UPDATE champion SET id= #{championEntity.id}, name= #{championEntity.name} WHERE id= #{id}")
    void update(@Param("id") Long id, @Param("championEntity") ChampionEntity championEntity);

    @Delete("DELETE FROM champion WHERE id=#{id}")
    void deleteById(@Param("id") Long id);
}

