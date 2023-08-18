package gachon.rookie.server.common;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 모든 엔티티에 공통으로 들어가는 요소
 * @author LEE JIHO
 * @usage 각 엔티티데 BaseEntity를 extends 받아 사용
 * */

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BaseStatus status;

    public BaseEntity(LocalDateTime createdAt, LocalDateTime updatedAt, BaseStatus status) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }
}
