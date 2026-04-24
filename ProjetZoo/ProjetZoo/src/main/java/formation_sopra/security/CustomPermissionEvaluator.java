package formation_sopra.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class CustomPermissionEvaluator implements PermissionEvaluator{

    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission){
        if (targetDomainObject == null) {
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        return this.hasPrivilege(auth, targetType, permission.toString());
    }

    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission){
        return this.hasPrivilege(auth, targetType, permission.toString());
    }

    public boolean hasPrivilege(Authentication auth, String targetType, String permission){
        if ((auth == null) || (targetType == null) || !(permission instanceof String)){
            return false;
        }
        return auth.getAuthorities().stream()
            .anyMatch(gauth -> gauth.getAuthority().startsWith(targetType.toUpperCase() + "_" + permission.toUpperCase())
        );
    }

}
