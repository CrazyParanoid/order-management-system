package org.example.order.service.domain.supertype;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class AuditableDomainObject {

    @CreatedDate
    private Date creationDate;
    @LastModifiedDate
    private Date lastModifiedDate;

}
