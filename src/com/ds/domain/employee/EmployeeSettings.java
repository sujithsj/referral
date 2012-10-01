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
  private char sendEmailOnAddAff;


  @Column(name = "SEND_EMAIL_ON_ASSIGNED_POST", nullable = false, length = 1)
  private char sendEmailOnAssignedPost;


  @Column(name = "LOCK_VERSION", nullable = false)
  private Long lockVersion;


  @Column(name = "SEND_EMAIL_ON_TERM_AFF", nullable = false, length = 1)
  private char sendEmailOnTermAff;


  @Column(name = "SEND_EMAIL_ON_JOIN_AFF", nullable = false, length = 1)
  private char sendEmailOnJoinAff;


  @Column(name = "SEND_EMAIL_ON_PAYOUT", nullable = false, length = 1)
  private char sendEmailOnPayout;


  @Column(name = "SEND_EMAIL_ON_GOAL_CONV", nullable = false, length = 1)
  private char sendEmailOnGoalConv;

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

  public char getSendEmailOnAddAff() {
    return this.sendEmailOnAddAff;
  }

  public void setSendEmailOnAddAff(char sendEmailOnAddAff) {
    this.sendEmailOnAddAff = sendEmailOnAddAff;
  }

  public char getSendEmailOnAssignedPost() {
    return this.sendEmailOnAssignedPost;
  }

  public void setSendEmailOnAssignedPost(char sendEmailOnAssignedPost) {
    this.sendEmailOnAssignedPost = sendEmailOnAssignedPost;
  }

  public Long getLockVersion() {
    return this.lockVersion;
  }

  public void setLockVersion(Long lockVersion) {
    this.lockVersion = lockVersion;
  }

  public char getSendEmailOnTermAff() {
    return this.sendEmailOnTermAff;
  }

  public void setSendEmailOnTermAff(char sendEmailOnTermAff) {
    this.sendEmailOnTermAff = sendEmailOnTermAff;
  }

  public char getSendEmailOnJoinAff() {
    return this.sendEmailOnJoinAff;
  }

  public void setSendEmailOnJoinAff(char sendEmailOnJoinAff) {
    this.sendEmailOnJoinAff = sendEmailOnJoinAff;
  }

  public char getSendEmailOnPayout() {
    return this.sendEmailOnPayout;
  }

  public void setSendEmailOnPayout(char sendEmailOnPayout) {
    this.sendEmailOnPayout = sendEmailOnPayout;
  }

  public char getSendEmailOnGoalConv() {
    return this.sendEmailOnGoalConv;
  }

  public void setSendEmailOnGoalConv(char sendEmailOnGoalConv) {
    this.sendEmailOnGoalConv = sendEmailOnGoalConv;
  }

  public Set<Employee> getEmployees() {
    return this.employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }


}


