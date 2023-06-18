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
public class SolidCalendar {
    
}
class MicrosoftCalendar implements Calendar{
    
   private Map<String,Meeting> confirmedMeetings;
   private Queue<Meeting> pendingRequest;

   Meeting getMeeting(String name){
       return confirmedMeetings.get(name);
   }
    public MicrosoftCalendar() {
        confirmedMeetings=new HashMap<>();
        pendingRequest=new PriorityQueue<Meeting>();
    }
    
    @Override
    public List<Meeting> getMeetings(Intervel intervel) {
        List<Meeting> respectiveMeeting=new ArrayList<>();
        for(Meeting confirmedMeeting:confirmedMeetings.values()){
            if(confirmedMeeting.haveOverlap(intervel)){
                respectiveMeeting.add(confirmedMeeting);
            }
        }
        return respectiveMeeting;
    }

    @Override
    public void createMeeting(String name,Meeting meeting) throws SlotNotAvailable {
        for (Meeting coinfirmedMeetings:confirmedMeetings.values()) {
           if(coinfirmedMeetings.haveOverlap(meeting.intevel())){
               throw new SlotNotAvailable("A meeting already exist at this time");
           } 
        }
        if (meeting.meetingRoom().calendar.haveConflict(meeting)) {
               throw new SlotNotAvailable("Meeting room not available");
        }
        
        for(Person attendee:meeting.attendee()){
            attendee.sendNotification(meeting, false);
        }
        confirmedMeetings.put(name,meeting);
    }

    @Override
    public void cancleMeeting(String name,Meeting meeting) {
        for(int i=0;i<meeting.attendee().size();i++){
            meeting.attendee().get(i).sendNotification(meeting,true);
        }
        confirmedMeetings.remove(name, meeting);
    }

    @Override
    public Intervel getFirstFreeInvervalAfter(Time time, int durationHours) {
        return null;
    }

    @Override
    public void updateMeeting(Meeting oldMeeting, Meeting newMeeting) {
        for(int i=0;i<newMeeting.attendee().size();i++){
            newMeeting.attendee().get(i).sendNotification(newMeeting, false);
        }
        confirmedMeetings.remove(oldMeeting.title(),oldMeeting);
        confirmedMeetings.put(oldMeeting.title(),newMeeting);
    }

    @Override
    public void reciveInvite(Meeting meeting) {
        pendingRequest.add(meeting);
     }

    @Override
    public void confirmMeeting(Meeting meeting) throws SlotNotAvailable{
        for (int i = 0; i < confirmedMeetings.size(); i++) {
           if(confirmedMeetings.get(i).haveOverlap(meeting.intevel())){
               throw new SlotNotAvailable("Can't accept invite a confit is there");
           } 
        }
        pendingRequest.remove(meeting);
        confirmedMeetings.put(meeting.title(),meeting);
    }

    @Override
    public void rejectMeeting(Meeting meeting) throws MeetingNotFound{
        if(!pendingRequest.contains(meeting))
            throw new MeetingNotFound();
        pendingRequest.remove(meeting);
    }

    @Override
    public boolean haveConflict(Meeting invite) {
        for (Meeting confirmed:confirmedMeetings.values()) {
           if(invite.haveOverlap(confirmed.intevel())){
               return true;
           } 
        }
        return false;
    }
    
}