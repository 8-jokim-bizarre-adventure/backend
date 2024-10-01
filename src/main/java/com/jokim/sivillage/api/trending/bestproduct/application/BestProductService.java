package com.jokim.sivillage.api.trending.bestproduct.application;

import com.jokim.sivillage.api.trending.bestproduct.dto.BestProductResponseDto;
import java.util.List;

public interface BestProductService {
    List<BestProductResponseDto> getBestProduct();
}
