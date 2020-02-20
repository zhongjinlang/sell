package com.imooc.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    /** org.slf4j.event.Level
     *     ERROR(40, "ERROR"),
     *     WARN(30, "WARN"),
     *     INFO(20, "INFO"),
     *     DEBUG(10, "DEBUG"),
     *     TRACE(0, "TRACE");
     *
     *  系统默认级别为info, 只会输出info之上
     */
    @Test
    public void testLog(){
      log.debug("log debug..."); // 不输出
      log.info("log info...");
      log.error("log error...");
    }
}
