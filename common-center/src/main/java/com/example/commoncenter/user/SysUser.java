package com.example.commoncenter.user;

import com.example.commoncenter.base.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 *
 * @author 2u c1111
 * @since 1.0 2020-11-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class SysUser extends BaseEntity  implements UserDetails {
	private static final long serialVersionUID = 1L;

    /**
     * 同步工作流引擎(1-同步,0-不同步)
     */
     private Byte activitiSync;
    /**
     * 头像
     */
     private String avatar;
    /**
     * 生日
     */
     private String birthday;
    /**
     * 设备ID
     */
     private String clientId;
    /**
     * 创建人
     */
     private String createBy;
    /**
     * 创建时间
     */
     private String createTime;
    /**
     * 删除状态(0-正常,1-已删除)
     */
     private Byte delFlag;

    /**
     * 电子邮件
     */
     private String email;
    /**
     * 机构编码
     */
     private String orgCode;
    /**
     * 密码
     */
     private String password;
    /**
     * 电话
     */
     private String phone;
    /**
     * 职务，关联职务表
     */
     private String post;
    /**
     * 真实姓名
     */
     private String realname;
    /**
     * 多租户标识
     */
     private String relTenantIds;
    /**
     * md5密码盐
     */
     private String salt;
    /**
     * 性别(0-默认未知,1-男,2-女)
     */
     private Byte sex;
    /**
     * 性别(1-正常,2-冻结)
     */
     private Byte status;
    /**
     * 座机号
     */
     private String telephone;
    /**
     * 第三方登录的唯一标识
     */
     private String thirdId;
    /**
     * 第三方类型
     */
     private String thirdType;
    /**
     * 更新人
     */
     private String updateBy;
    /**
     * 更新时间
     */
     private String updateTime;
    /**
     * 身份（1普通成员 2上级）
     */
     private Byte userIdentity;
    /**
     * 登录账号
     */
     private String username;
    /**
     * 工号，唯一键
     */
     private String workNo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
