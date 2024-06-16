package com.aluraChaleng.Foro_Hub.controller;

import com.aluraChaleng.Foro_Hub.Services.ServiceTopic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/topicsorder")
@SecurityRequirement(name = "bearer-key")
public class TopicsOrderController
{
    @Autowired
    ServiceTopic serviceTopic;

    @GetMapping
    @Operation(summary = "Return the last records of topics", tags = "Get")
    public ResponseEntity getAllTopics(@PageableDefault(size = 10) Pageable pagination)
    {
        return ResponseEntity.ok(serviceTopic.findLastTenRecords());
    }
}