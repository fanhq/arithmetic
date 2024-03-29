package com.fanhq.example.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2020/7/30
 */
public class DemoDataListener3 extends AnalysisEventListener<DemoDTO3> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener3.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    private List<DemoDTO3> list ;
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    public DemoDataListener3(List<DemoDTO3> list) {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        this.list = list;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DemoDTO3 data, AnalysisContext context) {
        list.add(data);
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        LOGGER.info("所有数据解析完成！");
    }
}
