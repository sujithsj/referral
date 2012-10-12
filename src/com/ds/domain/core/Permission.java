package com.ds.domain.core;


import javax.persistence.*;

/**
 * @author vaibhav.adlakha
 */
/*@Entity
@Table(name = "permission")*/
public class Permission implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "NAME", nullable = false)
  private String name;


  @Column(name = "LOCK_VERSION", nullable = false)
  @Version
  private Long lockVersion;


  public enum Type {
        ADD_EMPLOYEE, UPDATE_EMPLOYEE, DELETE_EMPLOYEE, ADD_PRODUCT, UPDATE_PRODUCT,
        DELETE_PRODUCT, ADD_ISSUETRACKER, UPDATE_ISSUETRACKER, DELETE_ISSUETRACKER, DELETE_POST,
        CHANGE_POST_STATUS,CHANGE_POST_EMPLOYEE,ANSWER_POST,UPDATE_POST,CREATE_ISSUE,
        UPDATE_COMPANY_DETAILS,MARK_POST_SPAM,MARK_POST_UNSPAM,COMPANY_EMPLOYEE,
        MARK_POST_PRIVATE,MARK_POST_PUBLIC,
        CREATE_ANNOUNCEMENT,UPDATE_ANNOUNCEMENT,DELETE_ANNOUNCEMENT,PUBLISH_ANNOUNCEMENT,UNPUBLISH_ANNOUNCEMENT
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

  public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }


}


