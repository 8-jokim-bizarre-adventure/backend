package com.jokim.sivillage.api.trending.mostviewproduct.application;

import com.jokim.sivillage.api.trending.mostviewproduct.dto.MostViewProductDto;
import java.util.List;

public interface MostViewProductService {
    List<MostViewProductDto> getMostViewProduct();
}
