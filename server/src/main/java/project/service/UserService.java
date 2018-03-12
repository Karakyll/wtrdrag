package project.service;

import project.entity.User;

import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByUserName(String userName);

}
