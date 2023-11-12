package com.mybatis.kdt9.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // 기본 생성자가 필요하다.
@Table(name="user")
@Getter
@Builder // 생성자에 필드가 들어있는 친구
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // auto_increment 로 설정해라
    private int id;
    @Column(name="name", nullable = false, length = 20)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String nickname; // 255

    // Enum 타입을 만들 수 있다.
//    @Column
//    @Enumerated(EnumType.STRING) // ORDINAL >
//    private UserType type;
//
//    public enum UserType {
//         STUDENT, TEACHER
//    }
}
