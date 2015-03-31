package org.ojbc.web.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
/**
 * This class is needed only because we are using velocity and are not able to use 
 * Spring security tags for JSP pages. 
 */
@Component
public class VelocitySecUser {

    /**
     * Gets the user name of the user from the Authentication object
     *
     * @return the user name as string
     */
    public static String getPrincipal() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (obj instanceof UserDetails) {
            return ((UserDetails) obj).getUsername();
        } else {
            return "Guest";
        }
    }

    /**
     * Is the user granted all of the grantedAuthorities passed in
     *
     * @param roles
     *            a string array of grantedAuth
     * @return true if user has all of the listed authorities/roles, otherwise
     *         false
     */
    public static boolean allGranted(String... checkForAuths) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                continue;
            return false;
        }
        return true;
    }

    /**
     * Is the user granted any of the grantedAuthorities passed into
     *
     * @param roles
     *            a string array of grantedAuth
     * @return true if user has any of the listed authorities/roles, otherwise
     *         false
     */
    public static boolean anyGranted(String... checkForAuths) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                return true;
        }
        return false;
    }

    /**
     * is the user granted none of the supplied roles
     *
     * @param roles
     *            a string array of roles
     * @return true only if none of listed roles are granted
     */
    public static boolean noneGranted(String... checkForAuths) {
        Set<String> userAuths = getUserAuthorities();
        for (String auth : checkForAuths) {
            if (userAuths.contains(auth))
                return false;
        }
        return true;
    }

    private static Set<String> getUserAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<String> roles = new HashSet<String>();
        @SuppressWarnings("unchecked")
        Collection<SimpleGrantedAuthority> gas = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities(); 
        for (GrantedAuthority ga : gas) {
            roles.add(ga.getAuthority());
        }
        return roles;
    }
}