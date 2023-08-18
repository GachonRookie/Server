package gachon.rookie.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import gachon.rookie.server.common.BaseEntity;
import gachon.rookie.server.common.BaseStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "user_token", unique = true, nullable = false)
    private String userToken;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @JsonBackReference
    @OneToMany(mappedBy = "memberId")
    private List<ClubApply> clubApplies;

    @Builder
    public Member(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long memberId, String userToken, String nickname, List<ClubApply> clubApplies) {
        super(createdAt, updatedAt, status);
        this.memberId = memberId;
        this.userToken = userToken;
        this.nickname = nickname;
        this.clubApplies = clubApplies;
    }
}
