package com.ds.domain.product;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "product_group")
public class ProductGroup implements java.io.Serializable {

  @Id
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "COMPANY_SHORTNAME", nullable = false, length = 50)
  private String companyShortname;


  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCompanyShortname() {
    return this.companyShortname;
  }

  public void setCompanyShortname(String companyShortname) {
    this.companyShortname = companyShortname;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }


}


