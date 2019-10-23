package meet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The Solution
 * 1）在一天内的24小时范围内，会议都会以整点和半点开始，如10:00或者10:30这样，会议的最小长度为半小时。
 * 2）对于将要添加的预约时间进行校验，判断是否和已有预约产生冲突，如果产生重叠，不能添加。
 * 3）（加分项）对你的算法增加单测（unit test）
 * @since 2019/10/23
 */
public class Solution {
    public static final int shortestDue = 30;
    static boolean scheduleMeeting(String startTime, String endTime, List<String[]> scheduleMeetings)
        throws ParseException {
        //参数校验
        if (startTime == null || endTime == null || !startTime.contains(":") || !endTime.contains(":")) {
            throw new IllegalArgumentException("Illegal Arguments with wrong format: " + startTime + ";" + endTime);
        }
        String startMm = startTime.split(":")[1];
        String endMm = endTime.split(":")[1];
        SimpleDateFormat myFmt = new SimpleDateFormat("HH:mm");
        Date start = myFmt.parse(startTime);
        Date end = myFmt.parse(endTime);
        long startMeet = start.getTime();
        long endMeet = end.getTime();
        int durtime = (int) (endMeet - startMeet)/(1000*60);
        //整点校验
        if (!("00".equals(startMm) || "30".equals(startMm)) || !("00".equals(endMm) || "30".equals(endMm))) {
            throw new IllegalArgumentException("Meeting time not in the Integer");
        }
        //会议长度校验
        if (durtime < shortestDue) {
            throw new IllegalArgumentException("Meeting duration is not large than 30m");
        }

        //对于将要添加的预约时间进行校验，判断是否和已有预约产生冲突
        for (int i = 0; i < scheduleMeetings.size(); i++) {
            String[] arg = scheduleMeetings.get(i);
            long scheduleStart = myFmt.parse(arg[0]).getTime();
            long scheduleEnd = myFmt.parse(arg[1]).getTime();
            if (arg[1].equals("00:00")) {
                arg[1] = "24:00";
            }
            if ((startMeet >= scheduleStart) && (startMeet < scheduleEnd)) {
                System.out.println("The time is conflict with the scheduled meet");
                return false;
            } else if ((endMeet > scheduleStart) && (endMeet <= scheduleEnd)) {
                System.out.println("The time is conflict with the scheduled meet!");
                return false;
            }
        }
        String[] str = {startTime, endTime};
        scheduleMeetings.add(str);
        for (int i = 0; i < scheduleMeetings.size(); i++) {
            String[] arg = scheduleMeetings.get(i);
            StringBuffer argbuf = new StringBuffer();
            for (int k = 0; k < arg.length; k++) {
                argbuf.append(arg[k] + " ");
            }
            System.out.println(argbuf);
        }
        return true;
    }

    /**
     * main
     * @param argus
     */
    public static void main(String[] argus) throws ParseException {

        //场景1
        List<String[]> scheduledMeetings = new ArrayList();
        String[] str1 = {"9:00", "10:00"};
        String[] str2 = {"11:00", "12:00"};
        String[] str3 = {"13:00", "13:30"};
        scheduledMeetings.add(str1);
        scheduledMeetings.add(str2);
        scheduledMeetings.add(str3);

        Scanner scan = new Scanner(System.in);
        String startTime = scan.next();  // 输入 13:30
        String endTime = scan.next();    // 输入 14:30
        scheduleMeeting(startTime, endTime, scheduledMeetings);

        //场景2
        String[] str4 = {"10:30", "11:30"};
        String[] str5 = {"14:00", "15:30"};
        scheduledMeetings.add(str4);  // 输入 13:30
        scheduledMeetings.add(str5);  // 输入 14:30

        String startTime1 = scan.next();
        String endTime1= scan.next();
        scheduleMeeting(startTime1, endTime1, scheduledMeetings);
    }
}
