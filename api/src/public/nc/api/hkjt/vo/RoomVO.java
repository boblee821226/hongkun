package nc.api.hkjt.vo;

import java.io.Serializable;

public class RoomVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6028818929180245395L;

	private String orgCode;			// 所属物业公司编码
	private String roomId;			// 房间ID
	private String roomCode;		// 房间编码
	private String roomName;		// 房间名称-房间号
	private String area;			// 面积
	private String direction;		// 朝向
	private String inOut;			// 内景/外景
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getInOut() {
		return inOut;
	}
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}
	
}
