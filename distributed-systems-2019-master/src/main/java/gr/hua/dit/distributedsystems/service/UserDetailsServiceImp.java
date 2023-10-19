package gr.hua.dit.distributedsystems.service;

import gr.hua.dit.distributedsystems.dao.UserDetailsDao;
import gr.hua.dit.distributedsystems.model.Permission;
import gr.hua.dit.distributedsystems.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDetailsDao.findUserByUsername(username);
        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(!(user.getState() == User.UserState.APPROVED));
            builder.password(user.getPassword());

            String[] authorities = user.getRole().getPermissions().stream().map(Permission::getName).toArray(String[]::new);
//            String[] authorities = user.getAuthorities().stream().map(a -> a.getAuthority()).toArray(String[]::new);

            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();

    }

}
