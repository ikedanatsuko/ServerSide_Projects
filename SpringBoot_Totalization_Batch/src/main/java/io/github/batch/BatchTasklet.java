package io.github.batch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.github.batch.dao.table.DateTotalDao;
import io.github.batch.tasklet.SetPreItem;
import io.github.batch.tasklet.TotalCreatedItem;
import io.github.batch.tasklet.TotalDeletedItem;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchTasklet {
	
	@Autowired
	private DateTotalDao dateTotalDao;
	
	@Autowired
	private TotalCreatedItem totalCreatedItem;
	@Autowired
	private TotalDeletedItem totalDeletedItem;
	@Autowired
	private SetPreItem setPreItem;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void perform() throws Exception {
		
		int dateId = dateTotalDao.createDateTotal(new java.sql.Date(new Date().getTime()));
		totalCreatedItem.setDateId(dateId);
		totalDeletedItem.setDateId(dateId);
		
		JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
		
		JobExecution execution = jobLauncher.run(job(step1(), step2(), step3()), param);
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.tasklet(totalCreatedItem)
				.build();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.tasklet(totalDeletedItem)
				.build();
	}
	
	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3")
				.tasklet(setPreItem)
				.build();
	}
	
	@Bean
	public Job job(Step step1, Step step2, Step step3) throws Exception {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.start(step1)
				.next(step2)
				.next(step3)
				.build();
	}
}
