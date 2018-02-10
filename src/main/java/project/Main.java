package main.java.project;

import main.java.project.track.Track;
import main.java.project.util.HibernateUtil;
import org.hibernate.Session;

public class Main {
    public static void main(final String[] args) throws Exception {
        if (args.length == 0){
            System.out.println("No input Track_name and Country");
            return;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();

        if (args.length == 2){

            session.beginTransaction();

            Track track = new Track();

            track.setTrackName(args[0]);
            track.setCountry(args[1]);

            session.save(track);

            session.getTransaction().commit();
        }

    }
}