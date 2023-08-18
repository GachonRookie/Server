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
@Table(name = "club_report")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id", nullable = false)
    private Club clubId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;


    @Builder
    public ClubReport(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long reportId, Member memberId, Club clubId, String content) {
        super(createdAt, updatedAt, status);
        this.reportId = reportId;
        this.memberId = memberId;
        this.clubId = clubId;
        this.content = content;
    }
}
