package kr.ac.hansung.op16.calender.view;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class CalenderPanel extends Panel {
	JFrame superFrame;
	Button prevMonthBtn = new Button("<");
	Button nextMonthBtn = new Button(">");
	
	Label[] dayOfWeekLabel = new Label[7];
	Label[] startBlankDay;
	Button[] dayButton;
	Label[] lastBlankDay;
	/**
	 * Create the panel.
	 */
	public CalenderPanel(int year, int month, MainFrame superFrame) {
		this.superFrame = superFrame;
		Calendar calendarInfo = Calendar.getInstance();
		calendarInfo.set(year, month, 1, 0, 0, 0);
		int firstDayPosition = calendarInfo.get(Calendar.DAY_OF_WEEK);
		int lastDayOfMonth =  calendarInfo.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		setLayout(new GridLayout(8, 7));
		
		prevMonthBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendarInfo.add(Calendar.MONTH, -1);
				superFrame.setYear(calendarInfo.get(Calendar.YEAR));
				superFrame.setMonth(calendarInfo.get(Calendar.MONTH));
				superFrame.calenderRepaint();
			}
		});
		add(prevMonthBtn);
		add(new Label(""));
		add(new Label(""+year));
		add(new Label("년"));
		add(new Label((month+1)+"월"));
		add(new Label(""));
		nextMonthBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendarInfo.add(Calendar.MONTH, 1);
				superFrame.setYear(calendarInfo.get(Calendar.YEAR));
				superFrame.setMonth(calendarInfo.get(Calendar.MONTH));
				superFrame.calenderRepaint();
			}
		});
		add(nextMonthBtn);
		
		
		/* 요일 표시 */
		dayOfWeekLabel[0] = new Label("일");
		dayOfWeekLabel[1] = new Label("월");
		dayOfWeekLabel[2] = new Label("화");
		dayOfWeekLabel[3] = new Label("수");
		dayOfWeekLabel[4] = new Label("목");
		dayOfWeekLabel[5] = new Label("금");
		dayOfWeekLabel[6] = new Label("토");
		for(int i=0; i<7; i++)
			add(dayOfWeekLabel[i]);
		
		/* 1일이 시작되기 전 비여있는 공간 생성 */
		if(firstDayPosition == 1 && firstDayPosition <= 28){
			/* 해당 달에 속한 주가 4주일 경우 첫줄을 비여두기 위해 사용 */
			firstDayPosition += 7;
		}
		startBlankDay = new Label[firstDayPosition - 1];
		for(int i=0; i<startBlankDay.length; i++){
			startBlankDay[i] = new Label();
			add(startBlankDay[i]);
		}
		
		/* 해당월의 1일 ~ 말일까지 출력 */
		dayButton = new Button[lastDayOfMonth];
		for(int i=0; i<dayButton.length; i++){
			dayButton[i] = new Button("" + (i+1));
			add(dayButton[i]);
		}
		
		/* 해당월의 일자를 다 출력한 다음 남는 공간을 채움 */
		lastBlankDay = new Label[(8*7) - (firstDayPosition + lastDayOfMonth - 1 + 7 + 7)];
		for(int i=0; i<lastBlankDay.length; i++){
			lastBlankDay[i] = new Label();
			add(lastBlankDay[i]);
		}
	}

}
