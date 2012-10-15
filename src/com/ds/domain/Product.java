package com.ds.domain;

@SuppressWarnings("serial")
/*@Entity
@Table(name = "product")*/
public class Product {

  /*@Id
@Column(name = "id", unique = true, nullable = false, length = 20)*/
  private String id;

  /* @Column(name = "name", nullable = false, length = 150)*/
  private String name;

  /*@Column(name = "thumb_url", nullable = false, length = 150)*/
  private String thumbUrl;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getThumbUrl() {
    return thumbUrl;
  }

  public void setThumbUrl(String thumbUrl) {
    this.thumbUrl = thumbUrl;
  }

  /*@Override
  protected String[] getKeys() {
      return new String[]{"id","turl","nm"};
  }

  @Override
  protected Object[] getValues() {
      return new Object[]{id,thumbUrl,name};
  }*/

}
