package com.cloud.managebackend.controller;


import com.cloud.apimodel.entity.UserInfo;
import com.cloud.apimodel.param.UserInfoQueryParam;
import com.cloud.apiservice.service.UserInfoService;
import com.example.commoncenter.base.BaseResponseUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user/userInfo")
public class UserInfoController {


    @Autowired
    UserInfoService userInfoService;

    @PostMapping(value = "/save")  //restful 风格的 如果不懂可以先去了解一下
    public Object save(@RequestBody UserInfo entity) {
        int result = userInfoService.save(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "保存成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "保存失败", result);
        }
    }

    @GetMapping(value = "/{id}/detail")  //restful 风格的 如果不懂可以先去了解一下
    public Object detail(@PathVariable long id) {
        UserInfo entity = userInfoService.getById(id);
        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", entity);
    }

    @PutMapping(value = "/update")    //restful 风格的 如果不懂可以先去了解一下
    public Object update(@RequestBody UserInfo entity) {
        int result = userInfoService.update(entity);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "修改成功", entity.getId());
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "修改失败");
        }
    }

    @DeleteMapping(value = "/{id}")   //restful 风格的 如果不懂可以先去了解一下
    public Object remove(@PathVariable long id) {
        int result = userInfoService.remove(id);

        if (result > 0) {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "删除成功");
        } else {
            return BaseResponseUtil.constructResponse(BaseResponseUtil.FAILED, "删除失败");
        }
    }

    @GetMapping(value = "/list")    //restful 风格的 如果不懂可以先去了解一下
    public Object list(UserInfoQueryParam queryParam) {
        PageInfo pageInfo = userInfoService.pageQuery(queryParam);

        return BaseResponseUtil.constructResponse(BaseResponseUtil.SUCCESS, "查询成功", pageInfo);
    }
}

