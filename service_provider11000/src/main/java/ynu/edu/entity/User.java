package ynu.edu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String password;
}
