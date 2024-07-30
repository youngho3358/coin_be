package com.coin.coininvestment.controller;

import com.coin.coininvestment.dto.JoinDTO;
import com.coin.coininvestment.service.JoinService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/join")
public class JoinController {
    private final JoinService joinService;
    @PostMapping("/user")
    public ResponseEntity<String> joinProcess(@RequestBody JoinDTO joinDTO) {
        return joinService.joinProcess(joinDTO);
    }
}
