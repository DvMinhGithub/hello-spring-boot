package com.mdv.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdv.identity_service.dto.request.RoleRequest;
import com.mdv.identity_service.dto.response.ApiRespone;
import com.mdv.identity_service.dto.response.RoleResponse;
import com.mdv.identity_service.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiRespone<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiRespone.<RoleResponse>builder()
                .code(201)
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiRespone<List<RoleResponse>> getAll() {
        return ApiRespone.<List<RoleResponse>>builder()
                .code(200)
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{name}")
    ApiRespone<Void> delete(@PathVariable String name) {
        roleService.delete(name);
        return ApiRespone.<Void>builder()
                .code(200)
                .message("Deleted role")
                .build();
    }
}