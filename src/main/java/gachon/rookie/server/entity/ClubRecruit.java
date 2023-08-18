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
@Table(name = "club_recruit")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubRecruit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id", nullable = false)
    private Long recruitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id", nullable = false)
    private Club clubId;

    @Column(name = "gen", nullable = false)
    private Integer gen;

    //모집 시작일
    @Column(name = "start_date")
    private LocalDate startDate;

    //모집 종료일
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Builder
    public ClubRecruit(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long recruitId, Club clubId, Integer gen, LocalDate startDate, LocalDateTime endDate) {
        super(createdAt, updatedAt, status);
        this.recruitId = recruitId;
        this.clubId = clubId;
        this.gen = gen;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
