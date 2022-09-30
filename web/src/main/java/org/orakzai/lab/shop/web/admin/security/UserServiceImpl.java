package org.orakzai.lab.shop.web.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.orakzai.lab.shop.domain.business.merchant.model.MerchantStore;
import org.orakzai.lab.shop.domain.business.merchant.service.MerchantStoreService;
import org.orakzai.lab.shop.domain.business.user.model.Group;
import org.orakzai.lab.shop.domain.business.user.model.GroupType;
import org.orakzai.lab.shop.domain.business.user.model.Permission;
import org.orakzai.lab.shop.domain.business.user.model.User;
import org.orakzai.lab.shop.domain.business.user.service.GroupService;
import org.orakzai.lab.shop.domain.business.user.service.PermissionService;
import org.orakzai.lab.shop.domain.business.user.service.UserService;
import org.orakzai.lab.shop.web.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service("userDetailsService")
public class UserServiceImpl implements WebUserServices {
	
	@Autowired
    MerchantStoreService merchantStoreService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;
	
	@Override
	public void createDefaultAdmin() throws Exception {
		MerchantStore store = merchantStoreService.getMerchantStore(MerchantStore.DEFAULT_STORE);
        String password = passwordEncoder.encode("password");
        List<Group> groups = groupService.listGroup(GroupType.ADMIN);
        User user = new User("admin", password, "admin@shopizer.com");
        user.setFirstName("Administrator");
        user.setLastName("user");
        if (groups != null) {
            for (Group group : groups) {
                if (group.getGroupName().equals(Constants.GROUP_SUPERADMIN) || group.getGroupName().equals(Constants.GROUP_ADMIN)) {
                    user.getGroups().add(group);
                }
            }
        }
        System.out.println(store);
        user.setMerchantStore(store);
        System.out.println(user.getMerchantStore());
        userService.create(user);
			
	}
	
	@Override
	@Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        try {
            user = userService.getByUserName(username);
            if (user == null) {
                return null;
            }

            var groupsId = new ArrayList<Integer>();
            List<Group> groups = user.getGroups();
            for (Group group : groups) {
                groupsId.add(group.getId());
            }

            List<Permission> permissions = permissionService.getPermissions(groupsId);
            for (Permission permission : permissions) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + permission.getPermissionName());
                authorities.add(authority);
            }

        } catch (Exception e) {
            log.error("Exception while quireing user", e);
            throw new SecurityDataAccessException("Exception while quiring user", e);
        }

        org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(
                username, user.getAdminPassword(), user.isActive(), true, true, true, authorities);

        return secUser;
    }

}
