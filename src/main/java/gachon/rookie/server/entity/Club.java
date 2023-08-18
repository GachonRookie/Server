package gachon.rookie.server.entity;

import gachon.rookie.server.common.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "club")
public class Club extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "dept")
    private String dept;

    @Column(name = "sub_class")
    private String subClass;

    @Column(name = "ad_content", columnDefinition = "TEXT")
    private String adContent;

    @Column(name = "sns_link", columnDefinition = "TEXT")
    private String snsLink;

    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;


}
