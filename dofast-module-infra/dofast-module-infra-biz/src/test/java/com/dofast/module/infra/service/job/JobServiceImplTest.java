package com.dofast.module.infra.service.job;

import com.dofast.framework.common.pojo.PageResult;
import com.dofast.framework.quartz.core.scheduler.SchedulerManager;
import com.dofast.framework.test.core.ut.BaseDbUnitTest;
import com.dofast.module.infra.controller.admin.job.vo.job.JobCreateReqVO;
import com.dofast.module.infra.controller.admin.job.vo.job.JobExportReqVO;
import com.dofast.module.infra.controller.admin.job.vo.job.JobPageReqVO;
import com.dofast.module.infra.controller.admin.job.vo.job.JobUpdateReqVO;
import com.dofast.module.infra.dal.dataobject.job.JobDO;
import com.dofast.module.infra.dal.mysql.job.JobMapper;
import com.dofast.module.infra.enums.job.JobStatusEnum;
import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.hutool.core.util.RandomUtil.randomEle;
import static com.dofast.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static com.dofast.framework.test.core.util.AssertUtils.assertPojoEquals;
import static com.dofast.framework.test.core.util.AssertUtils.assertServiceException;
import static com.dofast.framework.test.core.util.RandomUtils.randomPojo;
import static com.dofast.framework.test.core.util.RandomUtils.randomString;
import static com.dofast.module.infra.enums.ErrorCodeConstants.*;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@Import(JobServiceImpl.class)
public class JobServiceImplTest extends BaseDbUnitTest {

    @Resource
    private JobServiceImpl jobService;
    @Resource
    private JobMapper jobMapper;
    @MockBean
    private SchedulerManager schedulerManager;

    @Test
    public void testCreateJob_cronExpressionValid() {
        // 准备参数。Cron 表达式为 String 类型，默认随机字符串。
        JobCreateReqVO reqVO = randomPojo(JobCreateReqVO.class);

        // 调用，并断言异常
        assertServiceException(() -> jobService.createJob(reqVO), JOB_CRON_EXPRESSION_VALID);
    }

    @Test
    public void testCreateJob_jobHandlerExists() throws SchedulerException {
        // 准备参数 指定 Cron 表达式
        JobCreateReqVO reqVO = randomPojo(JobCreateReqVO.class, o -> o.setCronExpression("0 0/1 * * * ? *"));

        // 调用
        jobService.createJob(reqVO);
        // 调用，并断言异常
        assertServiceException(() -> jobService.createJob(reqVO), JOB_HANDLER_EXISTS);
    }

    @Test
    public void testCreateJob_success() throws SchedulerException {
        // 准备参数 指定 Cron 表达式
        JobCreateReqVO reqVO = randomPojo(JobCreateReqVO.class, o -> o.setCronExpression("0 0/1 * * * ? *"));

        // 调用
        Long jobId = jobService.createJob(reqVO);
        // 断言
        assertNotNull(jobId);
        // 校验记录的属性是否正确
        JobDO job = jobMapper.selectById(jobId);
        assertPojoEquals(reqVO, job);
        assertEquals(JobStatusEnum.NORMAL.getStatus(), job.getStatus());
        // 校验调用
        verify(schedulerManager).addJob(eq(job.getId()), eq(job.getHandlerName()), eq(job.getHandlerParam()),
                eq(job.getCronExpression()), eq(reqVO.getRetryCount()), eq(reqVO.getRetryInterval()));
    }

    @Test
    public void testUpdateJob_jobNotExists(){
        // 准备参数
        JobUpdateReqVO reqVO = randomPojo(JobUpdateReqVO.class, o -> o.setCronExpression("0 0/1 * * * ? *"));

        // 调用，并断言异常
        assertServiceException(() -> jobService.updateJob(reqVO), JOB_NOT_EXISTS);
    }

