package com.meettingfilm.backend_common.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by ilinklink on 2020/4/9.
 */
@Data
public class LoginParams implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;

    private String password;


}
