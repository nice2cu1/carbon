package com.neu.carbon.report.controller;

import com.neu.carbon.report.mapper.SaleAmountMapper;
import com.neu.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"数据中心"})
@RestController
@RequestMapping("/saleAmountReport")
public class SaleAmountController {
    @Autowired
    SaleAmountMapper saleAmountMapper;

    @GetMapping("/amount")
    public AjaxResult saleAmount() {
        List<Object> objects = saleAmountMapper.selectSaleAmount();
        return AjaxResult.success(objects);
    }

}
