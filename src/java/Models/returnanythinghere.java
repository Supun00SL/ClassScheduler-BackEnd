/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;
import pojos.Examvivashedule;
import pojos.Normalclassshedule;
import pojos.StudentHasSubjectvisebatch;

/**
 *
 * @author Supun Madushanka
 */
public class returnanythinghere {

    public boolean ada_dawasata_cls_thyenawa(Date date, pojos.Student student1) {

        boolean flag = false;
        Session session = connection.Connection.getSession();
        pojos.Student student = (pojos.Student) session.load(pojos.Student.class, student1.getIdstudent());

        Set<pojos.StudentHasSubjectvisebatch> hasSubjectvisebatchs = student.getStudentHasSubjectvisebatches();

        for (StudentHasSubjectvisebatch studentHasSubjectvisebatch : hasSubjectvisebatchs) {
            Criteria classCriteria = session.createCriteria(pojos.Normalclassshedule.class);
            classCriteria.add(Restrictions.eq("subjectvisebatch", studentHasSubjectvisebatch.getSubjectvisebatch()));
            classCriteria.add(Restrictions.eq("date", date));
            classCriteria.add(Restrictions.eq("state", "active"));

            List<pojos.Normalclassshedule> normalclassshedules = classCriteria.list();

            if (!normalclassshedules.isEmpty()) {
                flag = true;

            }
        }
        return flag;
    }

    public boolean ada_dawasata_exam_thyenawa(Date date, pojos.Student student1) {

        boolean flag = false;
        Session session = connection.Connection.getSession();

        pojos.Student student = (pojos.Student) session.load(pojos.Student.class, student1.getIdstudent());
        Set<pojos.StudentHasSubjectvisebatch> hasSubjectvisebatchs = student.getStudentHasSubjectvisebatches();

        for (StudentHasSubjectvisebatch studentHasSubjectvisebatch : hasSubjectvisebatchs) {
            Criteria examCriteria = session.createCriteria(pojos.Examvivashedule.class);
            examCriteria.add(Restrictions.eq("subjectvisebatch", studentHasSubjectvisebatch.getSubjectvisebatch()));
            examCriteria.add(Restrictions.eq("date", date));
            examCriteria.add(Restrictions.eq("state", "active"));

            List<pojos.Examvivashedule> examvivashedules = examCriteria.list();

            if (!examvivashedules.isEmpty()) {
                flag = true;

            }
        }
        return flag;
    }

    public String popOverekka_dateeka(Date date, pojos.Student student, int day) {
        String value = "";

        Session session = connection.Connection.getSession();

        Set<pojos.StudentHasSubjectvisebatch> hasSubjectvisebatchs = student.getStudentHasSubjectvisebatches();

        value += "<li>";
        value += "<span class='active'>";
        value += "<a href='#'>" + day;

        for (StudentHasSubjectvisebatch studentHasSubjectvisebatch : hasSubjectvisebatchs) {
            Criteria classCriteria = session.createCriteria(pojos.Normalclassshedule.class);
            classCriteria.add(Restrictions.eq("studentHasSubjectvisebatch", studentHasSubjectvisebatch));
            classCriteria.add(Restrictions.eq("date", date));
            classCriteria.add(Restrictions.eq("state", "active"));

            List<pojos.Normalclassshedule> normalclassshedules = classCriteria.list();

            if (!normalclassshedules.isEmpty()) {
                for (Normalclassshedule normalclassshedule : normalclassshedules) {

//                    value += "        <h4>" + studentHasSubjectvisebatch.getSubjectvisebatch().getSubject().getSubject() + " (" + studentHasSubjectvisebatch.getSubjectvisebatch().getSubject().getSubjectcode() + ")</h4>"
//                            + "        <div class='chip chip-label'>Start :" + new SimpleDateFormat("HH:mm").format(normalclassshedule.getStarttime()) + " End :" + new SimpleDateFormat("HH:mm").format(normalclassshedule.getEndtime()) + "</div>"
//                            + "        <p>Lecturer :" + studentHasSubjectvisebatch.getSubjectvisebatch().getLecturer().getFname() + " " + studentHasSubjectvisebatch.getSubjectvisebatch().getLecturer().getLname() + "</p>"
//                            + "        <p>Location :" + normalclassshedule.getLocation() + "-" + normalclassshedule.getLecturehall().getLecturehallnumber() + "</p><hr>";
                }

            }
        }
        value += "</a>";
        value += "</span>";
        value += "</li>";

        return value;

    }

