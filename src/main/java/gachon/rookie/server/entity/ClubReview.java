package gachon.rookie.server.entity;

import gachon.rookie.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "club_review")
public class ClubReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    private Member memberId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @Builder.Default
    @Column(name = "revire_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewType reviewType;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "gen", nullable = false)
    private Integer gen;

}
