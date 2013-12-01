package com.banking.xc.utils.vacationutil;
import com.banking.xc.entity.GuestRoom;

public class VacationSelectedRoomUtil {
	static GuestRoom guestRoom;
	static int selectedPosition;
	public static GuestRoom getGuestRoom() {
		return guestRoom;
	}
	public static void setGuestRoom(GuestRoom guestRoom) {
		VacationSelectedRoomUtil.guestRoom = guestRoom;
	}
	public static int getSelectedPosition() {
		return selectedPosition;
	}
	public static void setSelectedPosition(int selectedPosition) {
		VacationSelectedRoomUtil.selectedPosition = selectedPosition;
	}
	
	public static void clear(){
		guestRoom = null;
		selectedPosition = -1;
	}
	
}
