package bo.ucbcba.taller.proyecto.proyecto.controllers;

import bo.ucbcba.taller.proyecto.proyecto.LocationComparator;
import bo.ucbcba.taller.proyecto.proyecto.entities.Band;
import bo.ucbcba.taller.proyecto.proyecto.entities.Location;
import bo.ucbcba.taller.proyecto.proyecto.entities.User;
import bo.ucbcba.taller.proyecto.proyecto.services.BandService;
import bo.ucbcba.taller.proyecto.proyecto.services.LocationService;
import bo.ucbcba.taller.proyecto.proyecto.services.StepService;
import bo.ucbcba.taller.proyecto.proyecto.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by CORE i7 on 31/05/2017.
 */
@Controller
public class LocationController {

    LocationService locationService;
    @Autowired
    private UserService userService;
    @Autowired
    private BandService bandService;


    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/admin/locations", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("locations", locationService.listAllLocations());
        return "locations";
    }

    @RequestMapping(value = "/admin/searchResultsLocation", method = RequestMethod.POST)
    public String listResultsLocation(String dateStart, String dateEnd,Model model){

        String dateTime = dateEnd;

        DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-mm-dd");

        DateTime jodatime = dtf.parseDateTime(dateTime);

        DateTime dtPlusOne = jodatime.plusDays(1);

        dateTime =dtPlusOne.toString("YYYY-mm-dd");

        dateStart=dateStart.replace("-", "");
        dateTime=dateTime.replace("-", "");

        model.addAttribute("dateStart", dateStart);
        model.addAttribute("dateEnd", dateTime);

        model.addAttribute("locations",locationService.listAllSearchedLocations(dateStart,dateTime));

        return "locations";
    }

    @RequestMapping(value = "/distance", method = RequestMethod.GET)
    public ModelAndView distance(Model model) {

        ModelAndView modelAndView = new ModelAndView();
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        Band band = bandService.getBandById(1);
        Set<Location> locations = band.getLocations();

        List<Location> locationList = new ArrayList<Location>();

        locationList.addAll(locations);
        Collections.sort(locationList, new LocationComparator());

        BigDecimal x,y;

        BigInteger sumatoriaTotal = new BigInteger("0");
        for (int pos=0;pos<locationList.size()-1;pos++){
            x = locationList.get(pos).getLongitude().subtract( locationList.get(pos+1).getLongitude());
            y = locationList.get(pos).getLatitude().subtract( locationList.get(pos+1).getLatitude());
            x = x.multiply(x);
            y = y.multiply(y);

            BigDecimal sum = x.add(y);

            if (sum.compareTo(new BigDecimal("1000")) < 0) {

                BigInteger resp = sum.toBigInteger();
                BigInteger finalResp = new BigInteger("0");


                BigInteger a= BigInteger.ZERO.setBit(resp.bitLength()/2);
                BigInteger b= a;
                boolean ciclo = true;
                while(ciclo) {
                    BigInteger c = a.add(resp.divide(a)).shiftRight(1);
                    if (c.equals(a) || c.equals(b)){
                        ciclo = false;
                        finalResp = c;
                    }
                    b= a;
                    a= c;
                }

                sumatoriaTotal = sumatoriaTotal.add(finalResp);
            }

        }
        modelAndView.addObject("distancia",sumatoriaTotal);
        modelAndView.setViewName("distance");
        return modelAndView;
    }

}
