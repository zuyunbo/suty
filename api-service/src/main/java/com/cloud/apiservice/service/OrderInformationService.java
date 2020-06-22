package com.cloud.apiservice.service;

import com.cloud.apimodel.entity.OrderInformation;
import com.cloud.apimodel.param.OrderInformationQueryParam;
import com.example.commoncenter.base.BaseServiceInterface;
import org.springframework.stereotype.Service;

/**
 *
 */
public interface OrderInformationService extends BaseServiceInterface<OrderInformation, OrderInformationQueryParam, Long> {
}
