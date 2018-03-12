package project.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.Role;
import project.entity.User;
import project.repository.RoleRepository;
import project.repository.UserRepository;
import project.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Class for user service operations
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    /**
     * Save new user in database.
     * Set user role = ROLE_USER.
     * To grand ROLE_ADMIN ..TODO..
     * @param user - user exemplar
     * @return user
     */
    @Override
    public User save(User user) {
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setUserRoles(roles);
        return userRepository.save(user);
    }

    /**
     * Find user with NAME in database
     * @param userName - user name
     * @return - optional user
     */
    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
