/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.controller;

import com.ksm.server.dto.LoginRequestDTO;
import com.ksm.server.dto.LoginResponseDTO;
import com.ksm.server.dto.RegisterDTO;
import com.ksm.server.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aqira
 */
@RestController
@RequestMapping("/api")
public class UserManagementController {

    @Autowired
    UserManagementService service;

    @PostMapping("login") //email + password
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        return service.login(loginRequest);
    }

    @PostMapping("register")
    public ResponseEntity<Boolean> register(@RequestBody RegisterDTO registerData) {
        return service.register(registerData);
    }

}
