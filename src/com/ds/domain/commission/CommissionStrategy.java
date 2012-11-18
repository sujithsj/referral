package com.ds.domain.commission;


import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "commission_strategy")
@NamedQueries({
    @NamedQuery(name = "getCommissionPlanById", query = "select cp from CommissionPlan cp where cp.id = :commissionPlanId")
})
public class CommissionStrategy implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "NAME", nullable = false, length = 200)
  private String name;
                
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }


}


