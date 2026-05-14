public class Main {
    public static void main(String[] args) throws Exception {
        Star proximaCentauri = new Star(134, 1.5, "Proxima Centauri", "Red Dwarf", "Red", 3);
        Exoplanet proximaCentauriB = new Exoplanet(0.0037, 0.11, "Proxima Centauri B", "Superearth", false, 0, 234.0, "Incredibly Minimal", proximaCentauri);
        System.out.println(proximaCentauri.basicScan());
        System.out.println(proximaCentauri.scan());
        System.out.println(proximaCentauriB.basicScan());
        System.out.println(proximaCentauriB.scan());
    }
}