package pl.jakubkozlowski.learning.firststeps.mapper;

import org.apache.ibatis.annotations.*;
import pl.jakubkozlowski.learning.firststeps.model.Champion;

import java.util.List;

@Mapper
public interface ChampionMapper{


    @Insert("INSERT INTO champion (name) VALUES (#{champion.name})")
    @Options(useGeneratedKeys = true, keyProperty = "champion.id")
    void persist(@Param("champion") Champion champion);

    @Select("SELECT * FROM champion WHERE id= #{id}")
    Champion findById(@Param("id") Long id);

    @Select("SELECT * FROM champion WHERE name= #{name}")
    Champion findByName(@Param("name") String name);

    @Select("SELECT * FROM champion")
    List<Champion> findAll();

    @Update("UPDATE champion SET id= #{champion.id}, name= #{champion.name} WHERE id= #{id}")
    void update(@Param("id") Long entity_id, @Param("champion") Champion champion);

    @Delete("DELETE FROM champion WHERE id=#{id}")
    void deleteById(@Param("id") Long id);
}

