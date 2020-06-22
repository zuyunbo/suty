package com.cloud.managebackend.controller;

import com.cloud.apimodel.entity.OrderInformation;
import com.cloud.apimodel.param.OrderInformationQueryParam;
import com.cloud.apiservice.service.OrderInformationService;
import com.example.commoncenter.base.BaseResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 *
 */
@RestController
@RequestMapping("/order/orderInformation")
@Api(tags = "订单信息表 ")
public class OrderInformationController {

    @Resource
    OrderInformationService orderInformationService;

    @ApiOperation(value = "保存")
    @PostMapping(value = "/save")
    public Object save(@RequestBody @Valid OrderInformation entity) {
        int result = orderInformationService.save(entity);
        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "保存成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "保存失败", result);
        }
    }

    @ApiOperation(value = "信息")
    @GetMapping(value = "/{id}/detail")
    public Object detail(@PathVariable Long id) {
        OrderInformation entity = orderInformationService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", entity);
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/update")
    public Object update(@RequestBody OrderInformation entity) {
        int result = orderInformationService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "修改成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "修改失败");
        }
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/{id}")
    public Object delete(@PathVariable long id) {
        int result = orderInformationService.remove(id);
        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "删除成功");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "删除失败");
        }
    }

    @ApiOperation(value = "分页")
    @GetMapping(value = "/list")
    public Object getList(OrderInformationQueryParam queryParam) {
        PageInfo pageInfo = orderInformationService.pageQuery(queryParam);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", pageInfo);
    }
}
