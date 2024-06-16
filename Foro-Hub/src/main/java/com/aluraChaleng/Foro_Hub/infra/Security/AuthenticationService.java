package com.aluraChaleng.Foro_Hub.infra.Security;


import com.aluraChaleng.Foro_Hub.Respository.UserRepository;
import com.aluraChaleng.Foro_Hub.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    @Autowired
    UserRepository usersRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        return usersRepository.findByUsername(username);
    }
}
