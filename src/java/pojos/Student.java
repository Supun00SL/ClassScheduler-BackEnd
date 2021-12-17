package pojos;
// Generated Nov 13, 2017 1:11:38 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Student generated by hbm2java
 */
public class Student  implements java.io.Serializable {


     private Integer idstudent;
     private String fname;
     private String lname;
     private String nic;
     private String email;
     private String mobile;
     private String telephone;
     private String state;
     private String devicetoken;
     private Set<Chatlist> chatlists = new HashSet<Chatlist>(0);
     private Set<Studentlogin> studentlogins = new HashSet<Studentlogin>(0);
     private Set<Specialnotifications> specialnotificationses = new HashSet<Specialnotifications>(0);
     private Set<Notificationlist> notificationlists = new HashSet<Notificationlist>(0);
     private Set<StudentHasSubjectvisebatch> studentHasSubjectvisebatches = new HashSet<StudentHasSubjectvisebatch>(0);

    public Student() {
    }

    public Student(String fname, String lname, String nic, String email, String mobile, String telephone, String state, String devicetoken, Set<Chatlist> chatlists, Set<Studentlogin> studentlogins, Set<Specialnotifications> specialnotificationses, Set<Notificationlist> notificationlists, Set<StudentHasSubjectvisebatch> studentHasSubjectvisebatches) {
       this.fname = fname;
       this.lname = lname;
       this.nic = nic;
       this.email = email;
       this.mobile = mobile;
       this.telephone = telephone;
       this.state = state;
       this.devicetoken = devicetoken;
       this.chatlists = chatlists;
       this.studentlogins = studentlogins;
       this.specialnotificationses = specialnotificationses;
       this.notificationlists = notificationlists;
       this.studentHasSubjectvisebatches = studentHasSubjectvisebatches;
    }
   
    public Integer getIdstudent() {
        return this.idstudent;
    }
    
    public void setIdstudent(Integer idstudent) {
        this.idstudent = idstudent;
    }
    public String getFname() {
        return this.fname;
    }
    
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return this.lname;
    }
    
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getNic() {
        return this.nic;
    }
    
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public String getDevicetoken() {
        return this.devicetoken;
    }
    
    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }
    public Set<Chatlist> getChatlists() {
        return this.chatlists;
    }
    
    public void setChatlists(Set<Chatlist> chatlists) {
        this.chatlists = chatlists;
    }
    public Set<Studentlogin> getStudentlogins() {
        return this.studentlogins;
    }
    
    public void setStudentlogins(Set<Studentlogin> studentlogins) {
        this.studentlogins = studentlogins;
    }
    public Set<Specialnotifications> getSpecialnotificationses() {
        return this.specialnotificationses;
    }
    
    public void setSpecialnotificationses(Set<Specialnotifications> specialnotificationses) {
        this.specialnotificationses = specialnotificationses;
    }
    public Set<Notificationlist> getNotificationlists() {
        return this.notificationlists;
    }
    
    public void setNotificationlists(Set<Notificationlist> notificationlists) {
        this.notificationlists = notificationlists;
    }
    public Set<StudentHasSubjectvisebatch> getStudentHasSubjectvisebatches() {
        return this.studentHasSubjectvisebatches;
    }
    
    public void setStudentHasSubjectvisebatches(Set<StudentHasSubjectvisebatch> studentHasSubjectvisebatches) {
        this.studentHasSubjectvisebatches = studentHasSubjectvisebatches;
    }




}

