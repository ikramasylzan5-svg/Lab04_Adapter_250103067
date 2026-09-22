import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LegacyBulb rawBulb = new LegacyBulb();
        LegacyThermostat rawThermostat = new LegacyThermostat();

        BulbAdapter bulb = new BulbAdapter(rawBulb);
        ThermostatAdapter thermostat =
                new ThermostatAdapter(rawThermostat);

        List<SmartDevice> devices = new ArrayList<>();
        devices.add(bulb);
        devices.add(thermostat);
        ModernHub hub = new ModernHub(devices);

        hub.activateAll();
        System.out.println("Bulb on: " + bulb.isOn());
        System.out.println("Thermostat on: " + thermostat.isOn());
        System.out.println("Average: "
                + hub.calculateAveragePowerUsage());

        rawBulb.setBrightness(128);
        System.out.println("Bulb power: " + bulb.getPowerPercent());
        System.out.println("Calibrated average: "
                + hub.calculateAveragePowerUsage());

        rawThermostat.rotateDial("MAX");
        thermostat.turnOn();
        System.out.println("Dial: " + rawThermostat.checkDial());

        rawBulb.breakFilament();
        System.out.println("Broken bulb on: " + bulb.isOn());
        System.out.println("Broken bulb power: " + bulb.getPowerPercent());

        rawThermostat.rotateDial("STUCK");
        System.out.println("STUCK on: " + thermostat.isOn());
        System.out.println("STUCK power: " + thermostat.getPowerPercent());

        rawThermostat.rotateDial(null);
        System.out.println("Null on: " + thermostat.isOn());
        System.out.println("Null power: " + thermostat.getPowerPercent());
        
        hub.emergencyShutdown();
        System.out.println("Final brightness: " + rawBulb.readBrightness());
        System.out.println("Final dial: " + rawThermostat.checkDial());
        System.out.println("Final average: "
                + hub.calculateAveragePowerUsage());
    }
}