package gachon.rookie.server.entity;

import gachon.rookie.server.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "club_recruit")
public class ClubRecruit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id", nullable = false)
    private Long recruitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id", nullable = false)
    private Club clubId;

    @Column(name = "gen", nullable = false)
    private Integer gen;
}
