package com.bajiuqu.manage.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String salt;
    private Integer deptId;
    private String picture;
    private Integer sex;
    private String email;
    private String phone;
    private String remark;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Integer status;

}
