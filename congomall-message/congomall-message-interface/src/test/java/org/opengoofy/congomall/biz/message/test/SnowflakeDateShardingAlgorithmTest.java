/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opengoofy.congomall.biz.message.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengoofy.congomall.biz.message.infrastructure.dao.entity.MailSendRecordDO;
import org.opengoofy.congomall.biz.message.infrastructure.dao.mapper.MailSendRecordMapper;
import org.opengoofy.congomall.biz.message.web.MessageApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义复合分片算法单元测试
 *
 * @author chen.ma
 * @github https://github.com/opengoofy
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageApplication.class)
public class SnowflakeDateShardingAlgorithmTest {
    
    @Resource
    private MailSendRecordMapper mailSendRecordMapper;
    
    @Test
    public void testSendTimeQuery() {
        LambdaQueryWrapper<MailSendRecordDO> queryWrapper = Wrappers.lambdaQuery(MailSendRecordDO.class)
                .eq(MailSendRecordDO::getSendTime, DateUtil.now());
        executeQuery(queryWrapper);
    }
    
    @Test
    public void testSnowflakeQuery() {
        LambdaQueryWrapper<MailSendRecordDO> queryWrapper = Wrappers.lambdaQuery(MailSendRecordDO.class)
                .eq(MailSendRecordDO::getMessageSendId, 1547434279292878848L);
        executeQuery(queryWrapper);
    }
    
    @Test
    public void testBetweenQuery() {
        LambdaQueryWrapper<MailSendRecordDO> queryWrapper = Wrappers.lambdaQuery(MailSendRecordDO.class)
                .between(MailSendRecordDO::getSendTime, DateUtil.parse("2023-07-14 12:12:18"), DateUtil.parse("2024-08-14 12:15:18"));
        executeQuery(queryWrapper);
    }
    
    private void executeQuery(LambdaQueryWrapper queryWrapper) {
        List<MailSendRecordDO> mailSendRecords = mailSendRecordMapper.selectList(queryWrapper);
        log.info("mailSendRecords: {}", JSON.toJSONString(mailSendRecords));
    }
}