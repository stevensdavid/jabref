package org.jabref.gui.keyboard;

import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class EmacsKeyBindings {
    public static void executeEmac(Scene scene, KeyEvent event, boolean EmacsFlag, boolean CAFlag, boolean CFFlag, boolean CNFlag, boolean AUFlag, KeyBindingRepository keyBindingRepository) {
        if (EmacsFlag && scene.focusOwnerProperty().get() instanceof TextField) {
            //KeyBindingRepository keyBindingRepository = Globals.getKeyPrefs();
            TextField focusedTextField = (TextField) scene.focusOwnerProperty().get();
            Optional<KeyBinding> keyBinding = keyBindingRepository.mapToKeyBinding(event);
            if (keyBinding.isPresent()) {
                if (keyBinding.get().equals(KeyBinding.EMACS_DELETE)) {
                    focusedTextField.deletePreviousChar();
                }
                else if (keyBinding.get().equals(KeyBinding.EMACS_BACKWARD)) {
                    focusedTextField.backward();
                }
                else if (CFFlag && keyBinding.get().equals(KeyBinding.EMACS_FORWARD)) {
                    focusedTextField.forward();
                }
                else if (CAFlag && keyBinding.get().equals(KeyBinding.EMACS_BEGINNING)) {
                    focusedTextField.home();
                }
                else if (keyBinding.get().equals(KeyBinding.EMACS_END)) {
                    focusedTextField.end();
                }
                else if (keyBinding.get().equals(KeyBinding.EMACS_BEGINNING_DOC)) {
                    focusedTextField.home();
                }
                else if (keyBinding.get().equals(KeyBinding.EMACS_END_DOC)) {
                    focusedTextField.end();
                }
                else if (keyBinding.get().equals(KeyBinding.EMACS_UP)) {
                    focusedTextField.home();
                }
                else if (CNFlag && keyBinding.get().equals(KeyBinding.EMACS_DOWN)) {
                    focusedTextField.end();
                }
                else if (keyBinding.get().equals(KeyBinding.EMACS_CAPITALIZE)) {
                    int pos = focusedTextField.getCaretPosition();
                    String text = focusedTextField.getText(0, focusedTextField.getText().length());
                    String[] splitText = text.split("\\s+");
                    int numOfSpace = 0;
                    for (int i = 0; i < pos - 1; ++i) {
                        if (text.charAt(i) == ' ') {
                            numOfSpace++;
                        }
                    }
                    if (pos == 0) {
                        numOfSpace = -1;
                    }
                    String res = "";
                    for (int i = 0; i < splitText.length; ++i) {
                        if (i == numOfSpace + 1) {
                            res += Character.toUpperCase(splitText[i].charAt(0));
                            res += splitText[i].substring(1);
                        } else {
                            res += splitText[i];
                        }
                        res += " ";
                    }
                    focusedTextField.setText(res);
                    focusedTextField.positionCaret(pos);
                }
                else if (keyBinding.get().equals(KeyBinding.EMACS_LOWERCASE)) {
                    int pos = focusedTextField.getCaretPosition();
                    String text = focusedTextField.getText(0, focusedTextField.getText().length());
                    String[] splitText = text.split("\\s+");
                    int numOfSpace = 0;
                    for (int i = 0; i < pos - 1; ++i) {
                        if (text.charAt(i) == ' ') {
                            numOfSpace++;
                        }
                    }
                    if (pos == 0) {
                        numOfSpace = -1;
                    }
                    String res = "";
                    for (int i = 0; i < splitText.length; ++i) {
                        if (i == numOfSpace + 1) {
                            res += splitText[i].toLowerCase();
                        } else {
                            res += splitText[i];
                        }
                        res += " ";
                    }
                    focusedTextField.setText(res);
                    focusedTextField.positionCaret(pos);
                }
                else if (AUFlag && keyBinding.get().equals(KeyBinding.EMACS_UPPERCASE)) {
                    int pos = focusedTextField.getCaretPosition();
                    String text = focusedTextField.getText(0, focusedTextField.getText().length());
                    String[] splitText = text.split("\\s+");
                    int numOfSpace = 0;
                    for (int i = 0; i < pos - 1; ++i) {
                        if (text.charAt(i) == ' ') {
                            numOfSpace++;
                        }
                    }
                    if (pos == 0) {
                        numOfSpace = -1;
                    }
                    String res = "";
                    for (int i = 0; i < splitText.length; ++i) {
                        if (i == numOfSpace + 1) {
                            res += splitText[i].toUpperCase();
                        } else {
                            res += splitText[i];
                        }
                        res += " ";
                    }
                    focusedTextField.setText(res);
                    focusedTextField.positionCaret(pos);
                }
                event.consume();
            }
        }
    }
}
