package com.ds.domain.commission;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Jan 24, 2013
 * Time: 2:01:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "COMMISSION_EARNING_STATUS")
public class CommissionEarningStatus {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "STATUS_NAME", nullable = false, length = 100)
    private String statusName;

    @Column(name = "STATUS", nullable = false)
    private Long status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
