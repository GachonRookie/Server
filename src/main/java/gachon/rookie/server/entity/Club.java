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

    @Column(name = "club_type")
    private ClubType clubType;

    @Column(name = "sub_type")
    private ClubSubType clubSubType;

    @Column(name = "ad_content", columnDefinition = "TEXT")
    private String adContent;

    @Column(name = "sns_link", columnDefinition = "TEXT")
    private String snsLink;

    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    @Builder
    public Club(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status, Long clubId, ClubType clubType, ClubSubType clubSubType, String adContent, String snsLink, String logoUrl) {
        super(createdAt, updatedAt, status);
        this.clubId = clubId;
        this.clubType = clubType;
        this.clubSubType = clubSubType;
        this.adContent = adContent;
        this.snsLink = snsLink;
        this.logoUrl = logoUrl;
    }
}
