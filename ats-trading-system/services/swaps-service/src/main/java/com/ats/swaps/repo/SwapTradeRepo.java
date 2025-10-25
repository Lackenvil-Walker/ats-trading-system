package com.ats.swaps.repo;

import com.ats.swaps.domain.SwapTrade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SwapTradeRepo extends JpaRepository<SwapTrade, UUID>{}