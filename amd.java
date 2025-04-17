import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;



//interfaz de la fabrica de componentes amd

public class amd implements ComponenteFactory {

	public List<CPU> getCPUs(){

		return Arrays.asList(
			new CPUamd("Ryzen 5 5600G", 2449,"AMD", "CPU", 6),
            new CPUamd("Ryzen 5 7600X", 4789, "AMD", "CPU",6),
            new CPUamd("Ryzen 7 7700X", 7042.69,"AMD", "CPU", 8),
         	new CPUamd("Ryzen 9 9750X3D", 15059,"AMD", "CPU", 16)
            );

	}
    public List<GPU> getGPUs(){

    		// Se utiliza la fábrica Intel para obtener GPUs y luego se adapta cada una.
        intel_Nvidia intelFactory = new intel_Nvidia();
        List<GPU> intelGPUs = intelFactory.getGPUs();
        return intelGPUs.stream()
                .map(gpu -> new AdaptadorGPUnvidia(gpu))
                .collect(Collectors.toList());
    


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


    	ComponenteFactory intelFactory = new intel_Nvidia();
        List<Motherboard> intelMotherboards = intelFactory.getMotherboard();
        return intelMotherboards.stream()
                .map(motherboard -> new AdaptadorMotherboardIntel(motherboard))
                .collect(Collectors.toList());


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
