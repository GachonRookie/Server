package gachon.rookie.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import gachon.rookie.server.common.BaseEntity;
import gachon.rookie.server.common.BaseStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "club_recruit")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubRecruit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id", nullable = false)
    private Long recruitId;

    @ManyToOne()
    @JoinColumn(name = "club_id", referencedColumnName = "club_id", nullable = false)
    private Club clubId;

    @Column(name = "gen", nullable = false)
    private Integer gen;

    //모집 대상
    @Column(name = "target")
    private String target;

    //모집 시작일
    @Column(name = "recruit_start_date")
    private LocalDate recruitStartDate;

    //모집 종료일
    @Column(name = "recruit_end_date")
    private LocalDate recruitEndDate;

    //활동 시작일
    @Column(name = "activity_start_date")
    private LocalDate activityStartDate;

    //활동 종료일
    @Column(name = "activity_end_date")
    private LocalDate activityEndDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "recruitId", fetch = FetchType.EAGER)
    private List<ClubPart> parts;

    @Builder
    public ClubRecruit(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long recruitId, Club clubId, Integer gen, String target, LocalDate recruitStartDate, LocalDate recruitEndDate, LocalDate activityStartDate, LocalDate activityEndDate, List<ClubPart> parts) {
        super(createdAt, updatedAt, status);
        this.recruitId = recruitId;
        this.clubId = clubId;
        this.gen = gen;
        this.target = target;
        this.recruitStartDate = recruitStartDate;
        this.recruitEndDate = recruitEndDate;
        this.activityStartDate = activityStartDate;
        this.activityEndDate = activityEndDate;
        this.parts = parts;
    }
}
