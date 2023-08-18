package gachon.rookie.server.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "type", nullable = false)
    private MemberType type;

}
