package pl.jakubkozlowski.learning.firststeps.mapper;

import org.apache.ibatis.annotations.*;
import pl.jakubkozlowski.learning.firststeps.model.Champion;

import java.util.List;

@Mapper
public interface ChampionMapper{

    @Insert("INSERT INTO champion VALUES ( #{id}, #{name} )")
    void addOne(@Param("id") Long id, @Param("name") String name);

    @Select("SELECT * FROM champion WHERE id= #{id}")
    Champion findOne(Long id);

    @Select("SELECT * FROM champion")
    List<Champion> findAll();

    @Update("UPDATE champion SET name= #{name} WHERE id= ${id}")
    void updateOne(@Param("name") String name, @Param("id") Long id);

    @Delete("DELETE FROM champion WHERE id=#{id}")
    void deleteOne(Long id);


    @Delete("DELETE FROM champion")
    Champion deleteAll();

}

