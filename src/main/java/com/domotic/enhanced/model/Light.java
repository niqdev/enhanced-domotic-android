package com.domotic.enhanced.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity(name = "device_light")
public class Light implements Serializable {
  
  private static final long serialVersionUID = 5956805037753013677L;

  public Light() {
    // needed by ormlite
  }

  @Id
  @GeneratedValue//(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(nullable = false)
  private Integer deviceId;
  
  @Column
  private String name;
  
  @Column
  private String description;
  
  /*
   * 
   */

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(Integer deviceId) {
    this.deviceId = deviceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    } else if (object == this) {
      return true;
    } else if (!(object instanceof Light)) {
      return false;
    }
    
    Light light = (Light) object;
    return new EqualsBuilder()
      .append(id, light.id)
      .append(deviceId, light.deviceId)
      .isEquals();
  }

  @Override
  public int hashCode() {
    // two randomly chosen prime numbers
    return new HashCodeBuilder(17, 31)
      .append(id)
      .append(deviceId)
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("id", id)
      .append("deviceId", deviceId)
      .append("name", name)
      .append("description", description)
      .toString();
  }
  
}
