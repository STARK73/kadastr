package alketon.kadastr.service;

import alketon.kadastr.models.Contract;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.docx4j.model.fields.merge.DataFieldName;
import org.docx4j.model.fields.merge.MailMerger;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GenerationDoc {
    private final RuleBasedNumberFormat nf;

    public GenerationDoc() {
        nf = new RuleBasedNumberFormat(Locale.forLanguageTag("ru"),
                RuleBasedNumberFormat.SPELLOUT);
    }

    public String genDoc(String typeDoc, Contract contract) {
        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                    .load(new File("C:\\Users\\Антон\\IdeaProjects\\kadastrFiles\\templates\\" + typeDoc + ".docx"));
            List<Map<DataFieldName, String>> data = new ArrayList<Map<DataFieldName, String>>();

            Map<DataFieldName, String> map = getMapContract(contract);
            data.add(map);

            MailMerger.performMerge(wordMLPackage, map, true);
            new File("C:\\Users\\Антон\\IdeaProjects\\kadastrFiles\\" + contract.getId()).mkdir();
            String path = "C:\\Users\\Антон\\IdeaProjects\\kadastrFiles\\" + contract.getId() + "\\" + typeDoc + ".docx";
            wordMLPackage.save(new java.io.File(path));
            return path;
        } catch (Docx4JException e) {
            Logger.getLogger(GenerationDoc.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private Map<DataFieldName, String> getMapContract(Contract contract) {
        Map<DataFieldName, String> map = new HashMap<DataFieldName, String>();
        map.put(new DataFieldName("Фамилия"), contract.getFamilyName());
        map.put(new DataFieldName("Имя"), contract.getFirstName());
        map.put(new DataFieldName("Отчество"), contract.getPatronymicName());
        map.put(new DataFieldName("Паспорт_серия"), contract.getPasSerialNumber());
        map.put(new DataFieldName("Паспорт_кем_выдан"), contract.getPasIssued());
        map.put(new DataFieldName("Паспорт_дата"), contract.getPasDate());
        map.put(new DataFieldName("Адрес_прописки"), contract.getAddressRegistration());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy г.");
        map.put(new DataFieldName("Дата_договора"), dateFormat.format(new Date()));
        map.put(new DataFieldName("Дата_xml"), contract.getDateXml());
        map.put(new DataFieldName("Дата_геодезии"), contract.getDateGeodesy());
        map.put(new DataFieldName("Дата_проверки"), contract.getDateChecks());
        map.put(new DataFieldName("Услуги"), getServices(contract));
        map.put(new DataFieldName("Номер_заказа"), contract.getNumber());
        map.put(new DataFieldName("Сумма"), contract.getSum());
        if(!StringUtils.isEmpty(contract.getProxy())) {
            map.put(new DataFieldName("Доверенность"), "По доверенности " + contract.getProxy());
        }
        if (!StringUtils.isEmpty(contract.getSum())) {
            map.put(new DataFieldName("Сумма_прописью"), nf.format(Integer.valueOf(contract.getSum())));
        }
        map.put(new DataFieldName("Адрес_объекта"), contract.getAddressObject());
        map.put(new DataFieldName("Отчетная_документация"), getRepDoc(contract));
        map.put(new DataFieldName("ФамилияИО"), fio(contract));
        map.put(new DataFieldName("Кадастровый_номер"), contract.getCadastralNum());
        itemService(contract, map);
        return map;
    }


    private String fio(Contract contract) {
        return contract.getFamilyName() + " " +
                contract.getFirstName().substring(0, 1) + "." +
                contract.getPatronymicName().substring(0, 1) + ".";
    }



    private void itemService(Contract contract, Map<DataFieldName, String> map) {
        ArrayList<String> itemServices = new ArrayList<>();
        if ("true".equals(contract.getOrderMej())) {
            itemServices.add("Межевание земельного участка с подготовкой межевого плана по уточнению местоположения границ под ИЖС или ЛПХ");
        }
        if ("true".equals(contract.getOrderMejObr())) {
            itemServices.add("Межевание земельного участка с подготовкой межевого плана по образованию земельного участка под ИЖС или ЛПХ");
        }
        if ("true".equals(contract.getOrderShemRasp())) {
            itemServices.add("Подготовка схемы расположения земельного участка на КПТ");
        }
        if ("true".equals(contract.getOrderTexPlan())) {
            itemServices.add("Подготовка технического плана здания");
        }
        if ("true".equals(contract.getOrderSoglOpr())) {
            itemServices.add("Составление соглашения об определении порядка пользования земельного участка");
        }
        if ("true".equals(contract.getOrderMezSX())) {
            itemServices.add("Подготовка межевого плана для выдела с/х долей");
        }
        if ("true".equals(contract.getOrderVun())) {
            itemServices.add("Вынос характерных точек границ земельного участка в натуру");
        }
        if ("true".equals(contract.getOrderAktObs())) {
            itemServices.add("Подготовка акта обследования здания");
        }
        for(int i = 0; i < itemServices.size(); i++) {
            map.put(new DataFieldName("Услуга" + (i+1)), itemServices.get(i));
        }
    }

    /**
     * Генериация списка заказов
     *
     * @param contract заказ
     * @return список заказов
     */
    private String getServices(Contract contract) {
        String service = "";
        if ("true".equals(contract.getOrderMej())) {
            service += "Межевание земельного участка с подготовкой межевого плана по уточнению местоположения границ под ИЖС или ЛПХ, ";
        }
        if ("true".equals(contract.getOrderMejObr())) {
            service += "Межевание земельного участка с подготовкой межевого плана по образованию земельного участка под ИЖС или ЛПХ, ";
        }
        if ("true".equals(contract.getOrderShemRasp())) {
            service += "Подготовка схемы расположения земельного участка на КПТ, ";
        }
        if ("true".equals(contract.getOrderTexPlan())) {
            service += "Подготовка технического плана здания, ";
        }
        if ("true".equals(contract.getOrderSoglOpr())) {
            service += "Составление соглашения об определении порядка пользования земельного участка, ";
        }
        if ("true".equals(contract.getOrderMezSX())) {
            service += "Подготовка межевого плана для выдела с/х долей, ";
        }
        if ("true".equals(contract.getOrderVun())) {
            service += "Вынос характерных точек границ земельного участка в натуру, ";
        }
        if ("true".equals(contract.getOrderAktObs())) {
            service += "Подготовка акта обследования здания, ";
        }
        service = service.substring(0, service.length() - 2);
        return service;
    }

    private String getServices1(Contract contract) {
        String service = "";
        if ("true".equals(contract.getOrderMej())) {
            service += "внесений изменений, ";
        }
        if ("true".equals(contract.getOrderMejObr())) {
            service += "постановке на учет, ";
        }
        if ("true".equals(contract.getOrderTexPlan())) {
            service += " Подготовка технического плана здания, ";
        }
        if ("true".equals(contract.getOrderSoglOpr())) {
            service += "Составление соглашения об определении порядка пользования земельного участка, ";
        }
        if ("true".equals(contract.getOrderMezSX())) {
            service += "Подготовка межевого плана для выдела с/х долей, ";
        }
        if ("true".equals(contract.getOrderAktObs())) {
            service += "Подготовка акта обследования здания, ";
        }
        service = service.substring(0, service.length() - 2);
        return service;
    }

    /**
     * Генериация списка отчетной документация
     *
     * @param contract заказ
     * @return отчетная документация
     */
    private String getRepDoc(Contract contract) {
        String service = "";
        if ("true".equals(contract.getOrderMej())) {
            service += "Межевого плана земельного участка на бумажном и на электронном носителях, ";
        }
        if ("true".equals(contract.getOrderMejObr())) {
            service += "Межевого плана земельного участка на бумажном и на электронном носителях, ";
        }
        if ("true".equals(contract.getOrderShemRasp())) {
            service += "Схемы расположения земельного участка на КПТ на бумажном и на электронном носителях, ";
        }
        if ("true".equals(contract.getOrderTexPlan())) {
            service += "Технического плана здания на электронном носителе, ";
        }
        if ("true".equals(contract.getOrderSoglOpr())) {
            service += "соглашения об определении порядка пользования земельного участка на бумажном носителяе, ";
        }
        if ("true".equals(contract.getOrderMezSX())) {
            service += "Межевого плана земельного участка на бумажном и на электронном носителях, ";
        }
        if ("true".equals(contract.getOrderVun())) {
            service += "Акт__, ";
        }
        if ("true".equals(contract.getOrderAktObs())) {
            service += "Акт обследования здания на бумажном и на электронном носителях, ";
        }
        service = service.substring(0, service.length() - 2);
        return service;
    }

}
