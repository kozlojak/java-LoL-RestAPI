package pl.jakubkozlowski.learning.firststeps.mapper;

import org.apache.ibatis.annotations.*;
import pl.jakubkozlowski.learning.firststeps.model.ItemPageEntity;

import java.util.List;

@Mapper
public interface ItemPageMapper {

    @Insert("INSERT INTO item_page (pagename, description, championId, roleId, userId)" +
            " VALUES (#{itemPageEntity.pagename}, #{itemPageEntity.description}, #{itemPageEntity.championId}, " +
            "#{itemPageEntity.roleId}, #{itemPageEntity.userId})")
    @Options(useGeneratedKeys = true, keyProperty = "itemPageEntity.id")
    void saveItemPage(@Param("itemPageEntity") ItemPageEntity itemPageEntity);

    @Select("SELECT * FROM item_page WHERE id= #{id}")
    ItemPageEntity findItemPageById(@Param("id") Long id);

    @Select("SELECT * FROM item_page WHERE pagename= #{pagename}")
    ItemPageEntity findItemPageByPagename(@Param("pagename") String pagename);

    @Select("SELECT * FROM item_page")
    List<ItemPageEntity> findAllItemPages();

    @Update("UPDATE item_page SET pagename=#{itemPageEntity.pagename}, description=#{itemPageEntity.description}," +
            "championId=#{itemPageEntity.championId},roleId=#{itemPageEntity.roleId}, userId=#{itemPageEntity.userId} WHERE id=#{id}")
    void updateItemPage(@Param("id") Long id, @Param("itemPageEntity") ItemPageEntity itemPageEntity);

    @Delete("DELETE FROM item_page WHERE id=#{id}")
    void deleteItemPageById(@Param("id") Long id);
}
