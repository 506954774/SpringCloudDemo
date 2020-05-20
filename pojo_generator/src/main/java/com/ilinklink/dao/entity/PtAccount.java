package com.ilinklink.dao.entity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 平台账号信息
 * </p>
 *
 * @author chuck
 * @since 2020-05-20
 */
@Data
@ApiModel(value="PtAccount对象", description="平台账号信息")
public class PtAccount extends Model<PtAccount> {

    private static final long serialVersionUID = 1L;


    /*****
        * 数据库原始字段
    *******/
    public static final String ACC_ID="Acc_ID";
    public static final String ACC_TYPE="Acc_Type";
    public static final String ACC_NAME="Acc_Name";
    public static final String ACC_AVATAR="Acc_Avatar";
    public static final String ACC_ACCOUNT="Acc_Account";
    public static final String ACC_PWD="Acc_Pwd";
    public static final String PWD_SALT="Pwd_Salt";
    public static final String ACC_GENDER="Acc_Gender";
    public static final String ACC_TEL="Acc_Tel";
    public static final String ACC_STATUS="Acc_Status";
    public static final String STATUS_UTIME="Status_UTime";
    public static final String ACC_REMOVE="Acc_Remove";
    public static final String REMOVE_TIME="Remove_Time";
    public static final String ACC_CTIME="Acc_CTime";
    public static final String ACC_UTIME="Acc_UTime";
    public static final String REGISTER_ACC_ID="Register_Acc_ID";


    @ApiModelProperty( value = "账号ID，唯一" , required = true)
    @TableId("Acc_ID")
    private String accId;

    @ApiModelProperty( value = "账号类型 1-管理员 2-运营专员" , required = true)
    private Integer accType;

    @ApiModelProperty( value = "账号姓名" , required = true)
    private String accName;

    @ApiModelProperty( value = "头像url" , required = true)
    private String accAvatar;

    @ApiModelProperty( value = "登录账号" , required = true)
    private String accAccount;

    @ApiModelProperty( value = "密码" , required = true)
    private String accPwd;

    @ApiModelProperty( value = "密码salt" , required = true)
    private String pwdSalt;

    @ApiModelProperty( value = "性别 0-男 1-女" , required = true)
    private Integer accGender;

    @ApiModelProperty( value = "手机号" , required = true)
    private String accTel;

    @ApiModelProperty( value = "账号状态 1-正常、2-冻结" , required = true)
    private Integer accStatus;

    @ApiModelProperty( value = "账号状态更新时间" , required = true)
    private Date statusUtime;

    @ApiModelProperty( value = "删除状态 0-未删除 1-已删除" , required = true)
    private Integer accRemove;

    @ApiModelProperty( value = "删除时间" , required = true)
    private Date removeTime;

    @ApiModelProperty( value = "创建时间" , required = true)
    private Date accCtime;

    @ApiModelProperty( value = "更新时间" , required = true)
    private Date accUtime;

    @ApiModelProperty( value = "注册者账号ID" , required = true)
    private String registerAccId;

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
