package com.aluraChaleng.Foro_Hub.controller;


import com.aluraChaleng.Foro_Hub.Services.ServiceUser;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.user.DtoCreateUser;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.user.DtoUpdateUser;
import com.aluraChaleng.Foro_Hub.dto.DtoResponses.user.DtoUserMoreDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController
{
    @Autowired
    ServiceUser serviceUser;

    @GetMapping
    @Operation(summary = "Find all users", tags = "Get")
    public ResponseEntity findAllUsers()
    {
        List<DtoUserMoreDetails> dtoUserMoreDetailsList = serviceUser.findAllUsers();

        return ResponseEntity.ok(dtoUserMoreDetailsList);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get the information of user by id", tags = "Get")
    public ResponseEntity findUserById(@PathVariable Integer id)
    {
        DtoUserMoreDetails dtoUserMoreDetails = serviceUser.findUserById(Long.valueOf(id));

        return ResponseEntity.ok(dtoUserMoreDetails);
    }

    @PostMapping
    @Operation(summary = "Create a new user", tags = "Post")
    public ResponseEntity createUser(@RequestBody @Valid DtoCreateUser dtoCreateUser, UriComponentsBuilder uriComponentsBuilder)
    {
        DtoUserMoreDetails dtoUserMoreDetails = serviceUser.createNewUser(dtoCreateUser);

        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(dtoUserMoreDetails.code()).toUri();

        return ResponseEntity.created(url).body(dtoUserMoreDetails);
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Update information of a user", tags = "Put")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody DtoUpdateUser dtoUpdateUser)
    {
        DtoUserMoreDetails dtoUserMoreDetails = serviceUser.updateUser(Long.valueOf(id), dtoUpdateUser);

        return ResponseEntity.ok(dtoUserMoreDetails);
    }
}