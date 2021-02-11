package com.codecool.travelling;


import com.codecool.travelling.model.*;
import com.codecool.travelling.repository.CompanyRepository;
import com.codecool.travelling.repository.PersonalityRepository;
import com.codecool.travelling.repository.PositionRepository;
import com.codecool.travelling.repository.SalesmanRepository;
import com.codecool.travelling.service.PersonalityService;
import com.codecool.travelling.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final SalesmanRepository salesmanRepository;
    private final CompanyRepository companyRepository;
    private final PositionRepository positionRepository;
    private final PersonalityService personalityService;

    public DataInitializer(PersonalityRepository personalityRepository,PositionService positionService,PersonalityService personalityService,PositionRepository positionRepository,SalesmanRepository salesmanRepository, CompanyRepository companyRepository) {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.salesmanRepository = salesmanRepository;
        this.companyRepository = companyRepository;
        this.positionRepository = positionRepository;
        this.personalityService = personalityService;
    }

    @Override
    public void run(String... args) {

        Personality salesmanPersonality = Personality.builder()
                .energyLevel(6)
                .assertiveness(7)
                .socialContacts(7)
                .compliance(8)  //szabálykövetés
                .attitude(7)  //hozzáállás
                .decisionMaking(8)
                .adaptability(7) // alkalmazkodás
                .independence(6)
                .objectiveDecisionMaking(8)
                .studyIndex(7)
                .vocabulary(7)
                .readingLiteracy(7)
                .numberComprehension(7)
                .calculation(7)
                .creativity(2)
                .administrative(7)
                .scientificProfessional(7)
                .mechanical(7)
                .entrepreneurship(1)
                .humanityFocus(3)
                .build();

        Salesman SF = Salesman.builder()
                .username("SF")
                .firstname("Sándor")
                .lastname("Ferenc")
                .middleName("Péter")
                .gender("male")
                .phoneNumber(301234567)
                .email("sandor.ferenc@kamu.hu")
                .birthDate(LocalDate.parse("1980-01-01"))
                .nationality("Hungarian")
                .country("Hungary")
                .county("Pest")
                .city("Budapest")
                .postcode(1012)
                .address("Szabó Ilonka utca")
                .houseNumber(32)
                .onePosition("Autó értékesítő","Csonti Car")
                .oneDegree("Diploma", "Budapesti Gazdasági Egyetem")
                .language("english B2")
                .drivingLicense(true)
                .personality(salesmanPersonality)
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton( "ROLE_SALESMAN"))
                .build();
        salesmanPersonality.setSalesman(SF);
        salesmanRepository.save(SF);

        log.info(salesmanRepository.findByUsername("SF").get().toString());

        //perfect match GZ

        Company KC = Company.builder()
                .username("KC")
                .nameOfCompany("KAPTAIN Company")
                .country("Hungary")
                .county("Pest")
                .postcode(1013)
                .city("Budapest")
                .address("Batthiány tér")
                .houseNumber(4)
                .phoneNumber(306723464)
                .email("company.company2@kamu.hu")
                .dateOfFoundation(LocalDate.parse("2000-01-01"))
                .taxNumber("123456-3-89")
                .roles(Collections.singleton("ROLE_COMPANY"))
                .password(passwordEncoder.encode("password"))
                .build();
        companyRepository.save(KC);

        Position KCPosition = Position.builder()
                .company(KC)
                .nameOfPosition("Fegyver kereskedő")
                .city("Budapest")
                .salary(700000)
                .requiredMatchLevel(MATCH_LEVEL.PERFECT)
                .requirements(Arrays.asList("Kommunikatív", "Terhelhető", "Kreatív"))
                .build();
        positionRepository.save(KCPosition);

        Personality perfectPersonality = Personality.builder()
                .energyLevel(7)
                .assertiveness(7)
                .socialContacts(7)
                .compliance(7)  //szabálykövetés
                .attitude(7)  //hozzáállás
                .decisionMaking(7)
                .adaptability(7) // alkalmazkodás
                .independence(6)
                .objectiveDecisionMaking(8)
                .studyIndex(7)
                .vocabulary(7)
                .readingLiteracy(7)
                .numberComprehension(7)
                .calculation(7)
                .creativity(2)
                .administrative(7)
                .scientificProfessional(7)
                .mechanical(7)
                .entrepreneurship(1)
                .humanityFocus(3)
                .build();

        Salesman GZ = Salesman.builder()
                .username("GZ")
                .firstname("Gábor")
                .lastname("Zsazsa")
                .middleName("Beáta")
                .gender("female")
                .phoneNumber(304564583)
                .email("zsazsa.gabor@holliwood.hu")
                .birthDate(LocalDate.parse("1954-03-09"))
                .nationality("Hungarian")
                .country("Hungary")
                .county("Budapest")
                .city("Budapest")
                .postcode(1022)
                .address("Hegytetei út")
                .houseNumber(1)
                .onePosition("Színésznő","Warner Bross")
                .oneDegree("Diploma", "Élet Iskolája")
                .language("english C1")
                .drivingLicense(true)
                .personality(perfectPersonality)
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton( "ROLE_SALESMAN"))
                .build();
        perfectPersonality.setSalesman(GZ);
        salesmanRepository.save(GZ);

        log.info(salesmanRepository.findByUsername("GZ").get().toString());

