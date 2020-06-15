package org.example.order.service.input.ports.flow;

import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.worker.JobClient;

public interface FlowWorker {

    void executeJob(JobClient client, ActivatedJob job);

}
