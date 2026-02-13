package com.ssafy.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 회의실배정 {
	static class Meeting implements Comparable<Meeting>{
		int start,end;
		
		public Meeting(int start, int end)
		{
			super();
			this.start = start;
			this.end = end;
		}
		@Override
		public String toString() {
			return "Meeting [start=" + start + ", end=" + end + "]";
		}
		
		@Override
		public int compareTo(Meeting o){
			// 종료시간이 빠른 순
			int diff = Integer.compare(this.end,  o.end);
			if(diff != 0) return diff;
			return Integer.compare(this.start, o.start);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Meeting[] meetings = new Meeting[N];
		
		for (int i=0; i<N; i++) {
			meetings[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}
		
		List<Meeting> result = getSchedule(meetings);
		System.out.println(result.size());
		for(Meeting meeting : result) {
			System.out.println(meeting);
		}
	}
	
	
	static List<Meeting> getSchedule(Meeting[] meetings){
		List<Meeting> list = new ArrayList<>();
		Arrays.sort(meetings);
		
		list.add(meetings[0]);
		for(int i=1; i<meetings.length; i++) {
			if(list.get(list.size()-1).end <= meetings[i].start) {
				list.add(meetings[i]);
			}
		}
		return list;
	}

}
