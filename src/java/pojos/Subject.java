package pojos;
// Generated Nov 13, 2017 1:11:38 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Subject generated by hbm2java
 */
public class Subject  implements java.io.Serializable {


     private Integer idsubject;
     private String subject;
     private String subjectcode;
     private String state;
     private Set<Subjectvisebatch> subjectvisebatches = new HashSet<Subjectvisebatch>(0);

    public Subject() {
    }

    public Subject(String subject, String subjectcode, String state, Set<Subjectvisebatch> subjectvisebatches) {
       this.subject = subject;
       this.subjectcode = subjectcode;
       this.state = state;
       this.subjectvisebatches = subjectvisebatches;
    }
   
    public Integer getIdsubject() {
        return this.idsubject;
    }
    
    public void setIdsubject(Integer idsubject) {
        this.idsubject = idsubject;
    }
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getSubjectcode() {
        return this.subjectcode;
    }
    
    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    public Set<Subjectvisebatch> getSubjectvisebatches() {
        return this.subjectvisebatches;
    }
    
    public void setSubjectvisebatches(Set<Subjectvisebatch> subjectvisebatches) {
        this.subjectvisebatches = subjectvisebatches;
    }




}

