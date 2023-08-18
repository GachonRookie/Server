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

@Entity
@Table(name = "club_part")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubPart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id", nullable = false, unique = true)
    private Long partId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id", nullable = false)
    private Club clubId;

    @Column(name = "gen", nullable = false)
    private Integer gen;

    @Column(name = "part_name", nullable = false)
    private String partName;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "recruit_id", referencedColumnName = "recruit_id", nullable = false)
    private ClubRecruit recruitId;

    @Builder
    public ClubPart(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long partId, Club clubId, Integer gen, String partName, ClubRecruit recruitId) {
        super(createdAt, updatedAt, status);
        this.partId = partId;
        this.clubId = clubId;
        this.gen = gen;
        this.partName = partName;
        this.recruitId = recruitId;
    }
}
