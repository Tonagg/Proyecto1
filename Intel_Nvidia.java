

public class Intel_Nvidia implements ComponenteFactory {

	List<CPU> getCPUs(){

		return Arrays.asList(
            new CPUIntel("Core i3-13100", 2805, 4),
            new CPUIntel("Core i5-13600K", 5799, 14),
            new CPUIntel("Core i7-13700K", 8750.69, 16),
            new CPUIntel("Core i9-13900K", 10899.53, 24)
        );

	}
    List<GPU> getGPUs(){

    	return Arrays.asList( 
    		new GPUnvidia("GTX 1660", 4000, "GDDR6"),
    		new GPUnvidia("RTX 3060", 6329,  "GDDR6"),
    		new GPUnvidia("RTX 4070", 25000, "GDDR6" ),
    		new GPUnvidia("RTX 4080", 34552.61, "GDDR6X" ),
    		new GPUnvidia("RTX 4090", 64950, "GDDR6X")
    	);

    }
    List<RAM> getRAM(){

    	return Arrays.asList( 

    	new productoRAM("memoria RAM 8GB adata", 351, "Adata", 8),
    	new productoRAM("memoria RAM 16GB adata", 596, "Adata", 16),
    	new productoRAM("memoria RAM 32GB adata", 999, "Adata", 32),
    	new productoRAM("memoria RAM 8GB Kingston", 391, "Kingston", 8),
    	new productoRAM("memoria RAM 16GB Kingston", 710, "Kingston", 16),
    	new productoRAM("memoria RAM 32GB Kingston", 1261, "Kingston", 32)

    );

    }
    List<Motherboard> getMotherboard(){

    	return Arrays.asList( 
    		new MotherboardIntel("ROG Maximus Z790 Hero", 13490, "ASUS", "IntelZ790"),
    		new MotherboardIntel("TUF Gaming B760-Plus WIFI D4", 4835.62, "ASUS"  "IntelB760"),
    		new MotherboardIntel("MEG Z790 Godlike", 13821.82,"MSI", "IntelZ790" ),
    		new MotherboardIntel("MAG B760 Tomahawk WIFI DDR4", 5862.82, "MSI","IntelB760")
    	);



    }
    List<Fuente> getFuente(){

    	return Arrays.asList(

    	new productoFuenteDePoder("Fuente de poder EVGA 800w", 1919, "EVGA", 800),
    	new productoFuenteDePoder("Fuente de poder EVGA 1000w", 4039, "EVGA", 1000),
    	new productoFuenteDePoder("Fuente de poder EVGA 1500w", 7548, "EVGA", 1500),
    	new productoFuenteDePoder("Fuente de poder Corsair 800w", 2893.89, "Corsair", 800),
    	new productoFuenteDePoder("Fuente de poder Corsair 1200w", 5441, "Corsair", 1200),
    	new productoFuenteDePoder("Fuente de poder Corsair 1500w", 8130, "Corsair", 1500),
    	new productoFuenteDePoder("Fuente de poder XPG 500w", 799, "XPG", 800),
    	new productoFuenteDePoder("Fuente de poder XPG 700w", 1428.98, "XPG", 800),
   		new productoFuenteDePoder("Fuente de poder XPG 1000w", 3259, "XPG", 800),

    
    );

    }
    List<Almacenamineto> getAlmacenamiento(){

    	return Arrays.asList(

    	new productoAlmacenamiento("HDD Western Digital Blue 500GB", 514.79,"Western Digital Blue", 500, "HDD"),
    	new productoAlmacenamiento("HDD Western Digital Blue 1TB", 1548,"Western Digital Blue", 500, "HDD"),
    	new productoAlmacenamiento("HDD Seagate Barracuda 1TB", 1141,"Seagate Barracuda", 1000, "HDD"),
    	new productoAlmacenamiento("HDD Seagate Barracuda 2TB", 3721,"Seagate Barracuda", 2000, "HDD"),
    	new productoAlmacenamiento("SSD Kingston 500GB", 710,"Kingston", 500, "SSD"),
    	new productoAlmacenamiento("SSD Kingston 1TB", 1154,"Kingston", 1000, "SSD"),
    	new productoAlmacenamiento("SSD Kingston 2TB", 2160,"Kingston", 2000, "SSD"),
    	new productoAlmacenamiento("SSD Kingston 4TB", 11049,"Kingston", 4000, "SSD")



    	);

    }
    List<Gabinete> getGabinete(){

    	return Arrays.asList(

    	new productoGabinete("H6 Flow ATX", 2172.95, "NZXT"),
    	new productoGabinete("Lancer ATX", 1319, "Yeyian")

    	);

    }







}