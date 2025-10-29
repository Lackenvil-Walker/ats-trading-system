package com.ats.swaps.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "swap_trades")
public class SwapTrade {

    @Id
    private UUID id;
    private LocalDate tradeDate;
    private String counterparty;
    private BigDecimal notional;
    private BigDecimal fixedRate;
    private String floatingIndex;
    private String status;

    public SwapTrade() {
    }

    public SwapTrade(UUID id, LocalDate tradeDate, String counterparty,
                     BigDecimal notional, BigDecimal fixedRate,
                     String floatingIndex, String status) {
        this.id = id;
        this.tradeDate = tradeDate;
        this.counterparty = counterparty;
        this.notional = notional;
        this.fixedRate = fixedRate;
        this.floatingIndex = floatingIndex;
        this.status = status;
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }

    public String getCounterparty() { return counterparty; }
    public void setCounterparty(String counterparty) { this.counterparty = counterparty; }

    public BigDecimal getNotional() { return notional; }
    public void setNotional(BigDecimal notional) { this.notional = notional; }

    public BigDecimal getFixedRate() { return fixedRate; }
    public void setFixedRate(BigDecimal fixedRate) { this.fixedRate = fixedRate; }

    public String getFloatingIndex() { return floatingIndex; }
    public void setFloatingIndex(String floatingIndex) { this.floatingIndex = floatingIndex; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
