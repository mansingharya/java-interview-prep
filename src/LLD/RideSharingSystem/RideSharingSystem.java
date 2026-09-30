package LLD.RideSharingSystem;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


class Location {

    private final double lat;
    private final double longi;

    Location(double lat, double longi) {
        this.lat = lat;
        this.longi = longi;
    }

    public double getLat() {
        return lat;
    }

    public double getLongi() {
        return longi;
    }

    public double distanceTo(Location other) {
        double dLat = this.lat - other.lat;
        double dLongi = this.longi - other.longi;

        return Math.sqrt(dLat * dLat + dLongi * dLongi);
    }

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", longi=" + longi +
                '}';
    }
}


class Passenger {
    private final String passengerId;
    private final String name;
    private Location currentLocation;

    public Passenger(String passengerId, String name, Location currentLocation) {
        if (passengerId == null || passengerId.isBlank()) {
            throw new IllegalArgumentException("PassengerId cannot be blank");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Passenger name cannot be null");
        }

        if (currentLocation == null) {
            throw new IllegalArgumentException("Location cannot be null");
        }

        this.passengerId = passengerId;
        this.name = name;
        this.currentLocation = currentLocation;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId='" + passengerId + '\'' +
                ", name='" + name + '\'' +
                ", currentLocation=" + currentLocation +
                '}';
    }
}


enum DriverStatus { AVAILABLE, ON_RIDE }

class Driver {
    private final String driverId;
    private final String name;
    private final String vehicleInfo;
    private Location currentLocation;
    private DriverStatus status;

    public Driver(String driverId, String name, String vehicleInfo, Location currentLocation, DriverStatus status) {
        // TODO - Add Validation on driverId, name, vehicleInfo and currentLocation

        this.driverId = driverId;
        this.name = name;
        this.vehicleInfo = vehicleInfo;
        this.currentLocation = currentLocation;
        this.status = status;
    }

    public String getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nDriver{" +
                "driverId='" + driverId + '\'' +
                ", name='" + name + '\'' +
                ", vehicleInfo='" + vehicleInfo + '\'' +
                ", currentLocation=" + currentLocation +
                ", status=" + status +
                '}';
    }
}


enum RideStatus { PENDING, ACCEPTED, COMPLETED }


class Ride {

    private final String rideId;
    private final String passengerId;
    private final String driverId;
    private final Location startLocation;
    private final Location endLocation;
    private RideStatus status;

    public Ride(String rideId, String passengerId, String driverId, Location startLocation, Location endLocation, RideStatus status) {
        // TODO - Add Validations on RideId, PassengerId, startLocation and endLocation

        this.rideId = rideId;
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.status = status;
    }

