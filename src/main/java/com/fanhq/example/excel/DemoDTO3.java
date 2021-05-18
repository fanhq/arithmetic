package com.fanhq.example.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
@Data
public class DemoDTO3 {

    @ExcelProperty("房屋全称")
    private String name;

    @ExcelProperty("是否入住")
    private String status;
}
