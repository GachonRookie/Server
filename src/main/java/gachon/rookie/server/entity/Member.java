package gachon.rookie.server.entity;

import gachon.rookie.server.common.BaseEntity;
import gachon.rookie.server.common.BaseStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "type", nullable = false)
    private MemberType type;

    @Builder
    public Member(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long memberId, String userId, MemberType type) {
        super(createdAt, updatedAt, status);
        this.memberId = memberId;
        this.userId = userId;
        this.type = type;
    }
}
