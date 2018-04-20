package pl.jakubkozlowski.learning.firststeps.mapper;

import org.apache.ibatis.annotations.*;
import pl.jakubkozlowski.learning.firststeps.model.ItemOrderEntity;

import java.util.List;

@Mapper
public interface ItemOrderMapper {

    @Insert("INSERT INTO item_order (itemPageId, itemId, itemOrder) VALUES (#{itemOrderEntity.itemPageId}," +
            "#{itemOrderEntity.itemId}, #{itemOrderEntity.itemOrder})")
    void save(@Param("itemOrderEntity") ItemOrderEntity itemOrderEntity);

    @Select("SELECT * FROM item_order WHERE itemPageId= #{id}")
    ItemOrderEntity findById(@Param("id") Long id);

    @Select("SELECT * FROM item_order")
    List<ItemOrderEntity> findAll();

    @Update("UPDATE item_order SET itemPageId=#{itemOrderEntity.itemPageId}, itemId=#{itemOrderEntity.itemId}," +
            "itemOrder=#{itemOrderEntity.itemOrder}")
    void update(@Param("id") Long id, @Param("itemOrderEntity") ItemOrderEntity itemOrderEntity);

    @Delete("DELETE FROM item_order WHERE itemPageId=#{id}")
    void deleteById(@Param("id") Long id);
}
