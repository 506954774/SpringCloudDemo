package com.meettingfilm.backend.utils.auth;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by ilinklink on 2020/4/9.
 */
@Data
public class AccessToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String randomKey;

    private String token;

    private String userId;

    private int port;
}
