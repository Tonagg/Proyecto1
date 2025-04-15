//interfaz de la fabrica de componentes amd

public class amd implements ComponeteFactory {

	List<CPU> getCPUs(){

		return Arrays.asList(
			new CPUAMD("Ryzen 5 5600G", 2449, 6),
            new CPUAMD("Ryzen 5 7600X", 4789, 6),
            new CPUAMD("Ryzen 7 7700X", 7042.69, 8),
         	new CPUAMD("Ryzen 9 9750X3D", 15059, 16)
            );

	}
    List<GPU> getGPUs(){

    		// Se utiliza la fábrica Intel para obtener GPUs y luego se adapta cada una.
        IntelFactory intelFactory = new IntelFactory();
        List<GPU> intelGPUs = intelFactory.getGPUs();
        return intelGPUs.stream()
                .map(gpu -> new AdaptadorGPUIntel(gpu))
                .collect(Collectors.toList());
    


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


    	IntelFactory intelFactory = new IntelFactory();
        List<GPU> intelMotherboards = intel_Nvidia.getMotherboard();
        return intelMotherboards.stream()
                .map(motherboard -> new AdaptadorGPUIntel(motherboard))
                .collect(Collectors.toList());


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
