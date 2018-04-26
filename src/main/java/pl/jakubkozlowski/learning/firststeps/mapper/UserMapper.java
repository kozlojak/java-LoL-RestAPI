package pl.jakubkozlowski.learning.firststeps.mapper;

import org.apache.ibatis.annotations.*;
import pl.jakubkozlowski.learning.firststeps.model.UserEntity;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (username, email, password, favoriteRoleId, favoriteChampionId)" +
            " VALUES (#{userEntity.username}, #{userEntity.email}, #{userEntity.password}, " +
            "#{userEntity.favoriteRoleId}, #{userEntity.favoriteChampionId})")
    @Options(useGeneratedKeys = true, keyProperty = "userEntity.id")
    void saveUser(@Param("userEntity") UserEntity userEntity);

    @Select("SELECT * FROM user WHERE id= #{id}")
    UserEntity findUserById(@Param("id") Long id);

    @Select("SELECT username, email, favoriteRoleId, favoriteChampionId FROM user WHERE username= #{username}")
    UserEntity findUserByUsername(@Param("username") String username);

    @Update("UPDATE user SET username=#{userEntity.username}, email=#{userEntity.email}, password=#{userEntity.password}," +
            "favoriteRoleId=#{userEntity.favoriteRoleId}, favoriteChampionId=#{userEntity.favoriteChampionId}" +
            " WHERE id= #{id}")
    void updateUser(@Param("id") Long id, @Param("userEntity") UserEntity userEntity);

    @Delete("DELETE FROM user WHERE id=#{id}")
    void deleteById(@Param("id") Long id);
}

