/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import business.API;
import business.Action;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author K G
 */
public class ReadFiles {

    //private static String descriptionPath = "/androidFiles/descriptions.txt";
    //private static String apiFilesPath = "WEB_INF/androidFiles/actions";
    private String descriptionPath1 = this.getClass().getResource("/androidFiles/descriptions.txt").toString();
    private String descriptionPath = descriptionPath1.substring(5);
    private String apiFilesPath1 = this.getClass().getResource("/androidFiles/actions").toString();
    private String apiFilesPath = apiFilesPath1.substring(5);
    private ArrayList<String[]> descriptions = new ArrayList<String[]>();
    private ArrayList<String[]> api = new ArrayList<String[]>();
    private ArrayList<String[]> actions = new ArrayList<String[]>();

    public ReadFiles() {
        setDescriptions();
        setAPI();
        setActions();
    }

    public void initialise() {
        setDescriptions();
        setAPI();
        setActions();
    }

    public String getPath() {
        return descriptionPath1;
    }

    public List<API> getAPIList() {
        List<API> apiList = new ArrayList<API>();
        for (String[] ap : api) {
            API a = new API();
            a.setAPIName(ap[0]);
            List<String> m = new ArrayList<String>();
            for (int j = 1; j < ap.length; j++) {
                m.add(ap[j]);
            }
            a.setAPIActions(m);
            apiList.add(a);
        }
        return apiList;
    }

    public List<String> getAPINameList() {

        List<API> apiList = getAPIList();
        List<String> nameList = new ArrayList<String>();
        for (API ap : apiList) {
            nameList.add(ap.getAPIName());
        }
        //Collections.sort(nameList);
        return nameList;
    }

    public API getAPI(String name) {
        List<API> apiList = getAPIList();
        API a = new API();
        for (API ap : apiList) {
            if (name.equals(ap.getAPIName())) {
                a = ap;
                break;
            }
        }
        return a;
    }

    public List<Action> getActionList() {
        List<Action> actionList = new ArrayList<Action>();
        for (String[] a : actions) {
            Action ac = new Action();
            ac.setActionName(a[0]);
            ac.setUsingAPIS(a[1]);
            int flag = 0;
            for (String[] d : descriptions) {
                if (a[0].equals(d[0])) {
                    flag = 1;
                    ac.setDescription(d[1]);
                    continue;
                }
            }
            if (flag == 0) {
                ac.setDescription("Description not found");
            }
            actionList.add(ac);
        }
        return actionList;
    }

    public List<String> getActionNameList() {
        List<Action> actionList = getActionList();
        List<String> nameList = new ArrayList<String>();
        for (Action ac : actionList) {
            nameList.add(ac.getActionName());
        }
        Collections.sort(nameList);
        return nameList;
    }

    public Action getAction(String name) {
        List<Action> actionList = getActionList();
        Action a = new Action();
        for (Action ac : actionList) {
            if (name.equals(ac.getActionName())) {
                a = ac;
                break;
            }
        }
        return a;
    }

    public void setDescriptions() {
        List<String> descriptionLines = new ArrayList<String>();
        descriptionLines = readFileLines(descriptionPath);
        for (String line : descriptionLines) {
            String[] sp = line.split(";");
            descriptions.add(sp);
        }
    }

    public void setAPI() {
        List<String> fileNames = new ArrayList<String>();
        fileNames = readFileNames(apiFilesPath);
        for (String file : fileNames) {
            int n = getFileNameIntgerPart(file);
            String apiName = "API" + n;
            String filePath = apiFilesPath + "/" + file;
            List<String> actionsInAPI = new ArrayList<String>();
            actionsInAPI = readFileLines(filePath);
            String apiArray[] = new String[actionsInAPI.size() + 1];
            apiArray[0] = apiName;
            for (int i = 1; i < apiArray.length; i++) {
                apiArray[i] = actionsInAPI.get(i - 1);
            }
            api.add(apiArray);
        }
    }

    public void setActions() {
        List<String> fileNames = new ArrayList<String>();
        fileNames = readFileNames(apiFilesPath);
        for (String file : fileNames) {
            int n = getFileNameIntgerPart(file);
            String apiName = "API" + n;
            String filePath = apiFilesPath + "/" + file;
            List<String> actionsInAPI = new ArrayList<String>();
            actionsInAPI = readFileLines(filePath);
            if (actions.isEmpty()) {
                for (String a : actionsInAPI) {
                    String[] x = new String[2];
                    x[0] = a;
                    x[1] = apiName;
                    actions.add(x);
                }
            } else {
                for (String c : actionsInAPI) {
                    int flag = 0;
                    for (String[] d : actions) {
                        if (c.equals(d[0])) {
                            flag = 1;
                            d[1] = d[1] + ", " + apiName;
                            continue;
                        }
                    }
                    if (flag == 1) {
                        continue;
                    } else {
                        String[] y = new String[2];
                        y[0] = c;
                        y[1] = apiName;
                        actions.add(y);
                    }
                }
            }
        }
    }

    public List<String> readFileNames(String folderPath) {
        List<String> fileNames = new ArrayList<String>();
        try {
            File folder = new File(folderPath);
            File[] apiFilesList = folder.listFiles();
            for (File file : apiFilesList) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                    //System.out.println(file.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Error while reading " + e.getMessage());
        }
        return fileNames;
    }

    public int getFileNameIntgerPart(String fileName) {
        Scanner sc = new Scanner(fileName).useDelimiter("[^0-9]+");
        int x = sc.nextInt();
        return x;
    }

    public List<String> readFileLines(String path) {
        List<String> linesInFile = new ArrayList<String>();
        try {
            FileReader input = new FileReader(path);
            BufferedReader br = new BufferedReader(input);
            String lines;
            while ((lines = br.readLine()) != null) {
                linesInFile.add(lines);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error while reading " + e.getMessage());
        }
        return linesInFile;
    }

    public static void main(String[] args) {
        ReadFiles rf = new ReadFiles();
        //ReadFiles.initialise();
        //System.out.println(rf.getAPINameList().get(19));
        System.out.println(rf.getAction("android.app.action.ACTION_PASSWORD_FAILED").getUsingAPIS());
    }
}
