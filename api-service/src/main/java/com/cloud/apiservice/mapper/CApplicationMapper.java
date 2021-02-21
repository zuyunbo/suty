package com.cloud.apiservice.mapper;


import tk.mybatis.mapper.common.Mapper;
import com.cloud.apimodel.entity.CApplication;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.apimodel.param.CApplicationParam;


/**
 *
 * @author 2u c1111
 * @since 1.0 2021-01-27
 */
@Repository
public interface CApplicationMapper extends Mapper<CApplication> {


    List<CApplication> listQuery(CApplicationParam queryParam);

}
