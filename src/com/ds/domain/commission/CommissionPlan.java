package com.ds.domain.commission;


import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "commission_plan")
public class CommissionPlan implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "COMMISSION_CURRENCY_ID", nullable = false)
  private CommissionCurrency commissionCurrency;


  @Column(name = "TIERED", nullable = false, length = 1)
  private boolean tiered;

  @Column(name = "AUTO_APPROVE_COMM", nullable = false, length = 1)
  private boolean autoApproveComm = true;

  @Column(name = "COMPANY_SHORT_NAME", nullable = false, length = 50)
  private String companyShortName;


  @Column(name = "INIT_COM", precision = 22, scale = 0)
  private Double initCom;


  @Column(name = "RECUR_COM", precision = 22, scale = 0)
  private Double recurCom;


  @Column(name = "TIER_1_INIT_COM", precision = 22, scale = 0)
  private Double tier1InitCom;


  @Column(name = "TIER_1_RECUR_COM", precision = 22, scale = 0)
  private Double tier1RecurCom;


  @Column(name = "TIER_2_INIT_COM", precision = 22, scale = 0)
  private Double tier2InitCom;


  @Column(name = "TIER_2_RECUR_COM", precision = 22, scale = 0)
  private Double tier2RecurCom;


  @Column(name = "TIER_3_INIT_COM", precision = 22, scale = 0)
  private Double tier3InitCom;


  @Column(name = "TIER_3_RECUR_COM", precision = 22, scale = 0)
  private Double tier3RecurCom;


  @Column(name = "LIMIT_RECUR_COMM_DAYS")
  private Long limitRecurCommDays;


  @Column(name = "LIMIT_RECUR_COMM_TXN")
  private Long limitRecurCommTxn;


  @Column(name = "RECUR_COMM_GRADE", precision = 22, scale = 0)
  private Double recurCommGrade;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public CommissionCurrency getCommissionCurrency() {
    return this.commissionCurrency;
  }

  public void setCommissionCurrency(CommissionCurrency commissionCurrency) {
    this.commissionCurrency = commissionCurrency;
  }

  
  public Double getInitCom() {
    return this.initCom;
  }

  public void setInitCom(Double initCom) {
    this.initCom = initCom;
  }

  public Double getRecurCom() {
    return this.recurCom;
  }

  public void setRecurCom(Double recurCom) {
    this.recurCom = recurCom;
  }

  public Double getTier1InitCom() {
    return this.tier1InitCom;
  }

  public void setTier1InitCom(Double tier1InitCom) {
    this.tier1InitCom = tier1InitCom;
  }

  public Double getTier1RecurCom() {
    return this.tier1RecurCom;
  }

  public void setTier1RecurCom(Double tier1RecurCom) {
    this.tier1RecurCom = tier1RecurCom;
  }

  public Double getTier2InitCom() {
    return this.tier2InitCom;
  }

  public void setTier2InitCom(Double tier2InitCom) {
    this.tier2InitCom = tier2InitCom;
  }

  public Double getTier2RecurCom() {
    return this.tier2RecurCom;
  }

  public void setTier2RecurCom(Double tier2RecurCom) {
    this.tier2RecurCom = tier2RecurCom;
  }

  public Double getTier3InitCom() {
    return this.tier3InitCom;
  }

  public void setTier3InitCom(Double tier3InitCom) {
    this.tier3InitCom = tier3InitCom;
  }

  public Double getTier3RecurCom() {
    return this.tier3RecurCom;
  }

  public void setTier3RecurCom(Double tier3RecurCom) {
    this.tier3RecurCom = tier3RecurCom;
  }

  public Long getLimitRecurCommDays() {
    return this.limitRecurCommDays;
  }

  public void setLimitRecurCommDays(Long limitRecurCommDays) {
    this.limitRecurCommDays = limitRecurCommDays;
  }

  public Long getLimitRecurCommTxn() {
    return this.limitRecurCommTxn;
  }

  public void setLimitRecurCommTxn(Long limitRecurCommTxn) {
    this.limitRecurCommTxn = limitRecurCommTxn;
  }

  public Double getRecurCommGrade() {
    return this.recurCommGrade;
  }

  public void setRecurCommGrade(Double recurCommGrade) {
    this.recurCommGrade = recurCommGrade;
  }

  public boolean isTiered() {
    return tiered;
  }

  public void setTiered(boolean tiered) {
    this.tiered = tiered;
  }

  public boolean isAutoApproveComm() {
    return autoApproveComm;
  }

  public void setAutoApproveComm(boolean autoApproveComm) {
    this.autoApproveComm = autoApproveComm;
  }

  public String getCompanyShortName() {
    return companyShortName;
  }

  public void setCompanyShortName(String companyShortName) {
    this.companyShortName = companyShortName;
  }
}


