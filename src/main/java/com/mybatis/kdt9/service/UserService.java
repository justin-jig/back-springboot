package com.mybatis.kdt9.service;

import com.mybatis.kdt9.domain.User;
import com.mybatis.kdt9.domain.UserEntity;
import com.mybatis.kdt9.dto.UserDTO;
import com.mybatis.kdt9.dto.UserDTOBuilder;
import com.mybatis.kdt9.mapper.UserMapper;
import com.mybatis.kdt9.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    // mapper을 실행하고 그에 대한 결과값을 dto에 담아서 controller로 return
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRepository userRepository;
    public String insertUser(String name, String nickname) {
        UserEntity user = UserEntity.builder().name(name).nickname(nickname).build();
        UserEntity newUser = userRepository.save(user); 
        // save를 했을 때 반환되는 객체는 Entity 객체
        return newUser.getName();
    }
    public int searchNameOrNickname(String word) {
        List<UserEntity> result = userRepository.findByNameOrNickname(word, word);
        return result.size();
    }
    public boolean checkUser(String name) {
        return userRepository.existsByName(name);
    }
    public String searchId(int id){
        // orElse Optional.orElse()
        UserEntity user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user doesn't exist"));
        return user.getName();
//        Optional<UserEntity> result = userRepository.findById(id);
//        if ( result.isPresent() ) {
//            return result.get().getName();
//        } else {
//            return "noUser";
//        }
    }
    public String searchUser(String name) {
        List<UserEntity> result = userRepository.findByName(name);
        for ( UserEntity user : result ) {
            System.out.println(user.getId() + user.getNickname());
        }
        return "";
    }

}
