package kolesov.maksim.mapping.auth.model;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public enum Role {

    CREATE_MAP,
    DELETE_OWN_MAP,
    DELETE_ANY_MAP(true),
    EDIT_OWN_MAP,
    EDIT_ANY_MAP(true),

    EDIT_USERS(true),
    BAN_USER(true)
    ;

    private final boolean isAdminRole;

    Role() {
        this.isAdminRole = false;
    }

    public static List<Role> getBasicRoles() {
        return Arrays.stream(values())
                .filter(r -> !r.isAdminRole)
                .toList();
    }

}
