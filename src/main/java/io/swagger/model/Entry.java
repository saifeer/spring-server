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
 * A standard address book entry
 */
@ApiModel(description = "A standard address book entry")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-07-08T23:51:07.282Z")


public class Entry   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("numbers")
  @Valid
  private List<PhoneNumber> numbers = new ArrayList<PhoneNumber>();

  @JsonProperty("id")
  private Integer id = null;

  public Entry name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the contact 
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Name of the contact ")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Entry numbers(List<PhoneNumber> numbers) {
    this.numbers = numbers;
    return this;
  }

  public Entry addNumbersItem(PhoneNumber numbersItem) {
    this.numbers.add(numbersItem);
    return this;
  }

  /**
   * List of all numbers for the contact 
   * @return numbers
  **/
  @ApiModelProperty(required = true, value = "List of all numbers for the contact ")
  @NotNull

  @Valid

  public List<PhoneNumber> getNumbers() {
    return numbers;
  }

  public void setNumbers(List<PhoneNumber> numbers) {
    this.numbers = numbers;
  }

  public Entry id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Unique ID
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique ID")
  @NotNull


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Entry entry = (Entry) o;
    return Objects.equals(this.name, entry.name) &&
    		this.numbers.containsAll(entry.numbers) &&
    		entry.numbers.containsAll(this.numbers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, numbers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Entry {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    numbers: ").append(toIndentedString(numbers)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

