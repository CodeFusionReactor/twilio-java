/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1.workspace;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class TaskCreator extends Creator<Task> {
    private final String pathWorkspaceSid;
    private Integer timeout;
    private Integer priority;
    private String taskChannel;
    private String workflowSid;
    private String attributes;

    /**
     * Construct a new TaskCreator.
     * 
     * @param pathWorkspaceSid The workspace_sid
     */
    public TaskCreator(final String pathWorkspaceSid) {
        this.pathWorkspaceSid = pathWorkspaceSid;
    }

    /**
     * The amount of time in seconds the task is allowed to live up to a maximum of
     * 2 weeks. Defaults to 24 hours. On timeout, `task.canceled` event will fire
     * with description "Task TTL Exceeded"..
     * 
     * @param timeout The amount of time in seconds the task is allowed to live up
     *                to a maximum of 2 weeks.
     * @return this
     */
    public TaskCreator setTimeout(final Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Override priority for the Task. When supplied, the Task will take on the
     * given priority unless it matches a Workflow Target with a Priority set. When
     * not supplied, the Task will take on the priority of the matching Workflow
     * Target..
     * 
     * @param priority Override priority for the Task.
     * @return this
     */
    public TaskCreator setPriority(final Integer priority) {
        this.priority = priority;
        return this;
    }

    /**
     * When MultiTasking is enabled specify the type of the task by passing either
     * TaskChannel Unique Name or Task Channel Sid. Default value is "default".
     * 
     * @param taskChannel When MultiTasking is enabled specify the type of the task
     *                    by passing either TaskChannel Unique Name or Task Channel
     *                    Sid.
     * @return this
     */
    public TaskCreator setTaskChannel(final String taskChannel) {
        this.taskChannel = taskChannel;
        return this;
    }

    /**
     * The WorkflowSid for the Workflow that you would like to handle routing for
     * this Task. If there is only one Workflow defined for the Workspace that you
     * are posting a task to, then this is an optional parameter, and that single
     * workflow will be used..
     * 
     * @param workflowSid The WorkflowSid for the Workflow that you would like to
     *                    handle routing for this Task.
     * @return this
     */
    public TaskCreator setWorkflowSid(final String workflowSid) {
        this.workflowSid = workflowSid;
        return this;
    }

    /**
     * Url-encoded JSON string describing the attributes of this task. This data
     * will be passed back to the Workflow's AssignmentCallbackURL when the Task is
     * assigned to a Worker. An example task: `{ "task_type": "call",
     * "twilio_call_sid": "CAxxx", "customer_ticket_number": "12345" }`.
     * 
     * @param attributes Url-encoded JSON string describing the attributes of this
     *                   task.
     * @return this
     */
    public TaskCreator setAttributes(final String attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Task
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Task create(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.pathWorkspaceSid + "/Tasks",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Task creation failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Task.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (timeout != null) {
            request.addPostParam("Timeout", timeout.toString());
        }

        if (priority != null) {
            request.addPostParam("Priority", priority.toString());
        }

        if (taskChannel != null) {
            request.addPostParam("TaskChannel", taskChannel);
        }

        if (workflowSid != null) {
            request.addPostParam("WorkflowSid", workflowSid);
        }

        if (attributes != null) {
            request.addPostParam("Attributes", attributes);
        }
    }
}