package com.aluraChaleng.Foro_Hub.controller;

import com.aluraChaleng.Foro_Hub.Services.ServiceUser;
import com.aluraChaleng.Foro_Hub.dto.DtoGetData.user.DtoLoginDataUser;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    ServiceUser serviceUser;

    @PostMapping
    @Operation(summary = "Receive the username and password and it returns a JWT with authentication data", tags = "Authentication")
    public ResponseEntity checkAuthentication(@RequestBody DtoLoginDataUser dtoLoginDataUser)
    {
        return ResponseEntity.ok(serviceUser.authenticateUser(dtoLoginDataUser));
    }
}
