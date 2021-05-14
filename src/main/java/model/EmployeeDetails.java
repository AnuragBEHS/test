package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;

@JsonPropertyOrder({"id", "name", "zip", "address"})
public class EmployeeDetails {

  public EmployeeDetails(String id, String name, String address, String zip) {
    this.id = id;
    this.name = name;
    this.zip = zip;
    this.address = address;
  }

  public EmployeeDetails() {
  }

  @JsonProperty("name")
  private String name;

  @JsonProperty("address")
  private String address;

  @JsonProperty("zip")
  private String zip;

  @JsonProperty("id")
  private String id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj.getClass() != this.getClass()) {
      return false;
    }
    final EmployeeDetails other = (EmployeeDetails) obj;

    if (!Objects.equals(this.name, other.name)) {
      return false;
    }
    if (!Objects.equals(this.address, other.address)) {
      return false;
    }
    if (!Objects.equals(this.zip, other.zip)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
    hash = 53 * hash + (this.address != null ? this.address.hashCode() : 0);
    hash = 53 * hash + (this.zip != null ? this.zip.hashCode() : 0);
    return hash;
  }

  @Override
  public String toString() {
    return ("name:" + this.getName() +
        " address: " + this.getAddress() +
        " zip: " + this.getZip() +
        " id : " + this.getId());
  }

}
