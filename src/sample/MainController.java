package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Line;

import java.util.*;

public class MainController {

    @FXML
    TextField path01;
    @FXML
    TextField path02;
    @FXML
    TextField path03;
    @FXML
    TextField path04;
    @FXML
    TextField path12;
    @FXML
    TextField path13;
    @FXML
    TextField path14;
    @FXML
    TextField path23;
    @FXML
    TextField path24;
    @FXML
    TextField path34;
    @FXML
    Label label01;
    @FXML
    Label label02;
    @FXML
    Label label03;
    @FXML
    Label label04;
    @FXML
    Label label12;
    @FXML
    Label label13;
    @FXML
    Label label14;
    @FXML
    Label label23;
    @FXML
    Label label24;
    @FXML
    Label label34;
    @FXML
    TextField genNum;
    @FXML
    TextField populationNum;
    @FXML
    TextField crossNum;
    @FXML
    TextField mutationChance;
    @FXML
    TextField shortcut;
    @FXML
    TextField shortcut1;
    @FXML
    TextArea detailedInfTextArea;
    @FXML
    Button showHideButton;
    @FXML
    Button startButton;
    @FXML
    Line line01;
    @FXML
    Line line12;
    @FXML
    Line line04;
    @FXML
    Line line23;
    @FXML
    Line line34;
    @FXML
    Line line03;
    @FXML
    Line line24;
    @FXML
    Line line02;
    @FXML
    Line line14;
    @FXML
    Line line13;

    ArrayList<Path> pathArrayList = new ArrayList<>();

    public void onSetValuesButtonClick() {

        String regex = "\\d+";
        if (path01.getText().matches(regex) && path02.getText().matches(regex) && path03.getText().matches(regex) && path04.getText().matches(regex) && path12.getText().matches(regex) && path13.getText().matches(regex) && path14.getText().matches(regex) && path23.getText().matches(regex) && path24.getText().matches(regex) && path34.getText().matches(regex) && genNum.getText().matches(regex) && populationNum.getText().matches(regex) && mutationChance.getText().matches(regex) && crossNum.getText().matches(regex) && !(Integer.parseInt(genNum.getText()) == 0) && (Integer.parseInt(populationNum.getText()) >= 2) && !(Integer.parseInt(mutationChance.getText()) > 100)) {
            label01.setText(String.valueOf(Integer.parseInt(path01.getText())));
            label02.setText(String.valueOf(Integer.parseInt(path02.getText())));
            label03.setText(String.valueOf(Integer.parseInt(path03.getText())));
            label04.setText(String.valueOf(Integer.parseInt(path04.getText())));
            label12.setText(String.valueOf(Integer.parseInt(path12.getText())));
            label13.setText(String.valueOf(Integer.parseInt(path13.getText())));
            label14.setText(String.valueOf(Integer.parseInt(path14.getText())));
            label23.setText(String.valueOf(Integer.parseInt(path23.getText())));
            label24.setText(String.valueOf(Integer.parseInt(path24.getText())));
            label34.setText(String.valueOf(Integer.parseInt(path34.getText())));
            startButton.setDisable(false);
            path01.setDisable(true);
            path02.setDisable(true);
            path03.setDisable(true);
            path04.setDisable(true);
            path12.setDisable(true);
            path13.setDisable(true);
            path14.setDisable(true);
            path23.setDisable(true);
            path24.setDisable(true);
            path34.setDisable(true);
            genNum.setDisable(true);
            populationNum.setDisable(true);
            crossNum.setDisable(true);
            mutationChance.setDisable(true);
        } else {
            detailedInfTextArea.setVisible(true);
            showHideButton.setText("Скрыть подробную информацию");
            detailedInfTextArea.appendText("Некорректные данные!" + "\n");
        }
        clearLines();
    }

