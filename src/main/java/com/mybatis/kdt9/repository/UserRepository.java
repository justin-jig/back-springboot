package com.mybatis.kdt9.repository;

import com.mybatis.kdt9.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByNameOrNickname(String name, String nickname);
    // 예시
//    @Query(nativeQuery = true, value="select * from user where name=:word or nickname:word")
//    List<UserEntity> findaaaaaaaaaaaaaaa(@Param("word") String sadfsdf);

    boolean existsByName(String name);

    //
    List<UserEntity> findByName(String name);
    Optional<UserEntity> findById(int id);
    //List findByName(String name);
//    @Query("select * from user where name = :name")
    @Query("select ueee from UserEntity ueee where ueee.name = :name")
    // @Query(nativeQuery = true, value="select * from user where user.name = :name")
    UserEntity findNameByName(String name);
    // boolean ~~~~
}
