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
@Table(name = "club")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Club extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "club_name", nullable = false)
    private String clubName;

    //교내인지 연합인지
    @Column(name = "club_tag")
    private ClubTag clubTag;

    //홍보글
    @Column(name = "ad_content", columnDefinition = "TEXT")
    private String adContent;

    //지원 링크
    @Column(name = "sns_link", columnDefinition = "TEXT")
    private String snsLink;

    //동아리 로고 url
    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    @Builder
    public Club(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long clubId, String clubName, ClubTag clubTag, String adContent, String snsLink, String logoUrl) {
        super(createdAt, updatedAt, status);
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubTag = clubTag;
        this.adContent = adContent;
        this.snsLink = snsLink;
        this.logoUrl = logoUrl;
    }
}
