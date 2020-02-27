package org.jabref.gui.keyboard;

import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
//import org.jabref.preferences.JabRefPreferences;

import org.jabref.gui.keyboard.KeyBindingRepository;
import org.jabref.gui.keyboard.KeyBinding;


/**
 * Generic class which activates Emacs keybindings for java input {@link
 * JTextComponent}s.
 *
 * The inner class actions can also be used independently.
 */
public class EmacsKeyBindings2 {
    public static void executeEmac(Scene scene, KeyEvent event, boolean EmacsFlag, boolean CAFlag, boolean CFFlag, KeyBindingRepository keyBindingRepository){
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
                event.consume();
            }
        }
    }
}