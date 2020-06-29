package com.gouwo.bo;

import com.gouwo.model.PeoRoleModel;
import com.gouwo.model.PeoUserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 */
public class AdminUserDetails implements UserDetails {

    private PeoUserModel peoUserModel;
    private List<PeoRoleModel> roleList;

    public AdminUserDetails(PeoUserModel peoUserModel, List<PeoRoleModel> roleList) {
        this.peoUserModel = peoUserModel;
        this.roleList = roleList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return roleList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getRoleCode()+":"+role.getRoleCode()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return peoUserModel.getPassword();
    }

    @Override
    public String getUsername() {
        return peoUserModel.getUserName();
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
