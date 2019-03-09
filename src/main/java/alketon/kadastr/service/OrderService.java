package alketon.kadastr.service;

import alketon.kadastr.models.Contract;
import alketon.kadastr.repos.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Service
public class OrderService {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private GenerationDoc generationDoc;
    @Autowired
    private GenerationXML generationXML;
    @Autowired
    private ContractRepo contractRepo;

    /**
     * Новая заявка (отправка почты)
     * @param contract параметры заявки
     */
    public void addContract(Contract contract) {

        String messageClient = String.format(
                "Здравствуйте, %s! \n" +
                "Вы оставили заявку на проведение кадастровых работ. \n" +
                "Специалиты ее рассмотрят и свжутся в Вами в ближайшее время. \n" +
                "По всем вопросам, обращайтесь по адресу buro7305@yandex.ru — мы обязательно поможем!\n" +
                "С уважением, администрация.",
                contract.getFirstName());

        if(!StringUtils.isEmpty(contract.getEmail())) {
            mailSender.send(contract.getEmail(), "Заявка принята", messageClient);
        }

        /*mailSender.send("buro7305@yandex.ru", "Новая заявка", "Поступила новая заявка.\n" +
                "От " + contract.getFamilyName() + " " + contract.getFirstName() );*/
    }

    /**
     * Редактирование заявки
     * @param contract параматры заявки
     */
    public void editStatusContract(Contract contract) {

        mailSender.send(contract.getEmail(), "У Вашей заявки по кадастровм работам изменился статус",
                "Добрый день, " + contract.getFirstName() + ". \n" +
                "Статус Вашей заявки изменен на - " + getStatus(contract.getStatus()) + ". \n" +
                "С уважением. ");


    }

    public void getFile(String id, String fileName, HttpServletResponse response) {
        String path = null;
        if (fileName.equals("pol")) {
            path = generationXML.getXML2(contractRepo.findById(Long.parseLong(id)).get());
        } else {
            path = generationDoc.genDoc(fileName, contractRepo.findById(Long.parseLong(id)).get());
        }


        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                try {
                    String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                    if (mimeType == null) {
                        mimeType = "application/octet-stream";
                    }
                    response.setContentType(mimeType);
                    response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
                    response.setContentLength((int) file.length());

                    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getStatus(int idStatus) {
        switch (idStatus) {
            case 0:
                return "Отказано";
            case 1:
                return "На проверке";
            case 2:
                return "К оплате";
            case 3:
                return "К выезду";
            case 4:
                return "В обработке";
            case 5:
                return "К выдаче";
            case 6:
                return "Готово";
            case 7:
                return "Приостановлено";
        }
        return "";
    }
}