    public void onStartButtonClick() {
        int GEN_NUM = Integer.parseInt(genNum.getText());
        int POPULATION_NUM = Integer.parseInt(populationNum.getText());
        int MUTATION_CHANCE = Integer.parseInt(mutationChance.getText());
        int CROSS_NUM = Integer.parseInt(crossNum.getText());
        HashMap<String, Integer> lengths = new HashMap<>();
        lengths.put("01", Integer.parseInt(path01.getText()));
        lengths.put("10", Integer.parseInt(path01.getText()));
        lengths.put("02", Integer.parseInt(path02.getText()));
        lengths.put("20", Integer.parseInt(path02.getText()));
        lengths.put("03", Integer.parseInt(path03.getText()));
        lengths.put("30", Integer.parseInt(path03.getText()));
        lengths.put("04", Integer.parseInt(path04.getText()));
        lengths.put("40", Integer.parseInt(path04.getText()));
        lengths.put("12", Integer.parseInt(path12.getText()));
        lengths.put("21", Integer.parseInt(path12.getText()));
        lengths.put("13", Integer.parseInt(path13.getText()));
        lengths.put("31", Integer.parseInt(path13.getText()));
        lengths.put("14", Integer.parseInt(path14.getText()));
        lengths.put("41", Integer.parseInt(path14.getText()));
        lengths.put("23", Integer.parseInt(path23.getText()));
        lengths.put("32", Integer.parseInt(path23.getText()));
        lengths.put("24", Integer.parseInt(path24.getText()));
        lengths.put("42", Integer.parseInt(path24.getText()));
        lengths.put("34", Integer.parseInt(path34.getText()));
        lengths.put("43", Integer.parseInt(path34.getText()));
        ArrayList<Integer> defaultOrder = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            defaultOrder.add(i);
        }
        for (int i = 0; i < POPULATION_NUM; i++) {
            Collections.shuffle(defaultOrder);
            pathArrayList.add(new Path(defaultOrder.get(0), defaultOrder.get(1), defaultOrder.get(2), defaultOrder.get(3), defaultOrder.get(4)));
            pathArrayList.get(i).calcLength(lengths);
        }
        for (int i = 0; i < GEN_NUM; i++) {
            for (int j = 0; j < CROSS_NUM; j++) {
                int rand1 = (int) (Math.random() * POPULATION_NUM);
                int rand2;
                do {
                    rand2 = (int) (Math.random() * POPULATION_NUM);
                } while (rand1 == rand2);
                Path parent1 = pathArrayList.get(rand1);
                Path parent2 = pathArrayList.get(rand2);
                crossing(parent1, parent2);
            }
            for (int j = 0; j < pathArrayList.size(); j++) {
                if ((int) (Math.random() * 100) < MUTATION_CHANCE) {
                    Path mutatedPath = mutation(pathArrayList.get(j));
                    mutatedPath.calcLength(lengths);
                    pathArrayList.set(j, mutatedPath);
                }
            }
            for (int j = POPULATION_NUM; j < POPULATION_NUM + CROSS_NUM * 2; j++) {
                pathArrayList.get(j).calcLength(lengths);
            }
            pathArrayList.sort(Comparator.comparing(Path::getLength));
            for (int j = 0; j < CROSS_NUM * 2; j++) {
                pathArrayList.remove(POPULATION_NUM);
            }
        }
        for (int i = 0; i < pathArrayList.size(); i++) {
            detailedInfTextArea.appendText(i + ": " + pathArrayList.get(i).getLength() + "\n");
        }
        shortcut.setText(pathArrayList.get(0).getPath(true));
        String path = pathArrayList.get(0).getPath(false);
        String ch0;
        String ch1;
        String row;
        for (int i = 0; i < 5; i++) {
            ch0 = Character.toString(path.charAt(i));
            ch1 = Character.toString(path.charAt(i + 1));
            row = ch0 + ch1;
            switch (row) {
                case "01":
                case "10":
                    line01.setVisible(true);
                    break;
                case "02":
                case "20":
                    line02.setVisible(true);
                    break;
                case "03":
                case "30":
                    line03.setVisible(true);
                    break;
                case "04":
                case "40":
                    line04.setVisible(true);
                    break;
                case "12":
                case "21":
                    line12.setVisible(true);
                    break;
                case "13":
                case "31":
                    line13.setVisible(true);
                    break;
                case "14":
                case "41":
                    line14.setVisible(true);
                    break;
                case "23":
                case "32":
                    line23.setVisible(true);
                    break;
                case "24":
                case "42":
                    line24.setVisible(true);
                    break;
                case "34":
                case "43":
                    line34.setVisible(true);
                    break;
            }
        }
        shortcut1.setText(Objects.toString(pathArrayList.get(0).getLength()));
        pathArrayList = new ArrayList<>();
        startButton.setDisable(true);
        enableFields();
    }

    public void crossing(Path parent1, Path parent2) {
        ArrayList<Integer> stepsParent1 = new ArrayList<>();
        stepsParent1.add(parent1.getStep1());
        stepsParent1.add(parent1.getStep2());
        stepsParent1.add(parent1.getStep3());
        stepsParent1.add(parent1.getStep4());
        stepsParent1.add(parent1.getStep5());
        ArrayList<Integer> stepsParent2 = new ArrayList<>();
        stepsParent2.add(parent2.getStep1());
        stepsParent2.add(parent2.getStep2());
        stepsParent2.add(parent2.getStep3());
        stepsParent2.add(parent2.getStep4());
        stepsParent2.add(parent2.getStep5());
        ArrayList<Integer> stepsChild1 = new ArrayList<>();
        ArrayList<Integer> stepsChild2 = new ArrayList<>();
        switch ((int) ((Math.random() * (5 - 1)) + 1)) {
            case 1:
                stepsChild1.add(stepsParent1.get(0));
                for (int i = 1; i < 5; i++) {
                    if (!stepsChild1.contains(stepsParent2.get(i))) {
                        stepsChild1.add(stepsParent2.get(i));
                    }
                }
                for (int i = 1; i < 5; i++) {
                    if (!stepsChild1.contains(stepsParent1.get(i))) {
                        stepsChild1.add(stepsParent1.get(i));
                    }
                }
                stepsChild2.add(stepsParent2.get(0));
                for (int i = 1; i < 5; i++) {
                    if (!stepsChild2.contains(stepsParent1.get(i))) {
                        stepsChild2.add(stepsParent1.get(i));
                    }
                }
                for (int i = 1; i < 5; i++) {
                    if (!stepsChild2.contains(stepsParent2.get(i))) {
                        stepsChild2.add(stepsParent2.get(i));
                    }
                }
                break;
            case 2:
                stepsChild1.add(stepsParent1.get(0));
                stepsChild1.add(stepsParent1.get(1));
                for (int i = 2; i < 5; i++) {
                    if (!stepsChild1.contains(stepsParent2.get(i))) {
                        stepsChild1.add(stepsParent2.get(i));
                    }
                }
                for (int i = 2; i < 5; i++) {
                    if (!stepsChild1.contains(stepsParent1.get(i))) {
                        stepsChild1.add(stepsParent1.get(i));
                    }
                }
                stepsChild2.add(stepsParent2.get(0));
                stepsChild2.add(stepsParent2.get(1));
                for (int i = 2; i < 5; i++) {
                    if (!stepsChild2.contains(stepsParent1.get(i))) {
                        stepsChild2.add(stepsParent1.get(i));
                    }
                }
                for (int i = 2; i < 5; i++) {
                    if (!stepsChild2.contains(stepsParent2.get(i))) {
                        stepsChild2.add(stepsParent2.get(i));
                    }
                }
                break;
            case 3:
                stepsChild1.add(stepsParent1.get(0));
                stepsChild1.add(stepsParent1.get(1));
                stepsChild1.add(stepsParent1.get(2));
                for (int i = 3; i < 5; i++) {
                    if (!stepsChild1.contains(stepsParent2.get(i))) {
                        stepsChild1.add(stepsParent2.get(i));
                    }
                }
                for (int i = 3; i < 5; i++) {
                    if (!stepsChild1.contains(stepsParent1.get(i))) {
                        stepsChild1.add(stepsParent1.get(i));
                    }
                }
                stepsChild2.add(stepsParent2.get(0));
                stepsChild2.add(stepsParent2.get(1));
                stepsChild2.add(stepsParent2.get(2));
                for (int i = 3; i < 5; i++) {
                    if (!stepsChild2.contains(stepsParent1.get(i))) {
                        stepsChild2.add(stepsParent1.get(i));
                    }
                }
                for (int i = 3; i < 5; i++) {
                    if (!stepsChild2.contains(stepsParent2.get(i))) {
                        stepsChild2.add(stepsParent2.get(i));
                    }
                }
                break;
            case 4:
                stepsChild1.add(stepsParent1.get(0));
                stepsChild1.add(stepsParent1.get(1));
                stepsChild1.add(stepsParent1.get(2));
                stepsChild1.add(stepsParent1.get(3));
                for (int i = 4; i < 5; i++) {
                    if (!stepsChild1.contains(stepsParent2.get(i))) {
                        stepsChild1.add(stepsParent2.get(i));
                    }
                }
                for (int i = 4; i < 5; i++) {
                    if (!stepsChild1.contains(stepsParent1.get(i))) {
                        stepsChild1.add(stepsParent1.get(i));
                    }
                }
                stepsChild2.add(stepsParent2.get(0));
                stepsChild2.add(stepsParent2.get(1));
                stepsChild2.add(stepsParent2.get(2));
                stepsChild2.add(stepsParent2.get(3));
                for (int i = 4; i < 5; i++) {
                    if (!stepsChild2.contains(stepsParent1.get(i))) {
                        stepsChild2.add(stepsParent1.get(i));
                    }
                }
                for (int i = 4; i < 5; i++) {
                    if (!stepsChild2.contains(stepsParent2.get(i))) {
                        stepsChild2.add(stepsParent2.get(i));
                    }
                }
                break;
            default:
                detailedInfTextArea.appendText("Error! Wrong case in \"crossing switch\"!");
                break;
        }
        pathArrayList.add(new Path(stepsChild1.get(0), stepsChild1.get(1), stepsChild1.get(2), stepsChild1.get(3), stepsChild1.get(4)));
        pathArrayList.add(new Path(stepsChild2.get(0), stepsChild2.get(1), stepsChild2.get(2), stepsChild2.get(3), stepsChild2.get(4)));
    }

    public Path mutation(Path path) {
        int buf;
        ArrayList<Integer> stepsPath = new ArrayList<>(5);
        stepsPath.add(path.getStep1());
        stepsPath.add(path.getStep2());
        stepsPath.add(path.getStep3());
        stepsPath.add(path.getStep4());
        stepsPath.add(path.getStep5());
        int rand1 = (int) (Math.random() * 5);
        int rand2;
        do {
            rand2 = (int) (Math.random() * 5);
        } while (rand1 == rand2);
        buf = stepsPath.get(rand1);
        stepsPath.set(rand1, stepsPath.get(rand2));
        stepsPath.set(rand2, buf);
        return new Path(stepsPath.get(0), stepsPath.get(1), stepsPath.get(2), stepsPath.get(3), stepsPath.get(4));
    }

    public void onShowHideButtonClick() {
        if (Objects.equals(showHideButton.getText(), "Скрыть подробную информацию")) {
            detailedInfTextArea.setVisible(false);
            showHideButton.setText("Показать подробную информацию");
        } else {
            detailedInfTextArea.setVisible(true);
            showHideButton.setText("Скрыть подробную информацию");
        }
    }

    public void onCleanFieldsButtonClick1() {
        detailedInfTextArea.clear();
    }

    public void onCleanFieldsButtonClick() {
        clearFields();
        clearLines();
    }

    public void onInfoButtonClick() {
        String[] strings = detailedInfTextArea.getText().split("\n");
        String str = strings[strings.length-1];
        String info;
        String visible;
        if (str.equals("- вероятность мутации должна быть в диапазоне от 0 до 100.")) {
            info = "1";
        } else {
            info = "0";
        }
        if (detailedInfTextArea.isVisible()) {
            visible = "1";
        } else {
            visible = "0";
        }
        String condition = info + visible;
        switch (condition) {
            case "11":
            case "10":
                onShowHideButtonClick();
                break;
            case "01":
                detailedInfTextArea.appendText("Правила заполнения полей:\n- запрещён ввод символов кроме цифр;\n- запрещён ввод отрицательных значений;\n- размер путей должен быть больше либо равен нулю;\n- число поколений должно быть больше нуля;\n- размер популяции должен быть больше либо равен двум;\n- вероятность мутации должна быть в диапазоне от 0 до 100.\n");
                break;
            case "00":
                onShowHideButtonClick();
                detailedInfTextArea.appendText("Правила заполнения полей:\n- запрещён ввод символов кроме цифр;\n- запрещён ввод отрицательных значений;\n- размер путей должен быть больше либо равен нулю;\n- число поколений должно быть больше нуля;\n- размер популяции должен быть больше либо равен двум;\n- вероятность мутации должна быть в диапазоне от 0 до 100.\n");
                break;
            default:
                detailedInfTextArea.appendText("Error! Wrong case in infoButton!");
                break;
        }
    }

    public void enableFields() {
        path01.setDisable(false);
        path02.setDisable(false);
        path03.setDisable(false);
        path04.setDisable(false);
        path12.setDisable(false);
        path13.setDisable(false);
        path14.setDisable(false);
        path23.setDisable(false);
        path24.setDisable(false);
        path34.setDisable(false);
        genNum.setDisable(false);
        populationNum.setDisable(false);
        crossNum.setDisable(false);
        mutationChance.setDisable(false);
    }

    public void clearFields() {
        path01.clear();
        path02.clear();
        path03.clear();
        path04.clear();
        path12.clear();
        path13.clear();
        path14.clear();
        path23.clear();
        path24.clear();
        path34.clear();
        genNum.clear();
        populationNum.clear();
        crossNum.clear();
        mutationChance.clear();
        enableFields();
        label01.setText("0");
        label02.setText("0");
        label03.setText("0");
        label04.setText("0");
        label12.setText("0");
        label13.setText("0");
        label14.setText("0");
        label23.setText("0");
        label24.setText("0");
        label34.setText("0");
    }

    public void clearLines() {
        line01.setVisible(false);
        line02.setVisible(false);
        line03.setVisible(false);
        line04.setVisible(false);
        line12.setVisible(false);
        line13.setVisible(false);
        line14.setVisible(false);
        line23.setVisible(false);
        line24.setVisible(false);
        line34.setVisible(false);
    }
}
