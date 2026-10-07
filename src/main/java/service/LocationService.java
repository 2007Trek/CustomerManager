package com.trek.customermanager.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LocationService {

    private static final Map<String, List<String>> PROVINCE_DISTRICTS =
            new LinkedHashMap<>();

    static {

        // CENTRAL PROVINCE
        PROVINCE_DISTRICTS.put("Central", Arrays.asList(
                "Chibombo",
                "Chisamba",
                "Chitambo",
                "Kabwe",
                "Kapiri Mposhi",
                "Luano",
                "Mkushi",
                "Mumbwa",
                "Ngabwe",
                "Serenje",
                "Shibuyunji"
        ));

        // COPPERBELT PROVINCE
        PROVINCE_DISTRICTS.put("Copperbelt", Arrays.asList(
                "Chililabombwe",
                "Chingola",
                "Kalulushi",
                "Kitwe",
                "Luanshya",
                "Lufwanyama",
                "Masaiti",
                "Mpongwe",
                "Mufulira",
                "Ndola"
        ));

        // EASTERN PROVINCE
        PROVINCE_DISTRICTS.put("Eastern", Arrays.asList(
                "Chadiza",
                "Chipata",
                "Katete",
                "Lundazi",
                "Mambwe",
                "Nyimba",
                "Petauke",
                "Sinda",
                "Vubwi",
                "Chasefu",
                "Chipangali",
                "Kasenengwa",
                "Lumezi",
                "Lusangazi",
                "Chama"
        ));

        // LUAPULA PROVINCE
        PROVINCE_DISTRICTS.put("Luapula", Arrays.asList(
                "Chembe",
                "Chienge",
                "Chipili",
                "Kawambwa",
                "Lunga",
                "Mansa",
                "Milenge",
                "Mwansabombwe",
                "Mwense",
                "Nchelenge",
                "Samfya",
                "Chifunabuli"
        ));

        // LUSAKA PROVINCE
        PROVINCE_DISTRICTS.put("Lusaka", Arrays.asList(
                "Chilanga",
                "Chirundu",
                "Chongwe",
                "Kafue",
                "Luangwa",
                "Lusaka",
                "Rufunsa"
        ));

        // MUCHINGA PROVINCE
        PROVINCE_DISTRICTS.put("Muchinga", Arrays.asList(
                "Chinsali",
                "Isoka",
                "Mafinga",
                "Mpika",
                "Nakonde",
                "Shiwang'andu",
                "Kanchibiya",
                "Lavushimanda"
        ));

        // NORTHERN PROVINCE
        PROVINCE_DISTRICTS.put("Northern", Arrays.asList(
                "Chilubi",
                "Kaputa",
                "Kasama",
                "Luwingu",
                "Mbala",
                "Mporokoso",
                "Mpulungu",
                "Mungwi",
                "Nsama",
                "Senga Hill",
                "Lunte",
                "Lupososhi"
        ));

        // NORTH-WESTERN PROVINCE
        PROVINCE_DISTRICTS.put("North-Western", Arrays.asList(
                "Chavuma",
                "Ikelenge",
                "Kabompo",
                "Kasempa",
                "Manyinga",
                "Mufumbwe",
                "Mwinilunga",
                "Solwezi",
                "Zambezi",
                "Kalumbila",
                "Mushindamo"
        ));

        // SOUTHERN PROVINCE
        PROVINCE_DISTRICTS.put("Southern", Arrays.asList(
                "Chikankata",
                "Choma",
                "Gwembe",
                "Kalomo",
                "Kazungula",
                "Livingstone",
                "Mazabuka",
                "Monze",
                "Namwala",
                "Pemba",
                "Siavonga",
                "Sinazongwe",
                "Zimba",
                "Itezhi-Tezhi"
        ));

        // WESTERN PROVINCE
        PROVINCE_DISTRICTS.put("Western", Arrays.asList(
                "Kalabo",
                "Kaoma",
                "Limulunga",
                "Luampa",
                "Lukulu",
                "Mitete",
                "Mongu",
                "Mulobezi",
                "Mwandi",
                "Nalolo",
                "Nkeyema",
                "Senanga",
                "Sesheke",
                "Shangombo",
                "Sikongo",
                "Sioma"
        ));
    }

    public static List<String> getProvinces() {
        return List.copyOf(PROVINCE_DISTRICTS.keySet());
    }

    public static List<String> getDistricts(String province) {

        if (province == null || province.isBlank()) {
            return Collections.emptyList();
        }

        return PROVINCE_DISTRICTS.getOrDefault(
                province,
                Collections.emptyList()
        );
    }
}