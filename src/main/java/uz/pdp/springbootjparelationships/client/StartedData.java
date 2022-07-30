package uz.pdp.springbootjparelationships.client;

import uz.pdp.springbootjparelationships.payload.FacultyDTO;
import uz.pdp.springbootjparelationships.payload.UniversityDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartedData {
    public static final String[] subjects = {"Matematika", "Fizika", "Kimyo", "Organik kimyo", "Anorganik kimyo", "Neft-gaz kimyosi", "English tili", "Rus Tili", "Ona Tili", "Adabiyot", "Tarix", "Biologiya", "Zoologiya", "Biofizika", "Ekologiya"};

    public static List<UniversityDTO> universities() {
        List<UniversityDTO> universities = new ArrayList<>();
        universities.add(new UniversityDTO("O'zbekiston milliy universiteti", "Tashkent shahar", "Talabalar shaharchasi", "Beruniy Metrosi"));
        universities.add(new UniversityDTO("Toshkent davlat texnika universiteti", "Tashkent shahar", "Talabarlar shaharchasi", "Universitet ko'chasi"));
        universities.add(new UniversityDTO("Jahon tillari universiteti", "Tashkent", "Uchtepa tumani", "Кичик халқа йўли 21 а"));
        universities.add(new UniversityDTO("Turin Palitexnika", "Tashkent", "Olmazor", "Amir Temur"));
        return universities;
    }

    public static List<FacultyDTO> faculties() {
        List<FacultyDTO> faculties = new ArrayList<>(Arrays.asList(
                new FacultyDTO("Kimyo", 1),
                new FacultyDTO("Matematika", 1),
                new FacultyDTO("Biologiya", 1),
                new FacultyDTO("Xorijiy filologiya", 1),
                new FacultyDTO("Ijtimoiy fanlar", 1),
                new FacultyDTO("Tarix", 1),
                new FacultyDTO("Ekologiya", 1),
                new FacultyDTO("Iqtisodiyot", 1),
                new FacultyDTO("Ingliz tili", 3),
                new FacultyDTO("Rus filologiyasi", 3),
                new FacultyDTO("Xalqaro Jurnalistika", 3),
                new FacultyDTO("Naft va Gaz", 2),
                new FacultyDTO("Muxandislik", 2),
                new FacultyDTO("Issiqlik eneregetika", 2),
                new FacultyDTO("Elektr energetikasi", 1)
        ));
        return faculties;
    }
}
