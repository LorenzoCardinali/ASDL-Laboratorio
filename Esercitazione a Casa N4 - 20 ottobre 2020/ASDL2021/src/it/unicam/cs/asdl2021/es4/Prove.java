package it.unicam.cs.asdl2021.es4;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Prove {
    public static void main(String[] args) {
        /*
        //GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        //GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        //TimeSlot ts1 = new TimeSlot(g1, g2);

        System.out.println("    ");

        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts1 = new TimeSlot(g1, g2);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 13, 00);
        TimeSlot ts2 = new TimeSlot(g3, g4);

        ts2.equals(ts1);

        ts1.overlapsWith(ts2);

        System.out.println(ts1.toString() );
        */

        /*
        GregorianCalendar g1 = new GregorianCalendar(2019, 10, 4, 11, 00);
        GregorianCalendar g2 = new GregorianCalendar(2019, 10, 4, 12, 00);
        GregorianCalendar g3 = new GregorianCalendar(2019, 10, 4, 13, 00);
        GregorianCalendar g4 = new GregorianCalendar(2019, 10, 4, 14, 00);
        GregorianCalendar g5 = new GregorianCalendar(2019, 10, 4, 15, 00);
        GregorianCalendar g6 = new GregorianCalendar(2019, 10, 4, 16, 00);
        GregorianCalendar g7 = new GregorianCalendar(2019, 10, 4, 17, 00);
        GregorianCalendar g8 = new GregorianCalendar(2019, 10, 4, 18, 00);

        //same
        TimeSlot ts1 = new TimeSlot(g1, g2);
        TimeSlot ts2 = new TimeSlot(g1, g2);
        ts1.overlapsWith(ts2);
        ts2.overlapsWith(ts1);

        //annidati
        TimeSlot ts3 = new TimeSlot(g1, g8);
        TimeSlot ts4 = new TimeSlot(g3, g6);
        ts3.overlapsWith(ts4);
        ts4.overlapsWith(ts3);

        //prima A poi B
        TimeSlot ts5 = new TimeSlot(g1, g6);
        TimeSlot ts6 = new TimeSlot(g4, g7);
        ts5.overlapsWith(ts6);
        ts6.overlapsWith(ts5);

        //prima B poi A
        TimeSlot ts7 = new TimeSlot(g4, g7);
        TimeSlot ts8 = new TimeSlot(g1, g6);
        ts7.overlapsWith(ts8);
        ts8.overlapsWith(ts7);

        //prima A poi B con ora comune
        TimeSlot ts9 = new TimeSlot(g1, g3);
        TimeSlot ts10 = new TimeSlot(g3, g5);
        ts9.overlapsWith(ts10);
        ts10.overlapsWith(ts9);

        */

        Facility f1 = new PresenceFacility("HDMI",
                "Presenza di un proiettore HDMI");
        Facility f2 = new PresenceFacility("12334",
                "Presenza di proiettore HDMI");
        Facility f3 = new PresenceFacility("5555",
                "Presenza di posti a sedere normali");
        Facility f4 = new PresenceFacility("4345",
                "Presenza di posti a sedere normali");
        Facility f5 = new PresenceFacility("POSTITC",
                "Presenza di posti a sedere con Thin Client");
        Aula a = new Aula("LA1", "Polo Lodovici Piano Terra");
        Aula b = new Aula("LA2", "Polo 234234");
        a.addFacility(f1);
        a.addFacility(f2);
        a.addFacility(f5);
        b.addFacility(f3);
        b.addFacility(f4);


        System.out.println("Facilities di A > " + a.getNumeroFacilities());
        System.out.println(a.getFacilities());
        System.out.println();
        System.out.println("Facilities di B > " + b.getNumeroFacilities());
        System.out.println(b.getFacilities());

    }
}
