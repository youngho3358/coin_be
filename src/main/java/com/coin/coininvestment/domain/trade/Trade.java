package com.coin.coininvestment.domain.trade;
import com.coin.coininvestment.domain.user.Users;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
public class Trade {
    @Id @Column(name = "trade_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Coin coin;

    /* 코인 판매량 */
    private Long amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime completeDate;

    /* 코인 판매 시 코인 1개당 가격 */
    private Long cost;




    @PrePersist // 엔티티 생성 시 자동으로 생성 시점을 등록
    protected void onCreate() {
        this.registerDate = LocalDateTime.now();
    }
}
