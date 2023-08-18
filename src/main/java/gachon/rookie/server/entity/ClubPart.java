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
@Table(name = "club_part")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubPart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id", nullable = false, unique = true)
    private Long partId;

    @Column(name = "part_name", nullable = false)
    private String partName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id", referencedColumnName = "apply_id", nullable = false)
    private ClubApply applyId;

    @Builder
    public ClubPart(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long partId, String partName, ClubApply applyId) {
        super(createdAt, updatedAt, status);
        this.partId = partId;
        this.partName = partName;
        this.applyId = applyId;
    }
}