    @Test
    public void testUpdateJob_onlyNormalStatus(){
        // mock 数据
        JobDO job = randomPojo(JobDO.class, o -> o.setStatus(JobStatusEnum.INIT.getStatus()));
        jobMapper.insert(job);
        // 准备参数
        JobUpdateReqVO updateReqVO = randomPojo(JobUpdateReqVO.class, o -> {
            o.setId(job.getId());
            o.setCronExpression("0 0/1 * * * ? *");
        });

        // 调用，并断言异常
        assertServiceException(() -> jobService.updateJob(updateReqVO),
                JOB_UPDATE_ONLY_NORMAL_STATUS);
    }

    @Test
    public void testUpdateJob_success() throws SchedulerException {
        // mock 数据
        JobDO job = randomPojo(JobDO.class, o -> o.setStatus(JobStatusEnum.NORMAL.getStatus()));
        jobMapper.insert(job);
        // 准备参数
        JobUpdateReqVO updateReqVO = randomPojo(JobUpdateReqVO.class, o -> {
            o.setId(job.getId());
            o.setCronExpression("0 0/1 * * * ? *");
        });

        // 调用
        jobService.updateJob(updateReqVO);
        // 校验记录的属性是否正确
        JobDO updateJob = jobMapper.selectById(updateReqVO.getId());
        assertPojoEquals(updateReqVO, updateJob);
        // 校验调用
        verify(schedulerManager).updateJob(eq(job.getHandlerName()), eq(updateReqVO.getHandlerParam()),
                eq(updateReqVO.getCronExpression()), eq(updateReqVO.getRetryCount()), eq(updateReqVO.getRetryInterval()));
    }

    @Test
    public void testUpdateJobStatus_changeStatusInvalid() {
        // 调用，并断言异常
        assertServiceException(() -> jobService.updateJobStatus(1L, JobStatusEnum.INIT.getStatus()),
                JOB_CHANGE_STATUS_INVALID);
    }

    @Test
    public void testUpdateJobStatus_changeStatusEquals() {
        // mock 数据
        JobDO job = randomPojo(JobDO.class, o -> o.setStatus(JobStatusEnum.NORMAL.getStatus()));
        jobMapper.insert(job);

        // 调用，并断言异常
        assertServiceException(() -> jobService.updateJobStatus(job.getId(), job.getStatus()),
                JOB_CHANGE_STATUS_EQUALS);
    }

    @Test
    public void testUpdateJobStatus_stopSuccess() throws SchedulerException {
        // mock 数据
        JobDO job = randomPojo(JobDO.class, o -> o.setStatus(JobStatusEnum.NORMAL.getStatus()));
        jobMapper.insert(job);

        // 调用
        jobService.updateJobStatus(job.getId(), JobStatusEnum.STOP.getStatus());
        // 校验记录的属性是否正确
        JobDO dbJob = jobMapper.selectById(job.getId());
        assertEquals(JobStatusEnum.STOP.getStatus(), dbJob.getStatus());
        // 校验调用
        verify(schedulerManager).pauseJob(eq(job.getHandlerName()));
    }

    @Test
    public void testUpdateJobStatus_normalSuccess() throws SchedulerException {
        // mock 数据
        JobDO job = randomPojo(JobDO.class, o -> o.setStatus(JobStatusEnum.STOP.getStatus()));
        jobMapper.insert(job);

        // 调用
        jobService.updateJobStatus(job.getId(), JobStatusEnum.NORMAL.getStatus());
        // 校验记录的属性是否正确
        JobDO dbJob = jobMapper.selectById(job.getId());
        assertEquals(JobStatusEnum.NORMAL.getStatus(), dbJob.getStatus());
        // 校验调用
        verify(schedulerManager).resumeJob(eq(job.getHandlerName()));
    }

    @Test
    public void testTriggerJob_success() throws SchedulerException {
        // mock 数据
        JobDO job = randomPojo(JobDO.class);
        jobMapper.insert(job);

        // 调用
        jobService.triggerJob(job.getId());
        // 校验调用
        verify(schedulerManager).triggerJob(eq(job.getId()),
                eq(job.getHandlerName()), eq(job.getHandlerParam()));
    }

