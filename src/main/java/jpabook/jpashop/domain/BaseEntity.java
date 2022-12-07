package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 매핑 정보만 모아두는 곳
@Getter @Setter
public abstract class BaseEntity {
    @Column(name="insert_member")
    private String createdBy;

    private LocalDateTime createdDate;
    @Column(name="update_member")
    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;


}
