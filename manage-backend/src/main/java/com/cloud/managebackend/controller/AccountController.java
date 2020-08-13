package com.cloud.managebackend.controller;

import com.cloud.apimodel.entity.Account;
import com.cloud.apimodel.param.AccountParam;
import com.cloud.apiservice.service.AccountService;
import com.example.commoncenter.base.BaseResponseUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;


/**
 * ${comments}
 *
 * @author admin c1111
 * @since 1.0 2020-07-02
 */
@RestController
@RequestMapping("/api/account")
@Api(tags="Account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    Log log = LogFactory.getLog(this.getClass());


    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam( value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam( value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam( value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    public Object page(AccountParam queryParam){
        PageInfo pageInfo = accountService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", pageInfo);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("删除")
    public Object remove(@PathVariable long id) {
        int result = accountService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "删除成功");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "删除失败");
        }
    }


    @PostMapping(value = "/save")
    @ApiOperation("保存")
    public Object save(@RequestBody Account entity) {
        int result = accountService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "保存成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "保存失败", result);
        }
    }

    @PutMapping(value = "/update")
    @ApiOperation("修改")
    public Object update(@RequestBody Account entity) {
        int result = accountService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "修改成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "修改失败");
        }
    }


    @GetMapping(value = "/{id}/detail")
    @ApiOperation("详情")
    public Object detail(@PathVariable long id) {
        Account entity = accountService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", entity);
    }

    /**
     * 合营商绑定
     * @param
     * @return
     */
    @PostMapping("/copBind")
    @ResponseBody
    public Map<String, Object> copBind() {
        accountService.getExcted();
        return new HashMap<>();
    }



    /**
     * 合营商绑定
     * @param
     * @return
     */
    @RequestMapping(value = {"/copBind1"},method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> copBind1() {
        Map<String, Object> result = new HashMap<>();
        result.put("1",1);
        return result;
    }



}
