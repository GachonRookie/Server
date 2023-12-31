package gachon.rookie.server.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import gachon.rookie.server.common.BaseEntity;
import gachon.rookie.server.common.BaseStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "club_apply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class ClubApply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apply_id", nullable = false)
    private Long applyId;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id", nullable = false)
    private Club clubId;

    @Column(name = "apply_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'STANDBY'")
    private ApplyStatus applyStatus;

    @Column(name = "gen", nullable = false)
    private Integer gen;

    @Column(name = "apply_part", nullable = false)
    private String applyPart;

    @Builder
    public ClubApply(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long applyId, Member memberId, Club clubId, ApplyStatus applyStatus, Integer gen, String applyPart) {
        super(createdAt, updatedAt, status);
        this.applyId = applyId;
        this.memberId = memberId;
        this.clubId = clubId;
        this.applyStatus = applyStatus;
        this.gen = gen;
        this.applyPart = applyPart;
    }
}
