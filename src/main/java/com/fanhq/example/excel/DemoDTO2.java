package com.fanhq.example.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
@Data
public class DemoDTO2 {

    @ExcelProperty("房间代码（根据实际情况而定要不要）")
    private String room;

    @ExcelProperty("地址（尽可能祥细以免无法投递）(必填)")
    private String addr;
}
