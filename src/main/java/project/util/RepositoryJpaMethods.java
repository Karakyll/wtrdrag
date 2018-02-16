package project.util;

import net.bytebuddy.utility.RandomString;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.config.DataConfig;
import project.entity.Car;
import project.entity.Sponsor;
import project.service.*;

public class RepositoryJpaMethods {

    public static void run(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataConfig.class);

        SponsorService sponsorService = context.getBean(SponsorService.class);
        CarService carService = context.getBean(CarService.class);
        TrackService trackService = context.getBean(TrackService.class);
        RaceService raceService = context.getBean(RaceService.class);

        //sponsorService.getAllSponsors().forEach(System.out::println);
        //trackService.getAllTracks().forEach(System.out::println);
        //carService.getAllCars().forEach(System.out::println);
        //raceService.getAllRaces().forEach(System.out::println);

        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorName(String.format("Bielita%s", RandomString.make(3)));
        sponsor.setSponsorSlogan("Go go bielita!");

        Car car = new Car();
        car.setMark("Volvo");
        car.setModel("XC90");
        car.setPilotFirstName("Justin");
        car.setPilotLastName("Bibier");
        car.setPower(190);
        car.setTorque(200);
        car.setSponsor(sponsor);
        car.setSpec("Full stock.");


        System.out.println("====================================== " +
                "Sponsors before saving using JPA rep methods. " +
                "=============================================");
        sponsorService.getAllSponsors().forEach(System.out::println);

        sponsorService.save(sponsor);
        carService.save(car);


        System.out.println("====================================== " +
                "Sponsors after saving using JPA rep methods (shows using native query). " +
                "==============================================");
        sponsorService.getAllSponsorsUsingNativeQuery().forEach(System.out::println);

        System.out.println("====================================== " +
                "Showing @query method example (all cars with power greater than 200). " +
                "==============================================");
        carService.findByPowerGreaterThan(200).forEach(System.out::println);

        System.out.println("====================================== " +
                "Showing method using methodName (all cars with mark = BMW, and model = 335i " +
                "==============================================");
        System.out.println(carService.findByMarkAndModel("BMW", "335i"));

    }

}