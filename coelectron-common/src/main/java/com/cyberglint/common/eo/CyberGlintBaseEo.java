package com.cyberglint.common.eo;

import lombok.Data;

import java.util.Date;

@Data
public class CyberGlintBaseEo {
    /**
     * id
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建人
     */
    private String createPerson;
    /**
     * 更新人
     */
    private String updatePerson;
    /**
     * 逻辑删除 0:未删除 1:删除
     */
    private Integer dr;
}
