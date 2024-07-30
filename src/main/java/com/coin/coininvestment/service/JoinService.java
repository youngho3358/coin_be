package com.coin.coininvestment.service;

import com.coin.coininvestment.domain.user.Role;
import com.coin.coininvestment.domain.user.Users;
import com.coin.coininvestment.domain.wallet.Wallet;
import com.coin.coininvestment.dto.JoinDTO;
import com.coin.coininvestment.exception.join.ExistEmailException;
import com.coin.coininvestment.exception.join.ExistNicknameException;
import com.coin.coininvestment.repository.user.UsersRepository;
import com.coin.coininvestment.repository.wallet.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final WalletRepository walletRepository;
    public ResponseEntity<String> joinProcess(JoinDTO joinDTO) {

        // 이메일 중복 시 예외처리 로직
        if(usersRepository.existsByEmail(joinDTO.getEmail())) throw new ExistEmailException("Already Exist Email");

        // 닉네임 중복 시 예외처리 로직
        if(usersRepository.existsByNickname(joinDTO.getNickname())) throw new ExistNicknameException("Already Exist Nickname");

        Users user = Users.builder()
                .email(joinDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
                .nickname(joinDTO.getNickname())
                .assetKrw(1000000)
                .role(Role.ROLE_USER)
                .build();

        usersRepository.save(user);

        // 지갑 생성
        makeWallet(user);

        return new ResponseEntity<String>("Join Success", HttpStatus.OK);
    }

    private void makeWallet(Users user) {
        Wallet wallet = new Wallet();
        wallet.createWallet(user);
        walletRepository.save(wallet);
    }
}
