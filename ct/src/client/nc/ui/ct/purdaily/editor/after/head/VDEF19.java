package nc.ui.ct.purdaily.editor.after.head;

import hd.ui.hl.pub.tools.PubBatchEditDialog;
import hd.vo.pub.tools.PuPubVO;
import nc.ui.ct.editor.listener.IHeadTailAfterEditEventListener;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.ct.entity.CtZzDzInfoVO;
import nc.vo.pub.lang.UFDouble;

import org.codehaus.jackson.map.ObjectMapper;

public class VDEF19 implements IHeadTailAfterEditEventListener {

	@Override
	public void afterEdit(CardHeadTailAfterEditEvent event) {
		
		try {
			
			String dzType = PuPubVO.getString_TrimZeroLenAsNull(event.getValue());
			String vdef1 = PuPubVO.getString_TrimZeroLenAsNull(event.getBillCardPanel().getHeadItem("vdef1").getValueObject());
			
			Integer dzMonth = null;
			Double dzLv = null;
			Double dzJe = null;

			ObjectMapper objectMapper = new ObjectMapper();
			
			if (vdef1 != null) {
				CtZzDzInfoVO dzInfoVO = objectMapper.readValue(vdef1, CtZzDzInfoVO.class);
				dzMonth = dzInfoVO.getDzMonth();
				dzLv = dzInfoVO.getDzLv();
				dzJe = dzInfoVO.getDzJe();
			}
			
			// 递增年限
			BillItem dzYearItem = new BillItem();
			dzYearItem.setName("递增频率（月）");
			dzYearItem.setKey("dzYear");
			dzYearItem.setDataType(IBillItem.INTEGER);
			dzYearItem.setEdit(true);
			dzYearItem.setNull(true);	// 是否非空  false 不是非空
			dzYearItem.setValue(dzMonth);
			// 递增率
			BillItem dzLvItem = new BillItem();
			dzLvItem.setName("递增率（%）");
			dzLvItem.setKey("dzLv");
			dzLvItem.setDataType(IBillItem.DECIMAL);
			dzLvItem.setEdit(true);
			dzLvItem.setNull(true);	// 是否非空  false 不是非空
			dzLvItem.setValue(dzLv);
			// 递增额
			BillItem dzJeItem = new BillItem();
			dzJeItem.setName("递增额");
			dzJeItem.setKey("dzJe");
			dzJeItem.setDataType(IBillItem.DECIMAL);
			dzJeItem.setEdit(true);
			dzJeItem.setNull(true);	// 是否非空  false 不是非空
			dzJeItem.setValue(dzJe);
			// 说明
			BillItem memoItem = new BillItem();
			memoItem.setName("");
			memoItem.setKey("memo");
			memoItem.setDataType(IBillItem.TEXTAREA);
			memoItem.setEdit(false);	// 不能编辑
			memoItem.setNull(false);	// 是否非空  false 不是非空
			memoItem.setValue(
					"递增处理，从第二年开始。\r\n" +
					"递增率 和 递增额，只能由一种起效。不让起效的请手工填0"
					);
			
			PubBatchEditDialog dlg = new PubBatchEditDialog(
					event.getBillCardPanel()
					,new BillItem[]{
						dzYearItem,
						dzLvItem,
						dzJeItem,
						memoItem
					});
			dlg.setTitle("涨租信息");
			
			if(UIDialog.ID_OK != dlg.showModal()) return;
			
			dzMonth = PuPubVO.getInteger_NullAs(dzYearItem.getValueObject(), -1);
			UFDouble dzLv_temp = PuPubVO.getUFDouble_ZeroAsNull(dzLvItem.getValueObject());
			UFDouble dzJe_temp = PuPubVO.getUFDouble_ZeroAsNull(dzJeItem.getValueObject());
			dzLv = dzLv_temp == null ? null : dzLv_temp.toDouble();
			dzJe = dzJe_temp == null ? null : dzJe_temp.toDouble();
			
			CtZzDzInfoVO dzInfoVO = new CtZzDzInfoVO(
					dzMonth,
					dzLv,
					dzJe
			);
			
			vdef1 = objectMapper.writeValueAsString(dzInfoVO);
			
			event.getBillCardPanel().getHeadItem("vdef1").setValue(vdef1);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
