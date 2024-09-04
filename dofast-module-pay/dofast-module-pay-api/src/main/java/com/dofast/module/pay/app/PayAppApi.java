package com.dofast.module.pay.app;


import com.dofast.module.pay.app.dto.PayAppResDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

public interface PayAppApi {
    PayAppResDTO getApp();
}
