package com.domotic.enhanced.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity(name = "T_IPCAM")
public class IpCamModel implements Serializable {
  
  private static final long serialVersionUID = 4405300964954637700L;

  public IpCamModel() {
    // needed by ormlite
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(nullable = false)
  private String url;
  
  @Column
  private String username;
  
  @Column
  private String password; // TODO no clear-text
  
  @Column(nullable = false)
  private String name;
  
  /*
   * 
   */

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    } else if (object == this) {
      return true;
    } else if (!(object instanceof IpCamModel)) {
      return false;
    }
    
    IpCamModel ipcam = (IpCamModel) object;
    return new EqualsBuilder()
      .append(id, ipcam.id)
      .append(url, ipcam.url)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 31)
      .append(id)
      .append(url)
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("id", id)
      .append("url", url)
      .append("username", username)
      //.append("password", password)
      .append("name", name)
      .toString();
  }
  
}
