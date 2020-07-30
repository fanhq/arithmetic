package com.fanhq.example.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
@Data
public class DemoDTO1 {

    @ExcelProperty("新核实联系电话")
    private String tel;

    @ExcelProperty("新核实地址")
    private String addr;
}
