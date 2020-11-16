package nc.ui.tb.zior.pluginaction.fetchvalue;

import nc.ms.tb.task.data.TaskDataModel;
import nc.ms.tb.task.data.TaskSheetDataModel;
import nc.ui.tb.zior.TBSheetViewer;
import nc.vo.pub.BusinessException;

import com.ufsoft.table.CellsModel;

public interface Action_itf {

	void doAction (
			  StringBuffer ormessage
			, int year
			, TBSheetViewer tbSheetViewer
			, TaskDataModel taskDataModel
			, TaskSheetDataModel tsmodel
			, int rowno
			, int colno
			, CellsModel csModel
			) throws BusinessException;
}
