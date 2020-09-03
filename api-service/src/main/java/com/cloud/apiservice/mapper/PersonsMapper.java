package com.cloud.apiservice.mapper;


import tk.mybatis.mapper.common.Mapper;
import com.cloud.apimodel.entity.Persons;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.apimodel.param.PersonsParam;


/**
 *
 * @author 2u c1111
 * @since 1.0 2020-09-02
 */
@Repository
public interface PersonsMapper extends Mapper<Persons> {


    List<Persons> listQuery(PersonsParam queryParam);

}
