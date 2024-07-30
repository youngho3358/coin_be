package com.coin.coininvestment.repository.wallet;

import com.coin.coininvestment.domain.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
