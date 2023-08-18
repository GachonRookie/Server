package gachon.rookie.server.entity;

import gachon.rookie.server.common.BaseEntity;
import gachon.rookie.server.common.BaseStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "club_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_member_id")
    private Long clubMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private Club clubId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member memberId;

    @Column(name = "gen", nullable = false)
    private Integer gen;

    @Column(name = "activeEndDate", nullable = false)
    private LocalDate activeEndDate;

    @Builder
    public ClubMember(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long clubMemberId, Club clubId, Member memberId, Integer gen, LocalDate activeEndDate) {
        super(createdAt, updatedAt, status);
        this.clubMemberId = clubMemberId;
        this.clubId = clubId;
        this.memberId = memberId;
        this.gen = gen;
        this.activeEndDate = activeEndDate;
    }
}
