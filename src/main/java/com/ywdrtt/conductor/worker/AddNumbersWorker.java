package com.ywdrtt.conductor.worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class AddNumbersWorker implements Worker {

    private final String taskDefName;

    public AddNumbersWorker(String taskDefName) {
        this.taskDefName = taskDefName;
    }

    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        String num1 = (String) task.getInputData().get("num1");
        String num2 = (String) task.getInputData().get("num2");

        result.addOutputData("addition", Integer.parseInt(num1) +
                Integer.parseInt(num2));
        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }

}
