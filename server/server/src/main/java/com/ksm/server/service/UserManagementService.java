/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.server.service;

import com.ksm.server.dto.LoginRequestDTO;
import com.ksm.server.dto.LoginResponseDTO;
import com.ksm.server.dto.RegisterDTO;
import com.ksm.server.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ksm.server.repository.AccountRepository;

/**
 *
 * @author aqira
 */
@Service
public class UserManagementService {

    @Autowired
    AccountRepository accountRepository;

    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginData) {
        Account account = accountRepository.findByEmail(loginData.getEmail());

        // cek email ada di db ato ngga,
        // cek loginData.password == account.password
        // Response -> 503, 404, 200
        LoginResponseDTO loginResponse = new LoginResponseDTO(
                account.getEmail(),
                account.getAccountRoles());

        return ResponseEntity.ok(loginResponse);
    }

    public ResponseEntity<Boolean> register(RegisterDTO registerData) {
        //jika employee baru sudah ada di database, maka jangan bikin employee baru.
        //jika email sudah ada
        //jika id sudah ada

        //jika memang memenuhi semua pengecualian, maka save, dan return true
        Account account = new Account(
                registerData.getId(),
                registerData.getFirstName(),
                registerData.getLastName(),
                registerData.getPhone(),
                registerData.getAddress(),
                registerData.getEmail(),
                registerData.getPassword()
        );

        //Tambahkan Default Role
        // masukin new Role(1, "employee")
        accountRepository.save(account);

        return ResponseEntity.accepted().body(true);
    }
}
