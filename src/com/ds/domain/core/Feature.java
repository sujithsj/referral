package com.ds.domain.core;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import com.ds.domain.BaseDataObject;

/**
 * @author vaibhav.adlakha
 */
/*@Entity
@Table(name = "feature")*/
public class Feature extends BaseDataObject {


  private long id;
  // optional name to describe the feature
  private String name;
  private String featureType;
  private long count;
  private Plan plan;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFeatureType() {
    return featureType;
  }

  public void setFeatureType(String featureType) {
    this.featureType = featureType;
  }

  /**
   * @return the count
   */
  public long getCount() {
    return count;
  }

  /**
   * @param count the count to set
   */
  public void setCount(long count) {
    this.count = count;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }

  public boolean equals(Object obj) {
    if (obj instanceof Feature == false)
      return false;

    Feature other = (Feature) obj;
    if (this.getId() > 0 && other.getId() > 0)
      return this.getId() == other.getId();

    return new EqualsBuilder().append(getName(), other.getName()).append(getFeatureType(), other.getFeatureType()).append(getCount(), other.getCount()).isEquals();
  }

  public int hashCode() {
    if (getId() > 0) {
      return new HashCodeBuilder().append(getId()).toHashCode();
    }
    return new HashCodeBuilder().append(getName()).append(getFeatureType()).append(getCount()).toHashCode();
  }


}


