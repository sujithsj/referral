package com.ds.domain.commission;


import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "commission_currency")
@NamedQueries({
    @NamedQuery(name = "getCommissionCurrencyById", query = "select cc from CommissionCurrency cc where cc.id = :commissionCurrencyId")
})
public class CommissionCurrency implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "CURR_CODE", nullable = false, length = 45)
  private String currCode;


  @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)
  private String companyShortName;


  @Column(name = "NAME", nullable = false, length = 100)
  private String name;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commissionCurrency")
  private Set<CommissionPlan> commissionPlans = new HashSet<CommissionPlan>(0);

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCurrCode() {
    return this.currCode;
  }

  public void setCurrCode(String currCode) {
    this.currCode = currCode;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<CommissionPlan> getCommissionPlans() {
    return this.commissionPlans;
  }

  public void setCommissionPlans(Set<CommissionPlan> commissionPlans) {
    this.commissionPlans = commissionPlans;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }
}