    public String getRideId() {
        return rideId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public String getDriverId() {
        return driverId;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\nRide{" +
                "rideId='" + rideId + '\'' +
                ", passengerId='" + passengerId + '\'' +
                ", driverId='" + driverId + '\'' +
                ", startLocation=" + startLocation +
                ", endLocation=" + endLocation +
                ", status=" + status +
                '}';
    }
}


interface PassengerRepository {
    void save(Passenger passenger);
    Optional<Passenger> findById(String passengerId);
    List<Passenger> findAll();
}

class InMemoryPassengerRepository implements PassengerRepository {
    private final Map<String, Passenger> passengerMap = new ConcurrentHashMap<>();

    @Override
    public void save(Passenger passenger) {
        passengerMap.put(passenger.getPassengerId(), passenger);
    }

    @Override
    public Optional<Passenger> findById(String passengerId) {
        return Optional.ofNullable(passengerMap.get(passengerId));
    }

    @Override
    public List<Passenger> findAll() {
        return new ArrayList<>(passengerMap.values());
    }
}


interface DriverRepository {
    void save(Driver driver);
    Optional<Driver> findById(String driverId);
    List<Driver> findAll();
    List<Driver> findAvailableDriver();
}

class InMemoryDriverRepository implements DriverRepository {
    private final Map<String, Driver> driverMap = new ConcurrentHashMap<>();

    @Override
    public void save(Driver driver) {
        driverMap.put(driver.getDriverId(), driver);
    }

    @Override
    public Optional<Driver> findById(String driverId) {
        return Optional.ofNullable(driverMap.get(driverId));
    }

    @Override
    public List<Driver> findAll() {
        return new ArrayList<>(driverMap.values());
    }

    @Override
    public List<Driver> findAvailableDriver() {
        return driverMap.values().stream()
                .filter(d -> d.getStatus() == DriverStatus.AVAILABLE)
                .collect(Collectors.toList());
    }
}


interface RideRepository {
    void save(Ride ride);
    Optional<Ride> findById(String rideId);
    List<Ride> findAll();
}

class InMemoryRideRepository implements RideRepository {
    private final Map<String, Ride> rideMap = new ConcurrentHashMap<>();

    @Override
    public void save(Ride ride) {
        rideMap.put(ride.getRideId(), ride);
    }

    @Override
    public Optional<Ride> findById(String rideId) {
        return Optional.ofNullable(rideMap.get(rideId));
    }

    @Override
    public List<Ride> findAll() {
        return new ArrayList<>(rideMap.values());
    }
}


class RideService {

    private final PassengerRepository passengerRepo;
    private final DriverRepository driverRepo;
    private final RideRepository rideRepo;

    private final AtomicInteger rideCounter = new AtomicInteger(1);


    public RideService(PassengerRepository passengerRepo, DriverRepository driverRepo, RideRepository rideRepo) {
        this.passengerRepo = passengerRepo;
        this.driverRepo = driverRepo;
        this.rideRepo = rideRepo;
    }

    private Passenger getPassengerOrThrow(String id) {
        return passengerRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No Passenger Found"));
    }

    private Driver getDriverOrThrow(String id) {
        return driverRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No Driver Found"));
    }

    private Ride getRideOrThrow(String id) {
        return rideRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No Ride Found"));
    }

    public Passenger addPassenger(String id, String name, Location loc) {
        Passenger p = new Passenger(id, name, loc);
        passengerRepo.save(p);
        return p;
    }

    public Driver addDriver(String id, String name, String vehicleInfo, Location loc) {
        Driver d = new Driver(id, name, vehicleInfo, loc, DriverStatus.AVAILABLE);
        driverRepo.save(d);
        return d;
    }

    Optional<Driver> findNearestAvailableDriver(Location from) {
        return driverRepo.findAvailableDriver().stream()
                .min(Comparator.comparingDouble(
                        d -> d.getCurrentLocation().distanceTo(from)));
    }

/*
    API - POST /request_ride
    Params - passengerId, startLocation, endLocation

    1. Find Driver
    2. Link the Driver with Ride.
    3. Ride Status = PENDING.
 */

    public Ride requestRide(String passengerId, Location stat, Location end) {

        Passenger p = getPassengerOrThrow(passengerId);
        Driver d = findNearestAvailableDriver(stat).orElseThrow(() -> new IllegalArgumentException("No Driver Found"));

        String rideId = "RIDE-" + rideCounter.getAndIncrement();
        Ride r = new Ride(rideId, p.getPassengerId(), d.getDriverId(), stat, end, RideStatus.PENDING);

        rideRepo.save(r);
        return r;
    }


/*
    API - POST /accept_ride
    Params - rideId, driverId

    1. Ride.Status = Accepted
    2. Driver.Status = On-Ride
*/
    public Ride acceptRide(String rideId, String driverId) {

        Ride r = getRideOrThrow(rideId);
        Driver d = getDriverOrThrow(driverId);

        r.setStatus(RideStatus.ACCEPTED);
        d.setStatus(DriverStatus.ON_RIDE);

        return r;
    }


/*
    API - POST /complete_ride
    Params - rideId, driverId

    1. Ride.Status = Completed
    2. Driver.Status = Available
 */
    public Ride completeRide(String rideId, String driverId) {

        Ride r = getRideOrThrow(rideId);
        Driver d = getDriverOrThrow(driverId);

        r.setStatus(RideStatus.COMPLETED);
        d.setStatus(DriverStatus.AVAILABLE);

        return r;
    }

    public List<Driver> getAvailableDriversNearLocation(Location passengerLoc, double radius) {
        return driverRepo.findAvailableDriver().stream()
                .filter(d -> d.getCurrentLocation().distanceTo(passengerLoc) <= radius)
                .sorted(Comparator.comparingDouble(d -> d.getCurrentLocation().distanceTo(passengerLoc)))
                .collect(Collectors.toList());
    }

    public List<Driver> getAvailableDrivers() {
        return driverRepo.findAvailableDriver();
    }
}


class RideController {

    private final RideService rideService;

    RideController(RideService rideService) {
        this.rideService = rideService;
    }

    public Ride requestRide(String passengerId, Location start, Location end) {
        return rideService.requestRide(passengerId, start, end);
    }

    public Ride acceptRide(String rideId, String driverId) {
        return rideService.acceptRide(rideId, driverId);
    }

    public Ride completeRide(String rideId, String driverId) {
        return rideService.completeRide(rideId, driverId);
    }

    public List<Driver> getAvailableDrivers() {
        return rideService.getAvailableDrivers();
    }
}


public class RideSharingSystem {

    static void main(String[] args) {

        PassengerRepository passengerRepository = new InMemoryPassengerRepository();
        DriverRepository driverRepository = new InMemoryDriverRepository();
        RideRepository rideRepository = new InMemoryRideRepository();

        RideService rideService = new RideService(passengerRepository, driverRepository, rideRepository);
        RideController rideController = new RideController(rideService);

        Passenger p1 = rideService.addPassenger("P1", "Man", new Location(12.97, 77.59));
        Passenger p2 = rideService.addPassenger("P2", "Singh", new Location(12.90, 77.65));

        Driver d1 = rideService.addDriver("D1", "Rajan", "Toyota", new Location(12.96, 77.60));
        Driver d2 = rideService.addDriver("D2", "Suresh", "Honda", new Location(12.85, 77.70));
        Driver d3 = rideService.addDriver("D3", "Kiran", "Maruti", new Location(13.00, 77.55));

        List<Driver> availableDrivers = rideController.getAvailableDrivers();
        System.out.println(availableDrivers);

        System.out.println();
        Ride ride1 = rideController.requestRide("P1", new Location(12.97, 77.59), new Location(12.93, 77.68));
        System.out.println(ride1);

        ride1 = rideController.acceptRide(ride1.getRideId(), ride1.getDriverId());
        System.out.println(ride1);

        Ride ride2 = rideController.requestRide("P2", new Location(12.90, 77.65), new Location(13.05, 77.45));
        System.out.println(ride2);

        ride1 = rideController.completeRide(ride1.getRideId(), ride1.getDriverId());
        System.out.println(ride1);
    }

}
