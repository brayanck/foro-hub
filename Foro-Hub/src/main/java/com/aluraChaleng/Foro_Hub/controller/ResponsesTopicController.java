package com.aluraChaleng.Foro_Hub.controller;


import com.aluraChaleng.Foro_Hub.Services.ServiceResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/responsestopic")
@SecurityRequirement(name = "bearer-key")
public class ResponsesTopicController
{
    @Autowired
    ServiceResponses servicesResponses;

    @GetMapping("/{id}")
    @Operation(summary = "Get the responses by Topic", tags = "Get")
    public ResponseEntity getResponsesByTopic(@PathVariable Integer id)
    {
        return ResponseEntity.ok(servicesResponses.getResponsesByTopic(id));
    }
}
