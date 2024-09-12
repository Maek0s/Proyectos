/*
 * Hecho por @Maek0s
 *
 * Por hacer:
 * - Poner que funcione por numepad
 * - Hacer el historial ese de last result
 * - Hacer un Pop-up arriba de mensaje
 */

package com.example.calculadora;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Calculadora extends Application {

    @FXML
    private Button comma;

    @FXML
    private Button delete;

    @FXML
    private Button deleteAll;

    @FXML
    private Label lb_lastresult;

    @FXML
    private Button negate;

    @FXML
    private Button number0;

    @FXML
    private Button number1;

    @FXML
    private Button number2;

    @FXML
    private Button number3;

    @FXML
    private Button number4;

    @FXML
    private Button number5;

    @FXML
    private Button number6;

    @FXML
    private Button number7;

    @FXML
    private Button number8;

    @FXML
    private Button number9;

    @FXML
    private Button restar;

    @FXML
    private Button sumar;

    @FXML
    private Button dividir;

    @FXML
    private Button multiplicar;

    @FXML
    private Button equal;

    @FXML
    private Button menu;

    @FXML
    private Label result;

    public static String lastresult = "0";
    public static String actualresult = "0";

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Calculadora.class.getResource("calculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 550);
        stage.setTitle("(Maek0s) Calculadora");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    // @FXML
    // void switchToMenu(ActionEvent event, Stage scene) {
    //     FXMLLoader fxmlLoader = new FXMLLoader(Calculadora.class.getResource("menu.fxml"));
    // }

    /* OPERACIONES / OPERATIONS */

    void operation(String symbol) {

        actualresult = actualresult.replace(",", ".");
        lastresult = lastresult.replace(",", ".");

        if (lastresult.equals("0") ) {
            lastresult = actualresult;
            switch (symbol) {
                case "+":
                    lb_lastresult.setText(lastresult + " + ");
                    break;
                case "-":
                    lb_lastresult.setText(lastresult + " - ");
                    break;
                case "x":
                    lb_lastresult.setText(lastresult + " x ");
                    break;
                case "/":
                    lb_lastresult.setText(lastresult + " / ");
                    break;
                default:
                    break;
            }
            result.setText("0");
            actualresult = "0";
        } else {
            float operfloat = 0.00f;

            switch (symbol) {
                case "+":
                    operfloat = Float.parseFloat(lastresult) + Float.parseFloat(actualresult);
                    break;
                case "-":
                    operfloat = Float.parseFloat(lastresult) - Float.parseFloat(actualresult);
                    break;
                case "x":
                    operfloat = Float.parseFloat(lastresult) * Float.parseFloat(actualresult);
                    break;
                case "/":
                    operfloat = Float.parseFloat(lastresult) / Float.parseFloat(actualresult);
                    break;
                default:
                    break;
            }
            lb_lastresult.setText(lb_lastresult.getText() + result.getText() + " =");

            result.setText(Float.toString(operfloat).replace(".", ","));

            if (result.getText().substring(result.getText().length() - 2, result.getText().length()).equals(",0")) {
                result.setText(result.getText().substring(0, (result.getText().length() - 2)));
            }

            dot();
            lastresult = "0";
            actualresult = "0";
        }
    }

    @FXML
    void sumar(ActionEvent event) { operation("+"); }

    @FXML
    void restar(ActionEvent event) { operation("-"); }

    @FXML
    void multiplicar(ActionEvent event) { operation("x"); }

    @FXML
    void dividir(ActionEvent event) { operation("/"); }

    /* BORRAR / DELETE */

    @FXML
    void deleteAll(ActionEvent event) {
        result.setText("0");
        lastresult = "0";
        actualresult = "0";
        lb_lastresult.setText("");
    }

    @FXML
    void delete(ActionEvent event) {
        if (!result.getText().equals("0")) {
            String text = result.getText().substring(0, result.getText().length() - 1);
            result.setText(text);
            actualresult = result.getText();

            if (result.getText().length() == 0) {
                result.setText("0");
                actualresult = result.getText();
            }
        }
        dot();
        // System.out.println(actualresult + " delete " + result.getText());
    }

    /* OTROS BOTONES / OTHER BUTTONS */

    @FXML
    void negate(ActionEvent event) {
        String txt = Integer.valueOf(Integer.parseInt(result.getText()) * -1).toString();
        result.setText(txt);
        actualresult = result.getText();
    }

    @FXML
    void comma(ActionEvent event) {
        if (!result.getText().contains(",")) {
            result.setText(result.getText() + ",");
        } else {
            System.out.println("No puede haber más de una coma.");
        }
    }

    @FXML
    void equal(ActionEvent event) {
        if (!lastresult.equals("0")) {
            if (lb_lastresult.getText().contains("+")) {
                sumar(null);
            } else if (lb_lastresult.getText().contains("-")) {
                restar(null);
            } else if (lb_lastresult.getText().contains("x")) {
                multiplicar(null);
            } else if (lb_lastresult.getText().contains("/")) {
                dividir(null);
            }
        }
    }

    /* SISTEMAS / SYSTEMS  */

    void dot() {
        
        int contador = 0;
        String txt1 = "";
        String txt2 = "";

        actualresult = result.getText().replace(".", "");

        for (int i = actualresult.length() - 1; i >= 0; i--) {
            txt1 += actualresult.charAt(i);
            contador++;
            if (contador == 3) {
                txt1 += ".";
                contador = 0;
            }
        }

        if (txt1.contains(".")) {
            for (int i = txt1.length() - 1; i >= 0; i--) {
                txt2 += txt1.charAt(i);
            }
            if (txt2.charAt(0) == '.') {
                txt2 = txt2.substring(1, txt2.length());
            }

            result.setText(txt2);
        }

        // System.out.println(actualresult + " | " + result.getText());

    }

    void click(String number) {
        // System.out.println("1 ASDASDASD : " + result.getText());
        if (result.getText().length() != 16) {
            if (result.getText().equals("0")) {
                result.setText(number);
                actualresult = result.getText();
            } else {
                result.setText(result.getText() + number);
                actualresult = result.getText();
            }
            // if (result.getFont().getSize() == 45) { } Para cuando este hecho lo del "." automatico
        } else {
            System.out.println("El número es demasiado largo 16 máx.");
        }
        // System.out.println("2 ASDASDASD : " + result.getText());

        dot();
    }

    @FXML
    void pressPAD(KeyEvent event) {
        System.out.println(event.getCode());
        
        if (event.getCode() == KeyCode.NUMPAD0 || event.getCode() == KeyCode.DIGIT0) {
            click("0");
        } else if (event.getCode() == KeyCode.NUMPAD1 || event.getCode() == KeyCode.DIGIT1) {
            click("1");
        } else if (event.getCode() == KeyCode.NUMPAD2 || event.getCode() == KeyCode.DIGIT2) {
            click("2");
        } else if (event.getCode() == KeyCode.NUMPAD3 || event.getCode() == KeyCode.DIGIT3) {
            click("3");
        } else if (event.getCode() == KeyCode.NUMPAD4 || event.getCode() == KeyCode.DIGIT4) {
            click("4");
        } else if (event.getCode() == KeyCode.NUMPAD5 || event.getCode() == KeyCode.DIGIT5) {
            click("5");
        } else if (event.getCode() == KeyCode.NUMPAD6 || event.getCode() == KeyCode.DIGIT6) {
            click("6");
        } else if (event.getCode() == KeyCode.NUMPAD7 || event.getCode() == KeyCode.DIGIT7) {
            click("7");
        } else if (event.getCode() == KeyCode.NUMPAD8 || event.getCode() == KeyCode.DIGIT8) {
            click("8");
        } else if (event.getCode() == KeyCode.NUMPAD9 || event.getCode() == KeyCode.DIGIT9) {
            click("9");
        }
    }

    /* NUMEROS / NUMBERS */

    @FXML
    void click0(ActionEvent event) { click("0"); }

    @FXML
    void click1(ActionEvent event) { click("1"); }

    @FXML
    void click2(ActionEvent event) { click("2"); }

    @FXML
    void click3(ActionEvent event) { click("3"); }

    @FXML
    void click4(ActionEvent event) { click("4"); }

    @FXML
    void click5(ActionEvent event) { click("5"); }

    @FXML
    void click6(ActionEvent event) { click("6"); }

    @FXML
    void click7(ActionEvent event) { click("7"); }

    @FXML
    void click8(ActionEvent event) { click("8"); }

    @FXML
    void click9(ActionEvent event) { click("9"); }
}