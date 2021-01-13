package com.lmx.scaffold.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 员工信息表
 * </p>
 *
 * @author lmx
 * @since 2020-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dws_prsn_inf_di")
public class DwsPrsnInfDi implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 员工id
     */
    private String userid;

    /**
     * 统计日期
     */
    private Date statisticsDate;

    /**
     * 员工姓名
     */
    private String userName;

    /**
     * 级别
     */
    private String postRank;

    /**
     * 岗位
     */
    private String post;

    /**
     * 领导id
     */
    private String leadId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 工号
     */
    private String workNo;

    /**
     * 邮箱
     */
    private String email;


}
