package com.elvis.commons.old.prop;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : Elvis
 * @since : 2020/8/28 17:38
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class FtpProp implements Serializable {
    @ApiModelProperty("服务器地址")
    private String host;
    @ApiModelProperty("服务器端口")
    private Integer port;
    @ApiModelProperty("登录账号")
    private String username;
    @ApiModelProperty("登录密码")
    private String password;
}
