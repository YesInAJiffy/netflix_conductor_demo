package com.ywdrtt.conductor.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;


public class MultiplyBy5 implements Worker {

    private final String taskDefName;

    public MultiplyBy5(String taskDefName) {
        this.taskDefName = taskDefName;
    }

    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        Integer num1 = (Integer) task.getInputData().get("doubled");

        result.addOutputData("mb5", num1*5);
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
