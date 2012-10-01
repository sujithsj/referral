package com.ds.domain.core;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vaibhav.adlakha
 */
@Entity
@Table(name = "plan")
public class Plan implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "NAME", nullable = false)
  private String name;


  @Column(name = "DESCRIPTION")
  private String description;


  @Column(name = "LOCK_VERSION", nullable = false)
  private Long lockVersion;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "plan")
  private Set<Feature> features = new HashSet<Feature>(0);

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

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }

  public Set<Feature> getFeatures() {
    return this.features;
  }

  public void setFeatures(Set<Feature> features) {
    this.features = features;
  }


}


