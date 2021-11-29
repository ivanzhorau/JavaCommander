package sample;

import java.util.LinkedList;

public class LS {
    private String name;
    private String[] args;
    public LS(String name, String[] args){
        this.name = name;
        this.args = args;
    }
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
    public static LS[] getLS(String[] args, LinkedList<String> list){
        String[] ret = CommandExecutor.executeCommand(args).split("\n");
        LS[] ls = new LS[ret.length];
        for(int i =0; i<ls.length;i++){
            String s = "";

            s+=list.getLast();

            s+=ret[i]+"/";

            ls[i] = new LS(ret[i], new String[]{"ls", s});
            ls[i].setArgs(FileCommands.getArgs(ls[i]));


        }
        return ls;
    }
}
