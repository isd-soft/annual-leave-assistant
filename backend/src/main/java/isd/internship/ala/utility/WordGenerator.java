package isd.internship.ala.utility;

import isd.internship.ala.models.LeaveRequestType;
import isd.internship.ala.repositories.LeaveRequestTypeRepository;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;

public class WordGenerator {

    @Autowired
    LeaveRequestTypeRepository leaveRequestTypeRepository;

    public List<LeaveRequestType> leaveRequestTypes = new ArrayList<>();

    public WordGenerator(List<LeaveRequestType> leaveRequestTypes) {
        this.leaveRequestTypes = leaveRequestTypes;
    }

    String templatePath;

    public ByteArrayInputStream generateNewWord(Map<String, Object> map, String requestType ) {

        File templates_path = new File(System.getProperty("user.home") + "/properties.txt");
        Scanner in = null;
        String path = null;

        try {
            in = new Scanner(templates_path);
            while(in.hasNext()){

                String line=in.nextLine();
                if(line.contains("template_path"))
                    path = line;
                break;
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] splitString = path.split("=");
        System.out.println(splitString[1]);


        Map<String, String> templates = new HashMap<String, String>() {{
            for(int i = 0; i < leaveRequestTypes.size(); i++) {
                put(leaveRequestTypes.get(i).getName(), splitString[1] + leaveRequestTypes.get(i).getName() + ".docx");

                put(leaveRequestTypes.get(i).getName(), "C:\\Users\\EVOL\\Desktop\\concediu" + leaveRequestTypes.get(i).getName() + ".docx");

            }
        }};

        templatePath = templates.get(requestType);
        System.out.println(templatePath);
        ByteArrayOutputStream bai = new ByteArrayOutputStream();

        try {
//            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(templatePath));
            XWPFDocument doc = new XWPFDocument(new FileInputStream(templatePath));
            doc = replace(doc, "[$name]", map.get("name").toString());
            doc = replace(doc, "[$surname]", map.get("surname").toString());
//            doc = replaceText(doc, "$position", map.get("function").toString());
            doc = replace(doc, "[$start]", map.get("start_day").toString());
            doc = replace(doc, "[$end]", map.get("end_day").toString());
            doc = replace(doc, "[$days]", map.get("days").toString());
            System.out.println("Enter baby");

            doc.write(bai);

//            saveWord(saveDocumentPath, doc);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return new ByteArrayInputStream(bai.toByteArray());
        }

    }

    private XWPFDocument replace(XWPFDocument doc, String findText, String replaceText) {
        for(XWPFParagraph p : doc.getParagraphs()) {
            if (hasReplaceableItem(p.getText(), findText)) {
                String replacedText = p.getText().replace(findText, replaceText);
                removeAllRuns(p);
                insertReplacementRuns(p, replacedText);
            }
        }
        return doc;
    }

    private void insertReplacementRuns(XWPFParagraph paragraph, String replacedText) {
        String[] replacementTextSplitOnCarriageReturn = replacedText.split(" \n");

        for (int j = 0; j < replacementTextSplitOnCarriageReturn.length; j++) {
            String part = replacementTextSplitOnCarriageReturn[j];

            XWPFRun newRun = paragraph.insertNewRun(j);
            newRun.setText(part);

            if (j+1 < replacementTextSplitOnCarriageReturn.length) {
                newRun.addCarriageReturn();
            }
        }
    }

    private void removeAllRuns(XWPFParagraph paragraph) {
        int size = paragraph.getRuns().size();
        for (int i = 0; i < size; i++) {
            paragraph.removeRun(0);
        }
    }

    private boolean hasReplaceableItem(String runText, String findText) {
        return runText.contains(findText);
    }

//    private static XWPFDocument replaceText(XWPFDocument doc, String findText, String replaceText){
//        StringBuilder s = new StringBuilder();
//        for (XWPFParagraph p : doc.getParagraphs()) {
//            System.out.println(p.getText());
//            List<XWPFRun> runs = p.getRuns();
//            if (runs != null) {
//                for (XWPFRun r : runs) {
//                    String text = r.getText(0);
//                    s = s.append(r);
//                    if (text != null && text.contains(findText)) {
//                        text = text.replace(findText, replaceText);
//                        r.setText(text, 0);
//                    }
//                }
//            }
//        }
//        return doc;
//    }

//    private static void saveWord(String filePath, XWPFDocument doc) throws FileNotFoundException, IOException{
//        FileOutputStream out = null;
//        try{
//            out = new FileOutputStream(filePath);
//            doc.write(out);
//            System.out.println("enter");
//        }
//        finally{
//            out.close();
//        }
//    }


}
