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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity(name = "T_DRAWER_MENU")
public class DrawerMenuModel implements Serializable {
  
  private static final long serialVersionUID = -3288484734670172864L;

  private static final Logger log = LoggerFactory.getLogger(DrawerMenuModel.class);

  public DrawerMenuModel() {
    // needed by ormlite
  }
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(nullable = false)
  private String label;
  
  @Column(nullable = false)
  private String actionBarTitle;
  
  @Column(nullable = false)
  private Integer orderMenu;
  
  @Column(nullable = false)
  private String qualifiedName;
  
  /*
   * 
   */
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getActionBarTitle() {
    return actionBarTitle;
  }

  public void setActionBarTitle(String actionBarTitle) {
    this.actionBarTitle = actionBarTitle;
  }

  public Integer getOrderMenu() {
    return orderMenu;
  }

  public void setOrderMenu(Integer orderMenu) {
    this.orderMenu = orderMenu;
  }

  public String getQualifiedName() {
    return qualifiedName;
  }

  public void setQualifiedName(String qualifiedName) {
    this.qualifiedName = qualifiedName;
  }
  
  @SuppressWarnings("unchecked")
  public <T> T newInstance() {
    try {
      return (T) Class.forName(qualifiedName).newInstance();
    } catch (Exception e) {
      log.error("DrawerMenuModel#newInstance", e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    } else if (object == this) {
      return true;
    } else if (!(object instanceof DrawerMenuModel)) {
      return false;
    }
    
    DrawerMenuModel drawerMenu = (DrawerMenuModel) object;
    return new EqualsBuilder()
      .append(id, drawerMenu.id)
      .append(qualifiedName, drawerMenu.qualifiedName)
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 31)
      .append(id)
      .append(qualifiedName)
      .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("id", id)
      .append("label", label)
      .append("actionBarTitle", actionBarTitle)
      .append("orderMenu", orderMenu)
      .append("qualifiedName", qualifiedName)
      .toString();
  }

}
