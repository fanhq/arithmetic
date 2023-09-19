package com.fanhq.example.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
@Data
public class DemoDTO1 {

    @ExcelProperty("资源名称")
    private String name;
    private byte[] na;
}
