/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import org.hibernate.Session;

/**
 *
 * @author Supun Madushanka
 */
//public class Connection {
//
//    private static Session session;
//
//    private Connection() {
//    }
//
//    public synchronized static Session getSession() {
//
//        if (session == null) {
//            session = pojos.sessionpoolmanager.getSessionFactory().openSession();
//        } else {
//            if (!session.isOpen()) {
//                session = pojos.sessionpoolmanager.getSessionFactory().openSession();
//            }
//            session = pojos.sessionpoolmanager.getSessionFactory().openSession();
//        }
//        return session;
//    }
//
//}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Connection;
//
//import org.hibernate.Session;

/**
 *
 * @author Supun Madushanka
 */
public class Connection {

    private static Session session=pojos.sessionpoolmanager.getSessionFactory().openSession();

    private Connection() {
    }

    public static Session getSession() {
        if(!session.isOpen()){
            System.out.println("session is not open");
            long count =session.getSessionFactory().getStatistics().getConnectCount();
            System.out.println(""+count);
            session=pojos.sessionpoolmanager.getSessionFactory().openSession();
        }
        return session;
    }
    
}

