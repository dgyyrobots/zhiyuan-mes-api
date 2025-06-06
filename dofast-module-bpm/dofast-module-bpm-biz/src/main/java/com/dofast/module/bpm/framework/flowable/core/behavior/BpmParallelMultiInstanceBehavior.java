package com.dofast.module.bpm.framework.flowable.core.behavior;

import com.dofast.framework.flowable.core.util.FlowableUtils;
import com.dofast.module.bpm.service.definition.BpmTaskAssignRuleService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.Activity;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.impl.bpmn.behavior.AbstractBpmnActivityBehavior;
import org.flowable.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;

import java.util.Set;

/**
 * 自定义的【并行】的【多个】流程任务的 assignee 负责人的分配
 * 第一步，基于分配规则，计算出分配任务的【多个】候选人们。
 * 第二步，将【多个】任务候选人们，设置到 DelegateExecution 的 collectionVariable 变量中，以便 BpmUserTaskActivityBehavior 使用它
 *
 * @author kemengkai
 * @date 2022-04-21 16:57
 */
@Slf4j
public class BpmParallelMultiInstanceBehavior extends ParallelMultiInstanceBehavior {

    @Setter
    private BpmTaskAssignRuleService bpmTaskRuleService;

    public BpmParallelMultiInstanceBehavior(Activity activity,
                                            AbstractBpmnActivityBehavior innerActivityBehavior) {
        super(activity, innerActivityBehavior);
    }

    /**
     * 重写该方法，主要实现两个功能：
     * 1. 忽略原有的 collectionVariable、collectionElementVariable 表达式，而是采用自己定义的
     * 2. 获得任务的处理人，并设置到 collectionVariable 中，用于 BpmUserTaskActivityBehavior 从中可以获取任务的处理人
     *
     * 注意，多个任务实例，每个任务实例对应一个处理人，所以返回的数量就是任务处理人的数量
     *
     * @param execution 执行任务
     * @return 数量
     */
    @Override
    protected int resolveNrOfInstances(DelegateExecution execution) {
        // 第一步，设置 collectionVariable 和 CollectionVariable
        // 从  execution.getVariable() 读取所有任务处理人的 key
        super.collectionExpression = null; // collectionExpression 和 collectionVariable 是互斥的
        super.collectionVariable = FlowableUtils.formatCollectionVariable(execution.getCurrentActivityId());
        // 从 execution.getVariable() 读取当前所有任务处理的人的 key
        super.collectionElementVariable = FlowableUtils.formatCollectionElementVariable(execution.getCurrentActivityId());

        // 第二步，获取任务的所有处理人
        Set<Long> assigneeUserIds = bpmTaskRuleService.calculateTaskCandidateUsers(execution);
        execution.setVariable(super.collectionVariable, assigneeUserIds);
        return assigneeUserIds.size();
    }

}
