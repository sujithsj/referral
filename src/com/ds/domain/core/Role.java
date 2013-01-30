package com.ds.domain.core;


import com.ds.domain.BaseDataObject;

import java.util.Set;

/**
 * @author vaibhav.adlakha
 */
/*@Entity
@Table(name = "role")*/
public class Role extends BaseDataObject {


  /*@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)*/
  private Long id;


  /*@Column(name = "NAME", nullable = false)*/
  private String name;


  /*@Column(name = "DESCRIPTION", nullable = false)*/
  private String description;


  private Set<Permission> permissions;

  /*@Column(name = "LOCK_VERSION", nullable = false)
  @Version
  private Long lockVersion;*/


  public enum RoleType {
        admin, moderator, affiliate
    }

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

  /*public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }*/

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }
}


