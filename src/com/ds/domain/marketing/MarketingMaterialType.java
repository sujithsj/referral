package com.ds.domain.marketing;


import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "marketing_material_type")
public class MarketingMaterialType implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = IDENTITY)

  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "TYPE", nullable = false, length = 100)
  private String type;

/*@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="marketingMaterialType")
private Set<MarketingMaterial> marketingMaterials = new HashSet<MarketingMaterial>(0);*/

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }


}


