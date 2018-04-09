package pl.jakubkozlowski.learning.firststeps.mapper;

import org.apache.ibatis.annotations.*;
import pl.jakubkozlowski.learning.firststeps.model.Champion;

import java.util.List;

@Mapper
public interface ChampionMapper{


    @Insert("INSERT INTO champion VALUES ( #{champion.id}, #{champion.name} )")
    void create(@Param("champion") Champion champion);

    @Select("SELECT * FROM champion WHERE id= #{id}")
    Champion findOne(@Param("id") Long id);

    @Select("SELECT * FROM champion")
    List<Champion> findAll();

    @Update("UPDATE champion SET id= #{champion.id}, name= #{champion.name} WHERE id= #{id}")
    void update(@Param("id") Long a, @Param("champion") Champion champion);

    @Delete("DELETE FROM champion WHERE id=#{id}")
    void deleteById(@Param("id") Long id);


}

