package nc.vo.cm.inprocost.entity;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

public class InproCostTypeEnum extends MDEnum {
    /**
     * �ڲ��ɱ�
     */
    public static final InproCostTypeEnum CM_InproCost = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(0));

    /**
     * �ڳ��ڲ����
     */
    public static final InproCostTypeEnum CM_Begin = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(1));

    /**
     * �ڲ�������
     */
    public static final InproCostTypeEnum CM_Adjust = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(2));

    /**
     * �ڲ�ת��
     */
    public static final InproCostTypeEnum CM_TurnOut = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(3));

    /**
     * �ڲ��ɱ�(�ɱ�����Ϊ��)---�����쳣���
     */
    public static final InproCostTypeEnum CM_YCJC = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(4));

    /**
     * �ڲ��ɱ�(�ɱ�����Ϊ��)---�����쳣���(����)
     */
    public static final InproCostTypeEnum CM_YCJC_Remain = MDEnum.valueOf(InproCostTypeEnum.class, Integer.valueOf(5));

    public InproCostTypeEnum(IEnumValue enumValue) {
        super(enumValue);
    }
}
