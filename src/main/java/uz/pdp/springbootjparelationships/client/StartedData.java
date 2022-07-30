package uz.pdp.springbootjparelationships.client;

import uz.pdp.springbootjparelationships.payload.FacultyDTO;
import uz.pdp.springbootjparelationships.payload.UniversityDTO;

import java.util.ArrayList;
import java.util.List;

public class StartedData {
    public static final String[] subjects = {"Matematika", "Kimyo", "English tili", "Rus Tili", "Ona Tili", "Adabiyot", "Tarix", "Biologiya"};

    public static List<UniversityDTO> universities() {
        List<UniversityDTO> universities = new ArrayList<>();
        universities.add(new UniversityDTO("O'zbekiston", "Tashkent", "Olmazor", "Usmon Nosir"));
        universities.add(new UniversityDTO("Tibbiyot", "Tashkent", "Beruniy", "M.Ulug'bek"));
        universities.add(new UniversityDTO("Pedagogika", "Tashkent", "Beruniy", "A.Navoiy"));
        universities.add(new UniversityDTO("Turin Palitexnika", "Tashkent", "Olmazor", "Amir Temur"));
        universities.add(new UniversityDTO("Jahon Tillari", "Tashkent", "Olmazor", "Amir temur"));
        return universities;
    }

//    public static List<FacultyDTO> facultys
}
