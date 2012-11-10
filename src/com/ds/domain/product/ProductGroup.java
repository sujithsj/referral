package com.ds.domain.product;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "product_group")
@NamedQueries({
    @NamedQuery(name = "getProductGroupById", query = "select pg from ProductGroup pp where pp.id = :productGroupId")
})
public class ProductGroup implements java.io.Serializable {

  @Id
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "COMPANY_SHORTNAME", nullable = false, length = 50)
  private String companyShortname;


  @Column(name = "NAME", nullable = false, length = 200)
  private String name;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(
      name = "PRODUCT_GROUP_HAS_PRODUCT",
      joinColumns = {@JoinColumn(name = "PRODUCT_GROUP_ID", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false)}
  )
  
  private Set<Product> products = new HashSet<Product>();


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

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }
}


