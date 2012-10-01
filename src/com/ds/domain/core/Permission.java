package com.ds.domain.core;


import javax.persistence.*;

/**
 * @author vaibhav.adlakha
 */
@Entity
@Table(name = "permission")
public class Permission implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "NAME", nullable = false)
  private String name;


  @Column(name = "LOCK_VERSION", nullable = false)
  private Long lockVersion;

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

  public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }


}


