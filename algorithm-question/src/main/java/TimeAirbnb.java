import java.util.ArrayList;
import java.util.List;

/*
Suppose you want to bring the whole company together for
a gigantic meeting of the minds.

Your company has N employees, and you have their schedules available
as N lists of <begin, end> timestamp pairs, which indicate times that
each individual person is busy.

Each list is sorted and there are no overlaps within a single list.

Find a list of times (<begin, end> timestamp pairs) during which
all N people are free.

You should specify a beginDay and endDay time.
We don't care about times before or after that time

     9     10     11     12     13     14     15     16     17     18     19
A           |------|------|------|                   |------|
B           |------|             |------|             |------|
Busy        |------|------|      |------|      |------|------|
Free |------|             |------|      |------|             |------|------|
*/
public class Solution {
    public static void main(String[] args) {

        // Person A's Schedule
        Time a1 = new Time(10,11);
        Time a2 = new Time(11,12);
        Time a3 = new Time(15,16);

        //Person B's Schedule
        Time b1 = new Time(10,11);
        Time b2 = new Time(13,14);
        Time b3 = new Time(16,17);

        List<Time> l1 = new ArrayList<>();
        l1.add(a1);
        l1.add(a2);
        l1.add(a3);

        List<Time> l2 = new ArrayList<>();
        l2.add(b1);
        l2.add(b2);
        l2.add(b3);

        List<List<Time>> list = new ArrayList<>();
        list.add(l1);
        list.add(l2);

        findFreeTime(list,9,19);
        // Print time slots when everyone is free.
    }

    /**
     * You have to implement this (Please feel free to create other methods)
     * This method returns all the free time slots
     * @param times - All busy Time slots
     * @param dayStart - This is the earliest time slot for scheduling a meeting
     * @param day End - No meetings can be scheduled after this
     */
    public static List<Time> findFreeTime(List<List<Time>> times, int dayStart, int dayEnd) {
        int allBusyTimesInt = 0;
        for(List<Time> timeList : times) {
            allBusyTimesInt |= timeDescToIntBit(timeList);
        }
        int freeTimeInt = allBusyTimesInt ^ (Math.pow(2, 10));
        List<Time> freeTimeList = intBitToTimeDesc(freeTimeInt);
        return freeTimeList;
    }
    static int[] timeStarts = new int[] {9,10,11,12,13,14,15,16,17,18};
    public static  List<Time> intBitToTimeDesc(int timeInt) {
        List<Time> timeList = new ArrayList<>();
        for(int i=0;i<timeStarts.length;i++) {
            if ((timeInt >> i) & 1 > 0) {
                if (timeList.size() > 0) {
                    Time lastTime = timeList.get(timeList.size() -1);
                    if (lastTime.end - 9 == i) {
                        lastTime.end ++;
                        continue;
                    } else {
                        Time time = new Time(9+i,9+i+1);
                        timeList.add(time);
                    }
                } else {
                    Time time = new Time(9+i,9+i+1);
                    timeList.add(time);
                }
            }
        }
        return timeList;
    }
    public static int timeDescToIntBit(List<Time> times) {
        int timeIndex = 0;
        int result = 0;
        Time timeTemp = null;
        for(int i = 0; i< timeStarts.length;) {
            if(timeTemp == null) {
                timeTemp = timeStart.get(i);
            }
            i += 1 << (timeTemp.start - 9);
            if (timeTemp.end - timestart > 1) {
                timestart += 1;
            } else {
                timeTemp = null;
                i++;
            }
        }
        return result;
    }
}

class Time {
    int start;
    int end;

    public Time(int s, int e) {
        start = s;
        end = e;
    }
}
