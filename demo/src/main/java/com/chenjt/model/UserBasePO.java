package com.chenjt.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@Table(name = "user_base")
public class UserBasePO {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String phone;
  private Integer isActive;

}
