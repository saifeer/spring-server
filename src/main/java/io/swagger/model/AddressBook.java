package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * An address book that can hold multiple contacts.
 */
@ApiModel(description = "An address book that can hold multiple contacts.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-08T23:51:07.282Z")


public class AddressBook   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("entries")
  @Valid
  private List<Entry> entries = new ArrayList<Entry>();

  public AddressBook id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Unique id to identify an address book
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique id to identify an address book")
  @NotNull


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public AddressBook name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Unique name to identify the address book
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Unique name to identify the address book")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AddressBook entries(List<Entry> entries) {
    this.entries = entries;
    return this;
  }

  public AddressBook addEntriesItem(Entry entriesItem) {
    if (this.entries == null) {
      this.entries = new ArrayList<Entry>();
    }
    this.entries.add(entriesItem);
    return this;
  }

  /**
   * The contact entries held by the book
   * @return entries
  **/
  @ApiModelProperty(value = "The contact entries held by the book")

  @Valid

  public List<Entry> getEntries() {
    return entries;
  }

  public void setEntries(List<Entry> entries) {
    this.entries = entries;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressBook addressBook = (AddressBook) o;
    return 
        Objects.equals(this.name, addressBook.name) &&
        this.entries.containsAll(addressBook.entries) &&
        addressBook.entries.containsAll(this.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, entries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressBook {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

