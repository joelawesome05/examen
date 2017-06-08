package bo.ucbcba.taller.proyecto.proyecto;

import bo.ucbcba.taller.proyecto.proyecto.entities.Location;

import java.util.Comparator;

/**
 * Created by JOEL on 08/06/2017.
 */
public class LocationComparator implements Comparator<Location> {
    public int compare(Location e1, Location e2) {
        return e1.getRegisterDate().compareTo(e2.getRegisterDate());
    }
}
