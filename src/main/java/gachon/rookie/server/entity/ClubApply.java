package gachon.rookie.server.entity;

import gachon.rookie.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "club_apply")
public class ClubApply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id", nullable = false)
    private Long applyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member memberId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id", nullable = false)
    private Club clubId;

    @Builder.Default
    @Column(name = "apply_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'STANDBY'")
    private ApplyStatus applyStatus;

    @Column(name = "gen", nullable = false)
    private Integer gen;

    //활동 종료일
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
}
