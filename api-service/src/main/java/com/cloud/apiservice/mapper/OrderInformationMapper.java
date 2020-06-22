package com.cloud.apiservice.mapper;

import com.cloud.apimodel.entity.OrderInformation;
import com.cloud.apimodel.param.OrderInformationQueryParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *
 */
@Repository
public interface OrderInformationMapper extends Mapper<OrderInformation> {

    List<OrderInformation> listQuery(OrderInformationQueryParam queryParam);
}
