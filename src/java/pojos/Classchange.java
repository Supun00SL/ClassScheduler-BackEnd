package pojos;
// Generated Nov 13, 2017 1:11:38 PM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Classchange generated by hbm2java
 */
public class Classchange  implements java.io.Serializable {


     private Integer idclasschange;
     private String day;
     private Date datetime;

    public Classchange() {
    }

    public Classchange(String day, Date datetime) {
       this.day = day;
       this.datetime = datetime;
    }
   
    public Integer getIdclasschange() {
        return this.idclasschange;
    }
    
    public void setIdclasschange(Integer idclasschange) {
        this.idclasschange = idclasschange;
    }
    public String getDay() {
        return this.day;
    }
    
    public void setDay(String day) {
        this.day = day;
    }
    public Date getDatetime() {
        return this.datetime;
    }
    
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }




}


