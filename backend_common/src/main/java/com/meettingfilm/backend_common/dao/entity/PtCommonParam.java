package com.meettingfilm.backend_common.dao.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 常用参数
 * </p>
 *
 * @author chuck
 * @since 2020-08-11
 */
@Data
public class PtCommonParam extends Model<PtCommonParam> {

    private static final long serialVersionUID = 1L;

    /**
     * 参数ID
     */
    @TableId(value = "Param_ID", type = IdType.AUTO)
    private int paramId;

    /**
     * 参数值
     */
    @TableField("Param_Value")
    private String paramValue;

    /**
     * 参数类型 1-商品归类 2-运单服务商
     */
    @TableField("Param_Type")
    private int paramType;

    /**
     * 参数说明
     */
    @TableField("Param_Desc")
    private String paramDesc;

    /**
     * 开启状态 0-未开启 1-开启
     */
    @TableField("Param_Open")
    private int paramOpen;

    /**
     * 开启状态更新时间
     */
    @TableField("Open_UTime")
    private Date openUtime;

    /**
     * 删除状态 0-未删除 1-已删除
     */
    @TableField("Param_Remove")
    private int paramRemove;

    /**
     * 删除时间
     */
    @TableField("Remove_Time")
    private Date removeTime;

    /**
     * 创建时间
     */
    @TableField("CTime")
    private Date CTime;

    /**
     * 更新时间
     */
    @TableField("UTime")
    private Date UTime;



    @Override
    public String toString() {
        return "PtCommonParam{" +
        ", paramId=" + paramId +
        ", paramValue=" + paramValue +
        ", paramType=" + paramType +
        ", paramDesc=" + paramDesc +
        ", paramOpen=" + paramOpen +
        ", openUtime=" + openUtime +
        ", paramRemove=" + paramRemove +
        ", removeTime=" + removeTime +
        ", CTime=" + CTime +
        ", UTime=" + UTime +
        "}";
    }
}
