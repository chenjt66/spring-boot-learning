package com.chenjt.controller;

import com.chenjt.common.ResponseMsg;
import com.chenjt.common.TaskSet;
import com.chenjt.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.*;

/**
 * Created by chen jianting on 2019/4/24.
 */
@RestController
public class TaskController {

	private Logger log = LoggerFactory.getLogger(TaskController.class);

	//超时结果
	private static final ResponseMsg<String> OUT_OF_TIME_RESULT = new ResponseMsg<>(-1,"超时","out of time");

	//超时时间
	private static final long OUT_OF_TIME = 5000L;

	@Autowired
	private TaskSet taskSet;

	@Autowired
	private ExecutorService executorService;

	@RequestMapping(value = "/get",method = RequestMethod.GET)
	public DeferredResult<ResponseMsg<String>> getResult() throws Exception {

		log.info("接收请求，开始处理...");

		//建立DeferredResult对象，设置超时时间，以及超时返回超时结果
		DeferredResult<ResponseMsg<String>> result = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);

		result.onTimeout(() -> {
			log.info("调用超时，移除任务，此时队列长度为{}",taskSet.getSet().size());

			synchronized (taskSet.getSet()) {

				taskSet.getSet().remove(result);
			}
		});

		result.onCompletion(() -> {
			log.info("调用完成，移除任务，此时队列长度为{}",taskSet.getSet().size());

			synchronized (taskSet.getSet()) {

				taskSet.getSet().remove(result);
			}
		});

		//并发，加锁
		synchronized (taskSet.getSet()) {

			taskSet.getSet().add(result);

		}
		log.info("加入任务集合，集合大小为:{}",taskSet.getSet().size());

		log.info("接收任务线程完成并退出");

		return result;

	}

	@RequestMapping(value = "/get2", method = RequestMethod.GET)
	public String get2(){

		executorService.submit(new Runnable() {
			@Override
			public void run() {
				DeferredResult<String> result = new DeferredResult<>(OUT_OF_TIME, OUT_OF_TIME_RESULT);
				result.onTimeout(() -> {
					log.info("调用超时，移除任务，此时队列长度为{}",taskSet.getSset().size());

					synchronized (taskSet.getSset()) {

						taskSet.getSset().remove(result);
					}
				});
				result.onCompletion(() ->{
					log.info("调用完成，移除任务，此时队列长度为{}",taskSet.getSset().size());

					synchronized (taskSet.getSset()) {

						taskSet.getSset().remove(result);
					}
				});
				log.info("加入任务集合，集合大小为:{}",taskSet.getSset().size());
				//并发，加锁
				synchronized (taskSet.getSset()) {

					taskSet.getSset().add(result);

				}
			}
		});


		log.info("接收任务线程完成并退出");

		return "yes";

	}

	@Autowired
	private AsyncService asyncService;

	@RequestMapping(value = "/get3", method = RequestMethod.GET)
	public String get3(){
		log.info("调用开始");
//		async();
//		try {
//			asyncService.async("chenjt");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		executorService.execute(() ->{
			Future<String> result = executorService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					log.info("线程休眠");
					Thread.sleep(3000L);
					return "true";
				}
			});
			String res = null;
			try {
				res = result.get(2L, TimeUnit.SECONDS);
			} catch (Exception e) {
				log.error("超时异常", e);
				get3();
			}
			if (res != null && res.equals("false")){
				log.info("返回失败");
				get3();
			}
		});
		log.info("调用结束");
		return "helloWorld";
	}

	@Async
	public void async(){
		log.info("异步调用开始");
		try {
			Thread.sleep(1000L);
			log.info("异步调用结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=================");
	}
}