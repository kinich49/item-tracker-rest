package mx.kinich49.itemtracker.dtos;

import lombok.Data;

@Data
public class UserUserSecurityPropertiesDto {

    private final Long userId;
    private final Long userSecurityPropertiesId;
    private final String username;
    private final String password;
    private final boolean isAccountNonExpired;
    private final boolean isAccountNonLocked;
    private final boolean isCredentialsNonExpired;
    private final boolean isEnabled;

}
