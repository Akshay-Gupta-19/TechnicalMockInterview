/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MeetingScheduler;
import java.util.*;
/**
 *
 * @author Akshay Gupta
 */
interface Calendar{
    List<Meeting> getMeetings(Intervel intervel);
    void createMeeting(String name,Meeting meeting) throws SlotNotAvailable;
    void cancleMeeting(String name,Meeting meeting);
    Intervel getFirstFreeInvervalAfter(Time time,int durationHours);
    void updateMeeting(Meeting oldMeeting,Meeting newMeeting) ;
    void reciveInvite(Meeting meeting);
    void confirmMeeting(Meeting meeting) throws SlotNotAvailable;
    void rejectMeeting(Meeting meeting) throws MeetingNotFound;
    boolean haveConflict(Meeting invite);
}

record Meeting(
    Intervel intevel,
    String title,
    String description,
    MeetingRoom meetingRoom,
    Person organizer,
    List<Person> attendee)
{
    void addAttendee(Person person){
        person.sendNotification(this,true);
        attendee.add(person);
    }

    boolean timeBetweenInbetween(Time time){
        return time.compareTo(intevel.start)<0 && time.compareTo(intevel.end)>0;
    }
    
    boolean haveOverlap(Intervel interval){
        Intervel first=this.intevel;
        Intervel second = interval;
        if(first.start.compareTo(second.start)<0){
            Intervel temp=first;
            first=second;
            second=temp;
        }
        return first.end.compareTo(second.start)<=0;
    }
    @Override
    public String toString() {
        return "Meeting{" + "intevel=" + intevel + ", title=" + title + ", meetingRoom=" + meetingRoom + ", organizer=" + organizer + ", attendee=" + attendee + '}';
    }
    
}
class Intervel{
    Time start,end;

    public Intervel(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    
    @Override
    public String toString() {
        return "Intervel{" + "start=" + start + ", end=" + end + '}';
    }
}
class Time implements Comparable<Time>{
    int date,month,year;
    int hour,min,second;

    public Time(int date, int month, int year, int hour, int min, int second) {
        this.date = date;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.min = min;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Time{" + "date=" + date + ", month=" + month + ", year=" + year + ", hour=" + hour + ", min=" + min + ", second=" + second + '}';
    }

    @Override
    public int compareTo(Time o) {
        if(o.year!=this.year)
            return o.year-this.year;
        if(o.month!=this.month)
            return o.month-this.month;
        if(o.date!=this.date)
            return o.date-this.date;
        if(o.hour!=this.hour)
            return o.hour-this.hour;
        if(o.min!=this.min)
            return o.min-this.min;
        if(o.second!=this.second)
            return o.second-this.second;
        return 0;
   }
}

class Person{
    String name;
    Map<String,Calendar> calendar;
    String email;

    public Person(String name, String email) {
        this.name = name;
        this.calendar = new HashMap<>();
        this.email = email;
    }
    
    void sendNotification(Meeting meeting,boolean isCancling){
        System.out.println("Sending invite "+(isCancling?" isCancled ":"")+meeting+" to "+email);
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", email=" + email + '}';
    }
    
    
}
class MeetingRoom{
    Address address;
    String rromNumber;
    int floor;
    Calendar calendar;
    int capcity;

    public MeetingRoom( String rromNumber, int floor, int capcity) {
        this.rromNumber = rromNumber;
        this.floor = floor;
        this.calendar = new MicrosoftCalendar();
        this.capcity = capcity;
    }
    
    @Override
    public String toString() {
        return "MeetingRoom{" + "roomNumber=" + rromNumber + ", floor=" + floor + '}';
    }
    
    
}
class Address{
    String streat,building,city,State;
}
class SlotNotAvailable extends Exception{

    public SlotNotAvailable(String err) {
        super(err);
    }
}
class MeetingNotFound extends Exception{
    
}