package com.ds.domain.core;


import javax.persistence.*;

/**
 * @author vaibhav.adlakha
 */
/*@Entity
@Table(name = "feature")*/
public class Feature implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PLAN_ID", nullable = false)
  private Plan plan;


  @Column(name = "NAME", nullable = false)
  private String name;


  @Column(name = "FEATURE_TYPE", nullable = false)
  private String featureType;


  @Column(name = "COUNT")
  private Long count;


  @Column(name = "LOCK_VERSION", nullable = false)
  @Version
  private Long lockVersion;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Plan getPlan() {
    return this.plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFeatureType() {
    return this.featureType;
  }

  public void setFeatureType(String featureType) {
    this.featureType = featureType;
  }

  public Long getCount() {
    return this.count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }


}


