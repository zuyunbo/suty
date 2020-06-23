package com.cloud.apigenerator.controller;

import com.cloud.apigenerator.config.AutoInterface;
import com.cloud.apigenerator.config.impl.AutoInterfaceImpl;
import com.cloud.apigenerator.service.GenerateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zuyunbo
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
    /**
     * 生成代码
     */
    @RequestMapping("/code")
    public void code(String tables, HttpServletResponse response) throws IOException {
        AutoInterface autoInterface = new AutoInterfaceImpl() {
            @Override
            public GenerateCode getGenerateCodes() {
                return super.getGenerateCodes();
            }
        };
        autoInterface.connectionJDBC();
        GenerateCode generateCodes = autoInterface.getGenerateCodes();
        generateCodes.isToExecute();
    }
}
