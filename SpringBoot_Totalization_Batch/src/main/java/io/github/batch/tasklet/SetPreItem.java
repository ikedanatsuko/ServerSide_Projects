package io.github.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.batch.dao.service.ItemCompareDao;

@Component
public class SetPreItem implements Tasklet {
	
	@Autowired
	private ItemCompareDao itemCompareDao;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
		itemCompareDao.setPreItems();
		
		return RepeatStatus.FINISHED;
	}
}
