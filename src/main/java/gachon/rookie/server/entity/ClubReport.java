package gachon.rookie.server.entity;

import gachon.rookie.server.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "club_report")
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
    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;
}