//Recomended match TI

        Company CC = Company.builder()
                .username("CC")
                .nameOfCompany("Callback Company")
                .country("Hungary")
                .county("Pest")
                .postcode(1011)
                .city("Budapest")
                .address("Gyorskocsi utca")
                .houseNumber(1)
                .phoneNumber(306715264)
                .email("company.company@kamu.hu")
                .dateOfFoundation(LocalDate.parse("2000-01-01"))
                .taxNumber("123456-7-89")
                .roles(Collections.singleton("ROLE_COMPANY"))
                .password(passwordEncoder.encode("password"))
                .build();
        companyRepository.save(CC);

        Position CCPosition = Position.builder()
                .company(CC)
                .nameOfPosition("Autó értékesítő")
                .city("Budapest")
                .salary(350000)
                .requiredMatchLevel(MATCH_LEVEL.RECOMMENDED)
                .requirements(Arrays.asList("Kommunikatív", "Terhelhető", "Kreatív"))
                .build();
        positionRepository.save(CCPosition);

        Personality recommendedPersonality = Personality.builder()
                .energyLevel(9)
                .assertiveness(7)
                .socialContacts(7)
                .compliance(7)  //szabálykövetés
                .attitude(7)  //hozzáállás
                .decisionMaking(7)
                .adaptability(7) // alkalmazkodás
                .independence(6)
                .objectiveDecisionMaking(8)
                .studyIndex(7)
                .vocabulary(5)
                .readingLiteracy(7)
                .numberComprehension(7)
                .calculation(7)
                .creativity(4)
                .administrative(7)
                .scientificProfessional(7)
                .mechanical(7)
                .entrepreneurship(1)
                .humanityFocus(3)
                .build();

        Salesman TI = Salesman.builder()
                .username("TI")
                .firstname("Ilona")
                .lastname("Tündér")
                .middleName("Világszép")
                .gender("female")
                .phoneNumber(801112233)
                .email("tündér.ilona@feneketlento.org")
                .birthDate(LocalDate.parse("1002-12-31"))
                .nationality("Cosmopolitan")
                .country("Tündér Ország")
                .county("Gyémánt Palota")
                .city("Főterem")
                .postcode(0001)
                .address("Arany kapu")
                .houseNumber(1)
                .onePosition("Uralkodó","Tündérnép")
                .oneDegree("Bootcamp", "Gonosz mostoha")
                .language("tünde C2")
                .drivingLicense(false)
                .personality(recommendedPersonality)
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton( "ROLE_SALESMAN"))
                .build();

        recommendedPersonality.setSalesman(TI);
        salesmanRepository.save(TI);

        log.info(salesmanRepository.findByUsername("TI").get().toString());

