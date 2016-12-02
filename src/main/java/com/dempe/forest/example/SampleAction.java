package com.dempe.forest.example;

import com.dempe.forest.core.CompressType;
import com.dempe.forest.core.SerializeType;
import com.dempe.forest.core.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/11/28
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
@Action("sample")
public class SampleAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(SampleAction.class);

    @Autowired
    private SampleService sampleService;

    /**
     * uri:服务路由uri
     * compressType：压缩类型，目前支持:ompressNo, gizp, snappy;
     * serializeType:序列化类型，目前支持kyro, fastjson, hession2;
     * timeOut:客户端请求超时间
     * group：服务线程组，通过group实现线程隔离
     *
     * @param word
     * @return
     */
    @Interceptor(id = "printInterceptor,metricInterceptor")//拦截器，多个拦截器用逗号分隔
    @Rate(value = 1000000)//服务限速
    @Export(uri = "hello", compressType = CompressType.compressNo, serializeType = SerializeType.fastjson, timeOut = 1000, group = "sample")
    public String hello(@HttpParam String word) {
        return sampleService.hello(word);
    }

    @Export
    public void noReplyMethod() {
        // do service
        LOGGER.info("----noReplyMethod---");


    }

}
