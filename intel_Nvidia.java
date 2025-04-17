import java.util.List;
import java.util.Arrays;

public class intel_Nvidia implements ComponenteFactory {

	public List<CPU> getCPUs(){

		return Arrays.asList(
            new CPUIntel("Core i3-13100", 2805,"Intel", "CPU" ,4),
            new CPUIntel("Core i5-13600K", 5799,"Intel", "CPU" ,14),
            new CPUIntel("Core i7-13700K", 8750.69,"Intel", "CPU", 16),
            new CPUIntel("Core i9-13900K", 10899.53,"Intel", "CPU" ,24)
        );

	}
   public  List<GPU> getGPUs(){

    	return Arrays.asList( 
    		new GPUnvidia("GTX 1660", 4000, "Nvidia", "GPU", "GDDR6"),
    		new GPUnvidia("RTX 3060", 6329, "Nvidia", "GPU", "GDDR6"),
    		new GPUnvidia("RTX 4070", 25000,"Nvidia", "GPU", "GDDR6" ),
    		new GPUnvidia("RTX 4080", 34552.61, "Nvidia", "GPU", "GDDR6X" ),
    		new GPUnvidia("RTX 4090", 64950,"Nvidia", "GPU", "GDDR6X")
    	);

    }
    public List<RAM> getRAM(){

    	return Arrays.asList( 

    	new productoRAM("memoria RAM 8GB adata", 351, "Adata","RAM", 8),
    	new productoRAM("memoria RAM 16GB adata", 596, "Adata","RAM", 16),
    	new productoRAM("memoria RAM 32GB adata", 999, "Adata", "RAM",32),
    	new productoRAM("memoria RAM 8GB Kingston", 391, "Kingston", "RAM",8),
    	new productoRAM("memoria RAM 16GB Kingston", 710, "Kingston", "RAM",16),
    	new productoRAM("memoria RAM 32GB Kingston", 1261, "Kingston","RAM", 32)

    );

    }
    public List<Motherboard> getMotherboard(){

    	return Arrays.asList( 
    		new MotherboardIntel("ROG Maximus Z790 Hero", 13490, "ASUS","Motherboard", "IntelZ790"),
    		new MotherboardIntel("TUF Gaming B760-Plus WIFI D4", 4835.62, "ASUS", "Motherboard", "IntelB760"),
    		new MotherboardIntel("MEG Z790 Godlike", 13821.82,"MSI","Motherboard", "IntelZ790" ),
    		new MotherboardIntel("MAG B760 Tomahawk WIFI DDR4", 5862.82, "MSI","Motherboard","IntelB760")
    	);



    }
    public List<FuenteDePoder> getFuente(){

    	return Arrays.asList(

    	new productoFuenteDePoder("Fuente de poder EVGA 800w", 1919, "EVGA","Fuente de poder", 800),
    	new productoFuenteDePoder("Fuente de poder EVGA 1000w", 4039, "EVGA","Fuente de poder", 1000),
    	new productoFuenteDePoder("Fuente de poder EVGA 1500w", 7548, "EVGA","Fuente de poder", 1500),
    	new productoFuenteDePoder("Fuente de poder Corsair 800w", 2893.89, "Corsair","Fuente de poder", 800),
    	new productoFuenteDePoder("Fuente de poder Corsair 1200w", 5441, "Corsair","Fuente de poder", 1200),
    	new productoFuenteDePoder("Fuente de poder Corsair 1500w", 8130, "Corsair","Fuente de poder", 1500),
    	new productoFuenteDePoder("Fuente de poder XPG 500w", 799, "XPG","Fuente de poder", 800),
    	new productoFuenteDePoder("Fuente de poder XPG 700w", 1428.98, "XPG","Fuente de poder", 800),
   		new productoFuenteDePoder("Fuente de poder XPG 1000w", 3259, "XPG","Fuente de poder", 800)

    
    );

    }
    public List<Almacenamiento> getAlmacenamiento(){

    	return Arrays.asList(

    	new productoAlmacenamiento("HDD Western Digital Blue 500GB", 514.79,"Western Digital Blue","Almacenamineto", 500, "HDD"),
    	new productoAlmacenamiento("HDD Western Digital Blue 1TB", 1548,"Western Digital Blue","Almacenamineto", 500, "HDD"),
    	new productoAlmacenamiento("HDD Seagate Barracuda 1TB", 1141,"Seagate Barracuda","Almacenamineto", 1000, "HDD"),
    	new productoAlmacenamiento("HDD Seagate Barracuda 2TB", 3721,"Seagate Barracuda","Almacenamineto", 2000, "HDD"),
    	new productoAlmacenamiento("SSD Kingston 500GB", 710,"Kingston","Almacenamineto", 500, "SSD"),
    	new productoAlmacenamiento("SSD Kingston 1TB", 1154,"Kingston", "Almacenamineto", 1000, "SSD"),
    	new productoAlmacenamiento("SSD Kingston 2TB", 2160,"Kingston","Almacenamineto", 2000, "SSD"),
    	new productoAlmacenamiento("SSD Kingston 4TB", 11049,"Kingston","Almacenamineto", 4000, "SSD")



    	);

    }
    public List<Gabinete> getGabinete(){

    	return Arrays.asList(

    	new productoGabinete("H6 Flow ATX", 2172.95, "NZXT","Fuente de poder"),
    	new productoGabinete("Lancer ATX", 1319, "Yeyian","Fuente de poder")

    	);

    }







}