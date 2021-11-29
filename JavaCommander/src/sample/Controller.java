package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;

public class Controller {
    @FXML
    public TextField LeftCom, RightCom;
    @FXML
    public ListView<LS> LeftSide,RightSide;

    public LinkedList<String> leftList = new LinkedList<String>(),rightList=new LinkedList<String>();
    public TextField NameL;
    public TextField NameR;
    public RadioButton toLeft;
    public RadioButton toRight;

    public void open(){
        leftList.add("/");
        rightList.add("/");
        LeftCom.setText(leftList.getLast());
        RightCom.setText(rightList.getLast());

    }
    @FXML
    public void OpenL(ActionEvent actionEvent) {
        String[] a = {"ls", LeftCom.getText()};
        if(LeftSide.getSelectionModel().getSelectedItem()!=null) {
             a= LeftSide.getSelectionModel().getSelectedItem().getArgs();

            String[]com = new String[a.length+1];
            for(int i = 0; i<a.length;i++){
                com[i]=a[i];
            }
            com[a.length] = LeftCom.getText();
            a = com;
        }
        if(a[0].equals("ls")){
            LS[] ls = LS.getLS(a,leftList);
            leftList.add(LeftCom.getText());
            ObservableList<LS> ol = FXCollections.observableArrayList(ls);
            LeftSide.setItems(ol);
        }else{
            CommandExecutor.executeCommand(a);
        }
    }
    @FXML
    public void OpenR(ActionEvent actionEvent) {
        String[] a = {"ls", RightCom.getText()};
        if(RightSide.getSelectionModel().getSelectedItem()!=null) {
            a= RightSide.getSelectionModel().getSelectedItem().getArgs();
            String[]com = new String[a.length+1];
            for(int i = 0; i<a.length;i++){
                com[i]=a[i];
            }
            com[a.length] = RightCom.getText();
            a = com;
        }
        if(a[0].equals("ls")){
            LS[] ls = LS.getLS(a,rightList);
            rightList.add(RightCom.getText());
            ObservableList<LS> ol = FXCollections.observableArrayList(ls);
            RightSide.setItems(ol);
        }else{
            CommandExecutor.executeCommand(a);
        }
    }

    public void SelectedL(MouseEvent lsEditEvent) {
        if(LeftSide.getSelectionModel().getSelectedItem()!=null) {
            String com = leftList.getLast()+ LeftSide.getSelectionModel().getSelectedItem() + "/";
            LeftCom.setText(com);
        }
    }

    public void SelectedR(MouseEvent lsEditEvent) {
        if(RightSide.getSelectionModel().getSelectedItem()!=null){
        String com = rightList.getLast() + RightSide.getSelectionModel().getSelectedItem()+"/";
        RightCom.setText(com);
        }
    }

    public void BackL(ActionEvent actionEvent) {
        if(leftList.size()>1){
            leftList.removeLast();
        }
        LeftCom.setText(leftList.getLast());
        String[] a = {"ls",LeftCom.getText()};
        LS[] ls = LS.getLS(a,leftList);
        ObservableList<LS> ol = FXCollections.observableArrayList(ls);
        LeftSide.setItems(ol);
    }

    public void BackR(ActionEvent actionEvent) {
        if(rightList.size()>1){
            rightList.removeLast();
        }
        RightCom.setText(rightList.getLast());
        String[] a = {"ls",RightCom.getText()};
        LS[] ls = LS.getLS(a,rightList);
        ObservableList<LS> ol = FXCollections.observableArrayList(ls);
        RightSide.setItems(ol);
    }


    public void CreateL(ActionEvent actionEvent) {
        String newFile = NameL.getText();
        NameL.setText("");
        boolean b = newFile.contains(".");
        String[] a = {"ls", LeftCom.getText()+newFile};
        if(b){
            a[0] = "touch";

        }else{
            a[0] = "mkdir";
        }
        CommandExecutor.executeCommand(a);
        OpenL(actionEvent);
    }

    public void CreateR(ActionEvent actionEvent) {
        String newFile = NameR.getText();
        NameR.setText("");
        boolean b = newFile.contains(".");
        String[] a = {"ls", RightCom.getText()+newFile};
        if(b){
            a[0] = "touch";

        }else{
            a[0] = "mkdir";
        }
        CommandExecutor.executeCommand(a);
        OpenR(actionEvent);
    }

    public void DelR(ActionEvent actionEvent) {
        String[] a = {"ls",RightCom.getText().substring(0,RightCom.getText().length()-1)};
        boolean b = a[1].contains(".");
        if(b){
            a = new String[]{"rm", "-f", a[1]};

        }else{
            a= new String[]{"rm", "-rf", a[1]};
        }
        CommandExecutor.executeCommand(a);
        BackR(actionEvent);
    }

    public void DelL(ActionEvent actionEvent) {
        String[] a = {"ls",LeftCom.getText().substring(0,LeftCom.getText().length()-1)};
        boolean b = a[1].contains(".");
        if(b){
            a = new String[]{"rm", "-f", a[1]};

        }else{
            a= new String[]{"rm", "-rf", a[1]};
        }
        CommandExecutor.executeCommand(a);
        BackL(actionEvent);
    }

    public void Move(ActionEvent actionEvent) {
        boolean b = toLeft.isSelected() && !toRight.isSelected();
        String left = LeftCom.getText().substring(0,LeftCom.getText().length()-1);
        String right = RightCom.getText().substring(0,RightCom.getText().length()-1);
        String[] a = {"mv","",""};
        if(b){
            a[1] = right;
            a[2] = left;
        }else{
            a[1] = left;
            a[2] = right;
        }
        CommandExecutor.executeCommand(a);
        RightCom.setText(rightList.getLast());
        a = new String[]{"ls", RightCom.getText()};
        LS[] ls = LS.getLS(a,rightList);
        ObservableList<LS> ol = FXCollections.observableArrayList(ls);
        RightSide.setItems(ol);
        LeftCom.setText(leftList.getLast());
        a = new String[]{"ls", LeftCom.getText()};
        ls = LS.getLS(a,leftList);
        ol = FXCollections.observableArrayList(ls);
        LeftSide.setItems(ol);

    }

    public void Copy(ActionEvent actionEvent) {
        boolean b = toLeft.isSelected() && !toRight.isSelected();
        String left = LeftCom.getText().substring(0,LeftCom.getText().length()-1);
        String right = RightCom.getText().substring(0,RightCom.getText().length()-1);
        String[] a = {"cp","-a","",""};
        if(b){
            a[2] = right;
            a[3] = left;
        }else{
            a[2] = left;
            a[3] = right;
        }
        CommandExecutor.executeCommand(a);
        RightCom.setText(rightList.getLast());
        a = new String[]{"ls", RightCom.getText()};
        LS[] ls = LS.getLS(a,rightList);
        ObservableList<LS> ol = FXCollections.observableArrayList(ls);
        RightSide.setItems(ol);
        LeftCom.setText(leftList.getLast());
        a = new String[]{"ls", LeftCom.getText()};
        ls = LS.getLS(a,leftList);
        ol = FXCollections.observableArrayList(ls);
        LeftSide.setItems(ol);

    }

    public void Lclick(ActionEvent actionEvent) {
        toRight.setSelected(!toLeft.isSelected());
    }

    public void Rclick(ActionEvent actionEvent) {
        toLeft.setSelected(!toRight.isSelected());
    }
}
