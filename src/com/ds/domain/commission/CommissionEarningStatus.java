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


    @Column(name = "STATUS", nullable = false)
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}