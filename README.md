# [Conductor Netflix]

### How to [Run Conductor]

Conductor can running via locally or via Docker. 
We can get the docker of Conductor by pulls with command: 

```
$ git clone https://github.com/Netflix/conductor.git
```

Build the Docker Compose.
```
$ cd conductor
conductor $ cd docker
docker $ docker-compose up
```
We can use this command to execute docker-compose.yaml.
```
docker-compose up
``` 
By now, the only docker worked is docker-compose.yaml.


We can see on docker-compose.yaml, that file will run 3 images
- Conductor Server, to CRUD a workflow, task or to trigger a workflow, etc [Conductor Server API Spec]. Conductor server uses memory as database, so when the server was down, the data will reset.
- Conductor UI, to show information workflow, task (state, input, output, etc)
- Elastic, do not know yet

When Conductor is running, we get access to Conductor Server and Conductor UI. 
- Conductor Server must run on port 8080, so when run the image alone, we can use command (images build with forward port 8080):
```
    docker container start <CONTAINER_CONDUCTOR_SERVER_ID>
```
- The same with Conductor UI must run on port 5000, we can use with the same docker command

### How to [Create Workflow]
To configure the workflow, head over to the swagger API of conductor server and access the metadata workflow create API:

http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/metadata-resource/create

If the link doesn’t open the right Swagger section, we can navigate to Metadata-Resource → POST /api/metadata/workflow 
with request body the Workflow json.

The Workflow can be access in Conductor UI at http://localhost:5000/workflowDefs
### How to [Create Task]
Tasks can be created using the tasks metadata API with request body the Task json.

``POST /api/metadata/taskdefs``

This API takes an array of new task definitions.

The Task can be access in Conductor UI at http://localhost:5000/taskDefs
### How to [Create Worker]
- Make a new Worker by implements Worker Interface
- Define the taskDefName (task name who registered in Conductor Server)
- Define TaskRunnerConfigurer, 
The TaskRunnerConfigurer can be used to register the worker(s) and initialize the polling loop. Manages the task workers thread pool and server communication (poll and task update).
Use the Builder to create an instance of the TaskRunnerConfigurer.

### How to Trigger Workflow

- Conductor Server is running
- Conductor UI is running
- Workflow and Task was store
- Worker is running
- After all above component is already work properly, we can trigger the workflow. 

To do that we can use the swagger API under the workflow-resources

http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/workflow-resource/startWorkflow_1 with request body is our input.

example:

POST http://localhost:8080/api/workflow/simple_workflow?version=1

Request Body: 
```
{
  "num1": "34",
  "num2": "23"
}
```

Trigger workflow will return ID Executions of the workflow.

### How to Check Workflow was execute
On Conductor UI, we can access 
```
http://localhost:5000/execution/<ID_EXECUTIONS>
```
On that page, we can see the state of Workflow, input and output of the task that worker has executed.

[Create Workflow and System Task]

[Create Workflow and System Task]: https://conductor.netflix.com/labs/running-first-workflow.html


[Conductor Server API Spec]: https://conductor.netflix.com/apispec.html


[Run Conductor]: https://conductor.netflix.com/gettingstarted/docker.html

[Create Workflow]: https://conductor.netflix.com/gettingstarted/startworkflow.html

[Create Task]: https://conductor.netflix.com/how-tos/Tasks/creating-tasks.html

[Create Worker]: https://github.com/Netflix/conductor/blob/main/client/src/test/java/com/netflix/conductor/client/sample/Main.java
[Conductor Netflix]: https://conductor.netflix.com/
