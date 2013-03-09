package com.ds.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Vaibhav
 * Date: Mar 9, 2013
 * Time: 6:07:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequestExceutor {

  private ExecutorService _service;

    /**
     * @param minThreads initial thread pool size for the service
     * @param maxThreads maximum thread limit for the service
     * @param maxIdleTime thread keep alive time in seconds.
     */
    public RequestExceutor(int minThreads, int maxThreads, int maxIdleTime) {
        _service = new ThreadPoolExecutor(minThreads, maxThreads, maxIdleTime, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }


  public void addRequest(final ExecutionRequest request) {
        _service.submit(new Runnable() {
            @SuppressWarnings("unchecked")
            public void run() {
                RequestProcessor processor = (RequestProcessor) RequestProcessorFactory.getProcessor(request);
                processor.handleRequest(request);
            }
        });
    }
}