//acceptable match JV

        Company LC = Company.builder()
                .username("LC")
                .nameOfCompany("Lionel Company")
                .country("Hungary")
                .county("Pest")
                .postcode(1015)
                .city("Budapest")
                .address("Ponty utca")
                .houseNumber(10)
                .phoneNumber(301115453)
                .email("company.company4@kamu.hu")
                .dateOfFoundation(LocalDate.parse("2000-01-04"))
                .taxNumber("123116-7-22")
                .roles(Collections.singleton("ROLE_COMPANY"))
                .password(passwordEncoder.encode("password"))
                .build();
        companyRepository.save(LC);

        Position LCPosition = Position.builder()
                .company(LC)
                .nameOfPosition("Hajó értékesítő")
                .city("Budapest")
                .salary(250000)
                .requiredMatchLevel(MATCH_LEVEL.ACCEPTABLE)
                .requirements(Arrays.asList("Kommunikatív", "Terhelhető", "Kreatív"))
                .build();
        positionRepository.save(LCPosition);

        Company BC = Company.builder()
                .username("BC")
                .nameOfCompany("Budget Company")
                .country("Hungary")
                .county("Pest")
                .postcode(1012)
                .city("Budapest")
                .address("Bástya utca")
                .houseNumber(3)
                .phoneNumber(306715453)
                .email("company.company1@kamu.hu")
                .dateOfFoundation(LocalDate.parse("2000-01-02"))
                .taxNumber("123456-7-22")
                .roles(Collections.singleton("ROLE_COMPANY"))
                .password(passwordEncoder.encode("password"))
                .build();
        companyRepository.save(BC);

        Position BCPosition = Position.builder()
                .company(BC)
                .nameOfPosition("Fejvadász")
                .city("Budapest")
                .salary(400000)
                .requiredMatchLevel(MATCH_LEVEL.ACCEPTABLE)
                .requirements(Arrays.asList("Kommunikatív", "Terhelhető", "Kreatív"))
                .build();
        positionRepository.save(BCPosition);

        Personality acceptablePersonality = Personality.builder()
                .energyLevel(7)
                .assertiveness(7)
                .socialContacts(7)
                .compliance(7)  //szabálykövetés
                .attitude(7)  //hozzáállás
                .decisionMaking(7)
                .adaptability(7) // alkalmazkodás
                .independence(6)
                .objectiveDecisionMaking(8)
                .studyIndex(5)
                .vocabulary(6)
                .readingLiteracy(7)
                .numberComprehension(7)
                .calculation(5)
                .creativity(2)
                .administrative(7)
                .scientificProfessional(7)
                .mechanical(7)
                .entrepreneurship(1)
                .humanityFocus(3)
                .build();

        Salesman JV = Salesman.builder()
                .username("JV")
                .firstname("János")
                .lastname("Vitéz")
                .middleName("TheBest")
                .gender("male")
                .phoneNumber(101001000)
                .email("jános.vitéz@meseland.hu")
                .birthDate(LocalDate.parse("1616-07-11"))
                .nationality("Hungarian")
                .country("Hungary")
                .county("Puszta")
                .city("Falu")
                .postcode(7022)
                .address("Kukoricás dűlő")
                .houseNumber(1)
                .onePosition("Hivatásos katona","Tündér Királyság")
                .oneDegree("gyakorlat", "háború")
                .language("magyar C2")
                .drivingLicense(false)
                .personality(acceptablePersonality)
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton( "ROLE_SALESMAN"))
                .build();
        acceptablePersonality.setSalesman(JV);
        salesmanRepository.save(JV);

        log.info(salesmanRepository.findByUsername("JV").get().toString());

//not-recommended match GZ

        Personality notRecommendedPersonality = Personality.builder()
                .energyLevel(10)
                .assertiveness(7)
                .socialContacts(7)
                .compliance(7)  //szabálykövetés
                .attitude(7)  //hozzáállás
                .decisionMaking(7)
                .adaptability(7) // alkalmazkodás
                .independence(10)
                .objectiveDecisionMaking(8)
                .studyIndex(7)
                .vocabulary(3)
                .readingLiteracy(7)
                .numberComprehension(7)
                .calculation(7)
                .creativity(1)
                .administrative(7)
                .scientificProfessional(7)
                .mechanical(7)
                .entrepreneurship(6)
                .humanityFocus(4)
                .build();

        Salesman GB = Salesman.builder()
                .username("GB")
                .firstname("Boszi")
                .lastname("Gonosz")
                .middleName("Igazán")
                .gender("female")
                .phoneNumber(404564583)
                .email("theTrueWitch@meseland.com")
                .birthDate(LocalDate.parse("1900-03-09"))
                .nationality("Cosmopolitan")
                .country("Meseföld")
                .county("Mesemegye")
                .city("Erdő")
                .postcode(1022)
                .address("erdei kunyhó")
                .houseNumber(13)
                .onePosition("Boszi","BármelyMese")
                .oneDegree("Phd", "BosziKör")
                .language("varázsnyelv C2")
                .drivingLicense(true)
                .personality(notRecommendedPersonality)
                .password(passwordEncoder.encode("password"))
                .roles(Collections.singleton( "ROLE_SALESMAN"))
                .build();
        notRecommendedPersonality.setSalesman(GB);
        salesmanRepository.save(GB);

        log.info(salesmanRepository.findByUsername("GB").get().toString());



        personalityService.matchPersonToRole(SF);
        personalityService.matchPersonToRole(GB);
        personalityService.matchPersonToRole(JV);
        personalityService.matchPersonToRole(TI);
        personalityService.matchPersonToRole(GZ);
        personalityService.matchPersonToPositionsBasedOnPersonality(SF);

    }
}
