package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.LinkedList;

public class FileCommands {
    public static LinkedList<String []> fileCom = new LinkedList<String []>();

    public static void FillList(){
            try {
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = documentBuilder.parse("res/types.xml");
                Node root = document.getDocumentElement();
                NodeList types = root.getChildNodes();
                for(int i = 0; i<types.getLength();i++){
                    Node type = types.item(i);
                    if(type.getAttributes()!=null){
                        String name = type.getAttributes().item(0).getTextContent();
                        String comm = type.getAttributes().item(1).getTextContent();
                        fileCom.add(new String[]{name, comm});
                    }
                }
            } catch (Exception e) {e.printStackTrace(); }
    }

    public static String[] getArgs(LS l) {
        String[] args = l.getArgs();
        String name = l.getName();
        for(int i =0; i<fileCom.size();i++){
            if(name.endsWith(fileCom.get(i)[0])){
                args = fileCom.get(i)[1].split(" ");
            }
        }
        return args;
    }
}
