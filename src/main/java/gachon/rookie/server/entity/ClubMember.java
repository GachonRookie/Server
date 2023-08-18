package gachon.rookie.server.entity;

import gachon.rookie.server.common.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "club_member")
public class ClubMember extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "club_member_id")
    private Long clubMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private Club clubId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member memberId;

    @Column(name = "gen", nullable = false)
    private Integer gen;

    @Column(name = "activeEndDate", nullable = false)
    private LocalDate activeEndDate;
}
