/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MeetingScheduler;

import java.util.List;

/**
 *
 * @author Akshay Gupta
 */
public class Client {
    public static void main(String[] args) throws SlotNotAvailable {
        Calendar akshayClaendar1=new MicrosoftCalendar();
        Calendar rohitCalendar1=new MicrosoftCalendar();
        Person akshay=new Person("Akshay Gupta", "akshay.gupta@gmail.com");
        Person rohit =new Person("Rohit goka","rohit@gmail.com");
        
        akshay.calendar.put("Cal1", akshayClaendar1);
        rohit.calendar.put("Cal2", rohitCalendar1);
        MeetingRoom meetingRoom1=new MeetingRoom("101", 3, 10);
        Time startTime=new Time(1,1,1,1,0,0),endTime=new Time(1,1,1,2,0,0);
        Time startTime2=new Time(1,1,1,3,0,0),endTime2=new Time(1,1,1,4,0,0);
        akshayClaendar1.createMeeting("My first meeting", new Meeting(new Intervel(startTime,endTime), "My first meeting", "Test vody", meetingRoom1, akshay, List.of(rohit)));
         akshayClaendar1.createMeeting("My first meeting", new Meeting(new Intervel(startTime2,endTime2), "My first meeting", "Test vody", meetingRoom1, akshay, List.of(rohit)));
    }
}
