package com.aluraChaleng.Foro_Hub.Services;

import com.aluraChaleng.Foro_Hub.Respository.ProfileRepository;
import com.aluraChaleng.Foro_Hub.Respository.UserRepository;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.user.DtoCreateUser;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.user.DtoCreateUserToDatabase;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.user.DtoLoginDataUser;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.user.DtoUpdateUser;
import com.aluraChaleng.Foro_Hub.dto.DtoResponses.user.DtoResponseTokenData;
import com.aluraChaleng.Foro_Hub.dto.DtoResponses.user.DtoUserMoreDetails;
import com.aluraChaleng.Foro_Hub.infra.Security.TokenService;
import com.aluraChaleng.Foro_Hub.model.Profile;
import com.aluraChaleng.Foro_Hub.model.User;
import jakarta.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceUser {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public DtoResponseTokenData authenticateUser(DtoLoginDataUser dtoLoginDataUser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dtoLoginDataUser.getUsername(), dtoLoginDataUser.getPassword());
        var userAuthenticate = authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generateToken((User) userAuthenticate.getPrincipal());
        return new DtoResponseTokenData(JWTtoken, "Bearer");
    }

    public DtoUserMoreDetails findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ValidationException("User not found"));
        return new DtoUserMoreDetails(user.getCode(), user.getUsername(), user.getEmail(),
                user.getProfile().getName());
    }

    public List<DtoUserMoreDetails> findAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new DtoUserMoreDetails(user.getCode(), user.getUsername(),
                        user.getEmail(), user.getProfile().getName()))
                .collect(Collectors.toList());
    }

    public DtoUserMoreDetails createNewUser(DtoCreateUser dtoCreateUser) {
        Long profileId = Long.valueOf(dtoCreateUser.getTypeOfProfile());
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ValidationException("Profile type does not exist"));
        DtoCreateUserToDatabase dtoCreateUserToDatabase = new DtoCreateUserToDatabase(
                dtoCreateUser.getUsername(), dtoCreateUser.getEmail(),
                encryptPassword(dtoCreateUser.getPassword()), profile.getId().intValue());
        User newUser = new User(dtoCreateUserToDatabase);
        userRepository.save(newUser);
        return new DtoUserMoreDetails(newUser.getCode(), newUser.getUsername(),
                newUser.getEmail(), newUser.getProfile().getName());
    }

    public DtoUserMoreDetails updateUser(Long id, DtoUpdateUser dtoUpdateUser) {
        Profile profile = profileRepository.findById(Long.valueOf(dtoUpdateUser.getTypeOfProfile()))
                .orElseThrow(() -> new ValidationException("Profile type does not exist"));
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ValidationException("User not found"));
        user.setUsername(dtoUpdateUser.getUsername());
        user.setEmail(dtoUpdateUser.getEmail());
        user.setProfile(profile);
        userRepository.save(user);
        return new DtoUserMoreDetails(user.getCode(), user.getUsername(),
                user.getEmail(), user.getProfile().getName());
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
