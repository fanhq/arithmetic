package com.fanhq.example.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
@Data
public class DemoDTO1 {

    @ExcelProperty("房屋全称")
    private String roomName;

    @ExcelProperty("是否交房")
    private String tokeRoom;

    @ExcelProperty("是否入住")
    private String tokeIn;

    @ExcelProperty("是否装修")
    private String tokeDo;
}
