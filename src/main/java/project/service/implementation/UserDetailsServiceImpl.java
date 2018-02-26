package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Role;
import project.entity.User;
import project.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Service for finding users and roles in database.
 * Need for Spring security configuration
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user;

        if (!userRepository.findByUserName(userName).isPresent()) {
            throw new UsernameNotFoundException(userName);
        }

        user = userRepository.findByUserName(userName).get();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getUserRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), grantedAuthorities);
    }

}
