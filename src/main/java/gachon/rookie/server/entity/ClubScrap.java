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
@Table(name = "club_scrap")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubScrap extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id", nullable = false)
    private Long scrapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id", nullable = false)
    private Club clubId;

    @Builder
    public ClubScrap(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long scrapId, Member memberId, Club clubId) {
        super(createdAt, updatedAt, status);
        this.scrapId = scrapId;
        this.memberId = memberId;
        this.clubId = clubId;
    }
}
