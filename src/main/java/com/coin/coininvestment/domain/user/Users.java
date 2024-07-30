package com.coin.coininvestment.domain.user;

import com.coin.coininvestment.domain.trade.Trade;
import com.coin.coininvestment.domain.wallet.Wallet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id @Column(name = "user_id")
    @GeneratedValue
    private Long id;
    @Column(length = 50, unique = true, nullable = false)
    private String email;
    @Column(length = 200)
    private String password;
    @Column(length = 20, unique = true, nullable = false)
    private String nickname;
    private int assetKrw;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Trade> trades = new ArrayList<>();

    public Users(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void createWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