    @Test
    public void testDeleteJob_success() throws SchedulerException {
        // mock 数据
        JobDO job = randomPojo(JobDO.class);
        jobMapper.insert(job);

        // 调用
        jobService.deleteJob(job.getId());
        // 校验不存在
        assertNull(jobMapper.selectById(job.getId()));
        // 校验调用
        verify(schedulerManager).deleteJob(eq(job.getHandlerName()));
    }

    @Test
    public void testGetJobList() {
        // mock 数据
        JobDO dbJob = randomPojo(JobDO.class, o -> {
            o.setStatus(randomEle(JobStatusEnum.values()).getStatus()); // 保证 status 的范围
        });
        jobMapper.insert(dbJob);
        // 测试 id 不匹配
        jobMapper.insert(cloneIgnoreId(dbJob, o -> {}));

        // 准备参数
        Collection<Long> ids = singletonList(dbJob.getId());
        // 调用
        List<JobDO> list = jobService.getJobList(ids);
        // 断言
        assertEquals(1, list.size());
        assertPojoEquals(dbJob, list.get(0));
    }

    @Test
    public void testGetJobPage() {
        // mock 数据
        JobDO dbJob = randomPojo(JobDO.class, o -> {
            o.setName("定时任务测试");
            o.setHandlerName("handlerName 单元测试");
            o.setStatus(JobStatusEnum.INIT.getStatus());
        });
        jobMapper.insert(dbJob);
        // 测试 name 不匹配
        jobMapper.insert(cloneIgnoreId(dbJob, o -> o.setName("土豆")));
        // 测试 status 不匹配
        jobMapper.insert(cloneIgnoreId(dbJob, o -> o.setStatus(JobStatusEnum.NORMAL.getStatus())));
        // 测试 handlerName 不匹配
        jobMapper.insert(cloneIgnoreId(dbJob, o -> o.setHandlerName(randomString())));
        // 准备参数
        JobPageReqVO reqVo = new JobPageReqVO();
        reqVo.setName("定时");
        reqVo.setStatus(JobStatusEnum.INIT.getStatus());
        reqVo.setHandlerName("单元");

        // 调用
        PageResult<JobDO> pageResult = jobService.getJobPage(reqVo);
        // 断言
        assertEquals(1, pageResult.getTotal());
        assertEquals(1, pageResult.getList().size());
        assertPojoEquals(dbJob, pageResult.getList().get(0));
    }

    @Test
    public void testGetJobList_export() {
        // mock 数据
        JobDO dbJob = randomPojo(JobDO.class, o -> {
            o.setName("定时任务测试");
            o.setHandlerName("handlerName 单元测试");
            o.setStatus(JobStatusEnum.INIT.getStatus());
        });
        jobMapper.insert(dbJob);
        // 测试 name 不匹配
        jobMapper.insert(cloneIgnoreId(dbJob, o -> o.setName("土豆")));
        // 测试 status 不匹配
        jobMapper.insert(cloneIgnoreId(dbJob, o -> o.setStatus(JobStatusEnum.NORMAL.getStatus())));
        // 测试 handlerName 不匹配
        jobMapper.insert(cloneIgnoreId(dbJob, o -> o.setHandlerName(randomString())));
        // 准备参数
        JobExportReqVO reqVo = new JobExportReqVO();
        reqVo.setName("定时");
        reqVo.setStatus(JobStatusEnum.INIT.getStatus());
        reqVo.setHandlerName("单元");

        // 调用
        List<JobDO> list = jobService.getJobList(reqVo);
        // 断言
        assertEquals(1, list.size());
        assertPojoEquals(dbJob, list.get(0));
    }

    @Test
    public void testGetJob() {
        // mock 数据
        JobDO dbJob = randomPojo(JobDO.class);
        jobMapper.insert(dbJob);
        // 调用
        JobDO job = jobService.getJob(dbJob.getId());
        // 断言
        assertPojoEquals(dbJob, job);
    }

}
