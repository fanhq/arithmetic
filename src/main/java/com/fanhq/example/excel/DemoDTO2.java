package com.fanhq.example.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
@Data
public class DemoDTO2 {

    @ExcelProperty("房屋全称")
    private String room;
}
