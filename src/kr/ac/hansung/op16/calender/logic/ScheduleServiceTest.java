package kr.ac.hansung.op16.calender.logic;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import kr.ac.hansung.op16.calender.model.ScheduleData;

public class ScheduleServiceTest {
	
	
	/**
	 * 스케줄 추가기능 테스트 메소드
	 */
	@Test
	public void testOneAddSchedule(){
		ScheduleService scheduleService = new ScheduleService();
		
		scheduleService.addSchedule(2016, 4, 25, 00, 05, 13, 30, "테스트 일정", "테스트 일정입니다.");
		
		List<ScheduleData> scheduleList = scheduleService.getScheduleList();
		assertEquals(scheduleList.size(), 1);
		
		ScheduleData scheduleData = scheduleList.get(0);		
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		startDate.set(2016, 4, 25, 00, 05);
		endDate.set(2016,  4, 25, 13, 30);
		
		assertEquals(scheduleData.getStartDate().getTimeInMillis(), startDate.getTimeInMillis());
		assertEquals(scheduleData.getEndDate().getTimeInMillis(), endDate.getTimeInMillis());
		assertEquals(scheduleData.getTitle(), "테스트 일정");
		assertEquals(scheduleData.getContent(), "테스트 일정입니다.");
	}
	
	/**
	 * 달력의 날짜와 일정간의 맵핑 결과 테스트
	 */
	@Test
	public void testCalenderMapping(){
		ScheduleService scheduleService = new ScheduleService();
		
		scheduleService.addSchedule(2016, 4, 29, 00, 05, 2016, 5, 1, 13, 30, "테스트 일정", "테스트 일정입니다.");
		
		Map<Integer, List<ScheduleData>> calendarMapping = scheduleService.calendarMappingScheduleList(2016, 4);
		
		
		assertFalse(calendarMapping.containsKey(1));
		assertFalse(calendarMapping.containsKey(28));
		assertFalse(calendarMapping.containsKey(32));
		
		assertEquals(calendarMapping.get(29).size(), 1);
		assertEquals(calendarMapping.get(30).size(), 1);
		assertEquals(calendarMapping.get(31).size(), 1);
	}

}