    public String exampopOverekka_dateeka(Date date, pojos.Student student, int day) {
        String value = "";

        Session session = connection.Connection.getSession();

        Set<pojos.StudentHasSubjectvisebatch> hasSubjectvisebatchs = student.getStudentHasSubjectvisebatches();

        value += "<li>";
        value += "<span class='active'>";
        value += "<a href='#' data-popover='.popover-e" + day + "' class='open-popover'>" + day;
        value += "<div class='popover popover-e" + day + "'>";
        value += "    <div class='popover-angle'></div>'";
        value += "    <div class='popover-inner'>";
        value += "      <div class='content-block'>";

        for (StudentHasSubjectvisebatch studentHasSubjectvisebatch : hasSubjectvisebatchs) {
            Criteria examCriteria = session.createCriteria(pojos.Examvivashedule.class);
            examCriteria.add(Restrictions.eq("studentHasSubjectvisebatch", studentHasSubjectvisebatch));
            examCriteria.add(Restrictions.eq("date", date));
            examCriteria.add(Restrictions.eq("state", "active"));

            List<pojos.Examvivashedule> examshedules = examCriteria.list();

            if (!examshedules.isEmpty()) {
                for (Examvivashedule examvivashedule : examshedules) {

                    value += "        <h4>" + studentHasSubjectvisebatch.getSubjectvisebatch().getSubject().getSubject() + " (" + studentHasSubjectvisebatch.getSubjectvisebatch().getSubject().getSubjectcode() + ")</h4>"
                            + "        <div class='chip chip-label'>Start :" + new SimpleDateFormat("HH:mm").format(examvivashedule.getStarttime()) + " End :" + new SimpleDateFormat("HH:mm").format(examvivashedule.getEndtime()) + "</div>"
                            + "        <p>Lecturer :" + studentHasSubjectvisebatch.getSubjectvisebatch().getLecturer().getFname() + " " + studentHasSubjectvisebatch.getSubjectvisebatch().getLecturer().getLname() + "</p>"
                            + "        <p>Location :" + examvivashedule.getLocation() + "-" + examvivashedule.getLecturehall().getLecturehallnumber() + "</p><hr>";

                }

            }
        }
        value += "      </div>";
        value += "  </div>";
        value += "  </div>";
        value += "</a>";
        value += "</span>";
        value += "</li>";

        return value;

    }

    public String getdaysCount(String date, String time) {
        String Date = date;
        String Time = time;
        try {
            SimpleDateFormat returnDateFormat = new SimpleDateFormat("MMM dd");

            String Today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Date fromDay = new SimpleDateFormat("yyyy-MM-dd").parse(Date);
            Date today = new SimpleDateFormat("yyyy-MM-dd").parse(Today);

            String NowTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
            Date fromtime = new SimpleDateFormat("HH:mm:ss").parse(Time);
            Date nowtime = new SimpleDateFormat("HH:mm:ss").parse(NowTime);

            LocalDate from = LocalDate.fromDateFields(fromDay);
            LocalDate to = LocalDate.fromDateFields(today);

            LocalTime fromtimee = LocalTime.fromDateFields(fromtime);
            LocalTime totime = LocalTime.fromDateFields(nowtime);

            Days days = Days.daysBetween(from, to);
            Weeks weeks = Weeks.weeksBetween(from, to);
            Months months = Months.monthsBetween(from, to);
            Years years = Years.yearsBetween(from, to);

            int daysCount = days.getDays();
            int weeksCount = weeks.getWeeks();
            int monthsCount = months.getMonths();
            int yearsCount = years.getYears();

            Seconds seconds = Seconds.secondsBetween(fromtimee, totime);
            Minutes minutes = Minutes.minutesBetween(fromtimee, totime);
            Hours hours = Hours.hoursBetween(fromtimee, totime);

            int secondsCount = seconds.getSeconds();
            int minutesCount = minutes.getMinutes();
            int hoursCount = hours.getHours();

            System.out.println("days :" + daysCount);
            if (daysCount == 0) {
                if (secondsCount < 60) {
                    return secondsCount + " seconds ago";

                } else if (secondsCount > 60 & minutesCount < 60) {
                    return minutesCount + " minutes ago";
                } else {
                    return hoursCount + " hours ago";
                }
            } else if (daysCount < 7) {
                return daysCount + " days ago";
            } else {
                return returnDateFormat.format(returnDateFormat.parse(Date));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
