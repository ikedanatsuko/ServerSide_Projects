package io.github.batch.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.batch.dao.service.ItemCompareDao;
import io.github.batch.dao.service.ItemCompareDaoImpl;
import io.github.batch.dao.table.ItemOutputDao;
import io.github.batch.dao.table.CreatedItemDaoImpl;
import io.github.batch.dao.table.DateTotalDao;
import io.github.batch.entity.ItemOutput;

@Component
public class TotalCreatedItem implements Tasklet {
	
	@Autowired
	private ItemCompareDao itemCompareDao;
	
	@Autowired
	private DateTotalDao dateTotalDao;
	
	@Autowired
	private CreatedItemDaoImpl createdItemDao;
	
	
	private int dateId;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext context) {
		
		List<ItemOutput> createdItems = itemCompareDao.getCreatedItems();
		
		for (ItemOutput itemOutput : createdItems) {
			itemOutput.setDateId(dateId);
			createdItemDao.createItem(itemOutput);
		}
		int total = createdItems.size();
		dateTotalDao.setCreatedTotal(dateId, total);
		
		return RepeatStatus.FINISHED;
	}
	
	public void setDateId(int dateId) {
		this.dateId = dateId;
	}
}
