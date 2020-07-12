package com.gouwo.bo;

import com.gouwo.model.RoleModel;
import com.gouwo.model.UserModel;
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

    private UserModel userModel;

    private List<RoleModel> roleList;

    public AdminUserDetails(UserModel userModel, List<RoleModel> roleList) {
        this.userModel = userModel;
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
        return userModel.getPassword();
    }

    @Override
    public String getUsername() {
        return userModel.getAccount();
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
