package com.prueba.postgres.embedded.infraestructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//@SoftDelete(columnName = "active", strategy = SoftDeleteType.ACTIVE)
public class Auditing implements Serializable {

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  @EqualsAndHashCode.Include
  private Long createBy;
  
  @CreatedDate
  @Column(name = "created_instant", updatable = false, nullable = false, columnDefinition = "TIMESTAMP(0)")
  @EqualsAndHashCode.Include
  private LocalDateTime createdInstant;
 
  @LastModifiedBy
  @Column(name = "modified_by")
  @EqualsAndHashCode.Include
  private Long lastModifiedBy;
  
  @LastModifiedDate
  @Column(name = "modified_instant", nullable = false, columnDefinition = "TIMESTAMP(0)")
  @EqualsAndHashCode.Include
  private LocalDateTime lastModifiedDate;
    
  @Serial
  private static final long serialVersionUID = -5283537905927190955L;

}
