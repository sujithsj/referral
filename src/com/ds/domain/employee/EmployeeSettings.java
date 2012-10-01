package com.ds.domain.employee;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author vaibhav.adlakha
 */
@Entity
@Table(name = "employee_settings")
public class EmployeeSettings implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
  private Employee employee;


  @Column(name = "SEND_EMAIL_ON_ADD_AFF", nullable = false, length = 1)
  private boolean sendEmailOnAddAff;


  @Column(name = "SEND_EMAIL_ON_ASSIGNED_POST", nullable = false, length = 1)
  private boolean sendEmailOnAssignedPost;


  @Column(name = "LOCK_VERSION", nullable = false)
  @Version
  private Long lockVersion;


  @Column(name = "SEND_EMAIL_ON_TERM_AFF", nullable = false, length = 1)
  private boolean sendEmailOnTermAff;


  @Column(name = "SEND_EMAIL_ON_JOIN_AFF", nullable = false, length = 1)
  private boolean sendEmailOnJoinAff;


  @Column(name = "SEND_EMAIL_ON_PAYOUT", nullable = false, length = 1)
  private boolean sendEmailOnPayout;


  @Column(name = "SEND_EMAIL_ON_GOAL_CONV", nullable = false, length = 1)
  private boolean sendEmailOnGoalConv;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeeSettings")
  private Set<Employee> employees = new HashSet<Employee>(0);

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }


  public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }

  public Set<Employee> getEmployees() {
    return this.employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  public boolean isSendEmailOnAddAff() {
    return sendEmailOnAddAff;
  }

  public void setSendEmailOnAddAff(boolean sendEmailOnAddAff) {
    this.sendEmailOnAddAff = sendEmailOnAddAff;
  }

  public boolean isSendEmailOnAssignedPost() {
    return sendEmailOnAssignedPost;
  }

  public void setSendEmailOnAssignedPost(boolean sendEmailOnAssignedPost) {
    this.sendEmailOnAssignedPost = sendEmailOnAssignedPost;
  }

  public boolean isSendEmailOnTermAff() {
    return sendEmailOnTermAff;
  }

  public void setSendEmailOnTermAff(boolean sendEmailOnTermAff) {
    this.sendEmailOnTermAff = sendEmailOnTermAff;
  }

  public boolean isSendEmailOnJoinAff() {
    return sendEmailOnJoinAff;
  }

  public void setSendEmailOnJoinAff(boolean sendEmailOnJoinAff) {
    this.sendEmailOnJoinAff = sendEmailOnJoinAff;
  }

  public boolean isSendEmailOnPayout() {
    return sendEmailOnPayout;
  }

  public void setSendEmailOnPayout(boolean sendEmailOnPayout) {
    this.sendEmailOnPayout = sendEmailOnPayout;
  }

  public boolean isSendEmailOnGoalConv() {
    return sendEmailOnGoalConv;
  }

  public void setSendEmailOnGoalConv(boolean sendEmailOnGoalConv) {
    this.sendEmailOnGoalConv = sendEmailOnGoalConv;
  }
}


