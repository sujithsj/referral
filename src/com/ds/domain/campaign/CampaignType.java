package com.ds.domain.campaign;


import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.HashSet;
import java.util.Set;

/**
 * this is only a logical grouping of campaigns may be to be used only in reporting and no effect on commisions.
 */
@Entity
@Table(name = "campaign_type")
public class CampaignType implements java.io.Serializable {


  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long id;


  @Column(name = "TYPE", nullable = false, length = 250)
  private String type;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "campaignType")
  private Set<Campaign> campaigns = new HashSet<Campaign>(0);

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

  public Set<Campaign> getCampaigns() {
    return this.campaigns;
  }

  public void setCampaigns(Set<Campaign> campaigns) {
    this.campaigns = campaigns;
  }


}


