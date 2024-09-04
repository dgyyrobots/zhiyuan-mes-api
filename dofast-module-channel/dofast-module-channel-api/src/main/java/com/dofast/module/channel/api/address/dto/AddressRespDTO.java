package com.dofast.module.channel.api.address.dto;

import lombok.Data;

@Data
public class AddressRespDTO {

        private String refOid;


        private String posCode;


        private String mobile;


        private String refType;


        private String receiverCountry;


        private String receiverState;


        private String receiverCity;


        private String receiverDistrict;


        private String receiverTown;


        private String receiverId;


        private String openBuyerNick;


        private String openBuyerId;


        private String remark;


        private Integer addressId;


        private Integer userId;

        private Integer tenantId;
}
