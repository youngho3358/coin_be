package com.coin.coininvestment.domain.wallet;

import com.coin.coininvestment.domain.user.Users;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Wallet {
    @Id @Column(name = "wallet_id")
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY)
    private Users user;

    private Long btc; // 비트코인
    private Long eth; // 이더리움
    private Long ada; // 에이다
    private Long sol; // 솔라나
    private Long xrp; // 리플
    private Long dot; // 폴카닷
    private Long link; // 체인링크
    private Long avax; // 아발란체
    private Long atom; // 코스모스
    private Long xlm; // 스텔라
    private Long theta; // 쎄타토큰
    private Long trx; // 트론
    private Long aave; // 에이브
    private Long eos; // 이오스
    private Long stmx; // 스톰엑스
    private Long egld; // 멀티버스엑스
    private Long neo; // 네오
    private Long hbar; // 헤데라
    private Long shib; // 시바이누
    private Long doge; // 도지코인

    public void createWallet(Users user) {
        this.user = user;
        user.createWallet(this);
    }
}
