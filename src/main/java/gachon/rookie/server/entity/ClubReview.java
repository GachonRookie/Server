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
@Table(name = "club_review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "revire_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewType reviewType;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "gen", nullable = false)
    private Integer gen;

    @Builder
    public ClubReview(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long reviewId, Member memberId, String content, ReviewType reviewType, Integer rating, Integer gen) {
        super(createdAt, updatedAt, status);
        this.reviewId = reviewId;
        this.memberId = memberId;
        this.content = content;
        this.reviewType = reviewType;
        this.rating = rating;
        this.gen = gen;
    }
}
